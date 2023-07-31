package org.totemcraft.pow.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.R;
import org.totemcraft.pow.block.BlockLoader;
import org.totemcraft.pow.group.ItemGroupLoader;
import org.totemcraft.pow.item.ItemLoader;
import org.totemcraft.pow.util.Json;

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
}
