package org.totemcraft.pow.block;

import lombok.Data;
import net.minecraft.resources.ResourceLocation;

@Data
public class BlockFeature {
    private ResourceLocation model;
    private ResourceLocation layer;
    private boolean facing;
    private String renderType;

    public boolean getFacing() {
        return facing;
    }
}
