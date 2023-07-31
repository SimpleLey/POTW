package org.totemcraft.pow.client;

import net.minecraft.client.renderer.block.model.BlockModel;

public interface LayerBlockModelGenerator {
    static BlockModel from(String layer0) {
        return BlockModel.fromString("""
                {
                  "parent": "minecraft:item/generated",
                  "textures": {
                    "layer0": "%s"
                  }
                }
                """.formatted(layer0));
    }
}
