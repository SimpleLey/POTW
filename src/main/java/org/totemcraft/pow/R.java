package org.totemcraft.pow;

import lombok.SneakyThrows;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;

public interface R {
    Path RootPath = rootPath();

    @SneakyThrows
    static Path rootPath() {
        // 获取jar包目录
        var url = R.class.getResource("/definition");
        assert url != null;
        return Path.of(url.toURI());
    }

    static ResourceLocation location(String path) {
        var index = path.indexOf(':');
        if (index == -1) {
            return new ResourceLocation(PicnicOfTheWorld.Id, path);
        } else {
            return new ResourceLocation(path.substring(0, index), path.substring(index + 1));
        }
    }
}
