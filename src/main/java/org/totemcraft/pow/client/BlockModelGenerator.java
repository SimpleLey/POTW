package org.totemcraft.pow.client;

import net.minecraft.client.renderer.block.model.BlockModel;

public interface BlockModelGenerator {
    static BlockModel layer(String layer0) {
        return BlockModel.fromString("""
                {
                  "parent": "minecraft:item/generated",
                  "textures": {
                    "layer0": "%s"
                  }
                }
                """.formatted(layer0));
    }

    static BlockModel parent(String parent) {
        return BlockModel.fromString("""
                {
                  "parent": "%s"
                }
                """.formatted(parent));
    }
}
