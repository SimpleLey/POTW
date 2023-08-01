package org.totemcraft.potw.loader;

import com.google.gson.JsonElement;
import lombok.SneakyThrows;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.totemcraft.potw.PicnicOfTheWorld;
import org.totemcraft.potw.R;
import org.totemcraft.potw.block.BlockLoader;
import org.totemcraft.potw.group.ItemGroupLoader;
import org.totemcraft.potw.item.ItemLoader;
import org.totemcraft.potw.util.Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface ContentLoader<T> {
    List<Path> contentDirs(Path root);

    default T getLoaded(String id) {
        return null;
    }
    void load(JsonElement input);

    @SneakyThrows
    default void loadAll() {
        var dirPath = contentDirs(R.RootPath);

        for (Path dir : dirPath) try {
            Files.walk(dir)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            var content = Files.readString(path);

                            try {
                                var json = Json.from(content, JsonElement.class);
                                load(json);
                            } catch (Exception e) {
                                PicnicOfTheWorld.getLogger().error("Failed to load content from " + path, e);
                            }
                        } catch (IOException e) {
                            PicnicOfTheWorld.getLogger().error("Failed to load file from " + path, e);
                        }
                    });
        } catch (IOException e) {
            PicnicOfTheWorld.getLogger().error("Failed to load content from " + dir, e);
        }
    }

    default BlockModel getExtraBlockModel(ResourceLocation location) {
        return null;
    }
    default String getExtraBlockState(ResourceLocation location) {
        return null;
    }

    ContentLoader<?>[] Loaders = new ContentLoader[]{
            ItemGroupLoader.INSTANCE,
            ItemLoader.INSTANCE,
            BlockLoader.INSTANCE,
    };

    static void loadAllContent() {
        for (ContentLoader<?> loader : Loaders) {
            loader.loadAll();
        }
    }

    static BlockModel findExtraBlockModel(ResourceLocation location) {
        for (ContentLoader<?> loader : Loaders) {
            var model = loader.getExtraBlockModel(location);
            if (model != null) return model;
        }
        return null;
    }

    static ModelBakery.LoadedJson findExtraBlockState(ResourceLocation location) {
        for (ContentLoader<?> loader : Loaders) {
            var jsonStr = loader.getExtraBlockState(location);
            if (jsonStr != null) {
                var json = Json.from(jsonStr, JsonElement.class);
                return new ModelBakery.LoadedJson(jsonStr, json);
            }
        }
        return null;
    }
}
