package org.totemcraft.pow.block;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.Pair;
import lombok.Getter;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.R;
import org.totemcraft.pow.block.type.PBlockItem;
import org.totemcraft.pow.client.BlockStateGenerator;
import org.totemcraft.pow.client.BlockModelGenerator;
import org.totemcraft.pow.loader.ContentLoader;
import org.totemcraft.pow.util.Json;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BlockLoader implements ContentLoader<Block> {
    INSTANCE;

    private final DeferredRegister<Block> register = DeferredRegister.create(ForgeRegistries.BLOCKS, PicnicOfTheWorld.Id);
    private final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, PicnicOfTheWorld.Id);

    @Getter
    private Map<String, Pair<BlockDefinition, RegistryObject<Block>>> loaded = new HashMap<>();
    private Map<String, RegistryObject<Item>> loadedBlockItems = new HashMap<>();

    BlockLoader() {
        register.register(FMLJavaModLoadingContext.get().getModEventBus());
        itemRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public List<Path> contentDirs(Path root) {
        return List.of(root.resolve("block"));
    }

    @Override
    public void load(JsonElement input) {
        var definition = Json.from(input, BlockDefinition.class);

        var registryItem = register.register(definition.getId().getPath(), () -> {
            var block = definition.build();
            itemRegister.register(definition.getId().getPath(), () -> new PBlockItem(block, new Item.Properties(), definition));
            return block;
        });
        loaded.put(definition.getId().toString(), Pair.of(definition, registryItem));
    }

    @Override
    public BlockModel getExtraBlockModel(ResourceLocation location) {
        if (location.getPath().startsWith("item/")) {
            var id = location.getPath().substring("item/".length());
            var pair = loaded.get(R.location(id).toString());
            if (pair == null) return null;
            var definition = pair.first();

            try {
                if (definition.getFeature().getLayer() != null) {
                    return BlockModelGenerator.layer(definition.getFeature().getLayer().toString());
                } else if (definition.getFeature().getModel() != null){
                    return BlockModelGenerator.parent(definition.getFeature().getModel().toString());
                }
            } catch (Exception e) {
                PicnicOfTheWorld.getLogger().error("Failed to load layer model for " + id, e);
            }
        }

        return null;
    }

    @Override
    public String getExtraBlockState(ResourceLocation location) {
        // remove prefix "blockstates/" and suffix ".json"
        var id = location.getPath().replaceFirst("^blockstates/", "").replaceFirst(".json$", "");
        if (id.isBlank()) return null;

        var pair = loaded.get(R.location(id).toString());
        if (pair == null) return null;
        var definition = pair.first();

        if (definition.getFeature().getModel() != null) {
            if (definition.getFeature().getFacing()) {
                return BlockStateGenerator.ofFacing(definition.getFeature().getModel().toString());
            }
            return BlockStateGenerator.of(definition.getFeature().getModel().toString());
        }

        return null;
    }
}
