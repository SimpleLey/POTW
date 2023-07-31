package org.totemcraft.pow.block;

import lombok.Data;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.totemcraft.pow.R;
import org.totemcraft.pow.block.type.PBlock;
import org.totemcraft.pow.block.type.PFlowerBlock;
import org.totemcraft.pow.block.type.PTallGrassBlock;
import org.totemcraft.pow.group.ItemGroupLoader;
import org.totemcraft.pow.localization.LocalizationStrings;

@Data
public class BlockDefinition {
    private ResourceLocation id;
    private String type = "block";
    private LocalizationStrings displayName;
    private ResourceLocation group = R.location("blocks");
    private LocalizationStrings lores;

    private BlockSound sound = BlockSound.STONE;
    private DyeColor dyeColor;
    private MapColor mapColor;
    private BlockBehaviour.OffsetType offsetType;
    private PushReaction pushReaction;

    private Float destroyTime;
    private Float friction;
    private Float speedFactor;
    private Float jumpFactor;
    private Float explosionResistance;

    private Integer light;

    private boolean dynamicShape = false;
    private boolean forceSolidOn = false;
    private boolean ignitedByLava = false;
    private boolean air = false;
    private boolean randomTicks = false;
    private boolean instabreak = false;
    private boolean particlesOnBreak = true;
    private boolean collission = false;
    private boolean occlusion = false;
    private boolean replaceable = false;

    private BlockFeature feature = new BlockFeature();

    public Block build() {
        var properties = BlockBehaviour.Properties.of();
        properties.sound(sound.getSoundType());

        if (mapColor != null) properties.mapColor(mapColor);
        if (dyeColor != null) properties.mapColor(dyeColor);
        if (destroyTime != null) properties.destroyTime(destroyTime);
        if (friction != null) properties.friction(friction);
        if (speedFactor != null) properties.speedFactor(speedFactor);
        if (jumpFactor != null) properties.jumpFactor(jumpFactor);
        if (explosionResistance != null) properties.explosionResistance(explosionResistance);
        if (offsetType != null) properties.offsetType(offsetType);
        if (pushReaction != null) properties.pushReaction(pushReaction);

        if (dynamicShape) properties.dynamicShape();
        if (forceSolidOn) properties.forceSolidOn();
        if (ignitedByLava) properties.ignitedByLava();
        if (air) properties.air();
        if (randomTicks) properties.randomTicks();
        if (instabreak) properties.instabreak();
        if (!particlesOnBreak) properties.noParticlesOnBreak();
        if (collission) properties.noCollission();
        if (occlusion) properties.noOcclusion();
        if (replaceable) properties.replaceable();

        if (light != null) properties.lightLevel(state -> light);

        Block block;
        if (type.equalsIgnoreCase("flower")) {
            block = new PFlowerBlock(properties, this);
        } else if (type.equalsIgnoreCase("tall_grass")) {
            block = new PTallGrassBlock(properties, this);
        } else {
            block = new PBlock(properties, this);
        }

        if (group != null) {
            ItemGroupLoader.INSTANCE.addTabItem(group, block);
        }

        return block;
    }
}
