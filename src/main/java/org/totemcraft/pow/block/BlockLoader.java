package org.totemcraft.pow.block;

import com.google.gson.JsonElement;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.loader.ContentLoader;
import org.totemcraft.pow.util.Json;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BlockLoader implements ContentLoader<Block> {
    INSTANCE;

    public final DeferredRegister<Block> register = DeferredRegister.create(ForgeRegistries.BLOCKS, PicnicOfTheWorld.Id);
    public final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, PicnicOfTheWorld.Id);

    private Map<String, BlockDefinition> loadedDefinitions = new HashMap<>();
    private Map<String, RegistryObject<Block>> loadedBlocks = new HashMap<>();
    private Map<String, RegistryObject<Item>> loadedBlockItems = new HashMap<>();

    BlockLoader() {
        register.register(FMLJavaModLoadingContext.get().getModEventBus());
        itemRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public List<Path> contentDirs(Path root) {
        return List.of(root.resolve("blocks"));
    }

    @Override
    public void load(JsonElement input) {
        var definition = Json.from(input, BlockDefinition.class);

        var registryItem = register.register(definition.getId().getPath(), () -> {
            var block = definition.build();
            itemRegister.register(definition.getId().getPath(), () -> new BlockItem(block, new Item.Properties()));
            return block;
        });
        loadedBlocks.put(definition.getId().toString(), registryItem);
        loadedDefinitions.put(definition.getId().toString(), definition);
    }

    @Override
    public BlockModel getExtraBlockModel(ResourceLocation location) {
        System.out.println("getExtraBlockModel: " + location);
        return ContentLoader.super.getExtraBlockModel(location);
    }
}
