package org.totemcraft.potw.item;

import com.google.gson.JsonElement;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.potw.PicnicOfTheWorld;
import org.totemcraft.potw.R;
import org.totemcraft.potw.client.BlockModelGenerator;
import org.totemcraft.potw.loader.ContentLoader;
import org.totemcraft.potw.util.Json;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ItemLoader implements ContentLoader<Item> {
    INSTANCE;

    public final DeferredRegister<Item> register = DeferredRegister.create(ForgeRegistries.ITEMS, PicnicOfTheWorld.Id);

    private Map<String, ItemDefinition> loadedDefinition = new HashMap<>();
    private Map<String, RegistryObject<Item>> loadedItems = new HashMap<>();

    ItemLoader() {
        register.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public List<Path> contentDirs(Path jar) {
        return List.of(jar.resolve("item"));
    }

    @Override
    public Item getLoaded(String id) {
        return loadedItems.get(id).orElse(null);
    }

    public void load(JsonElement input) {
        var definition = Json.from(input, ItemDefinition.class);

        var registryItem = register.register(definition.getId().getPath(), definition::build);
        loadedItems.put(definition.getId().toString(), registryItem);
        loadedDefinition.put(definition.getId().toString(), definition);
    }

    @Override
    public BlockModel getExtraBlockModel(ResourceLocation location) {
        if (!location.getPath().startsWith("item/")) {
            return null;
        }

        var id = location.getPath().substring("item/".length());
        var definition = loadedDefinition.get(R.location(id).toString());

        if (definition == null) return null;

        if (definition.getFeature().getLayer() != null) try {
            return BlockModelGenerator.layer(definition.getFeature().getLayer().toString());
        } catch (Exception e) {
            PicnicOfTheWorld.getLogger().error("Failed to load layer model for " + id, e);
        }

        return null;
    }
}
