package org.totemcraft.potw.block;

import lombok.Data;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.totemcraft.potw.R;
import org.totemcraft.potw.block.type.*;
import org.totemcraft.potw.group.ItemGroupLoader;
import org.totemcraft.potw.localization.LocalizationStrings;

@Data
public class BlockDefinition {
    private ResourceLocation id;
    private String type = "block";
    private LocalizationStrings displayName;
    private ResourceLocation group = R.location("blocks");
    private LocalizationStrings lores;

    private BlockSound sound;
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
    private boolean collission = true;
    private boolean occlusion = true;
    private boolean replaceable = false;

    private BlockFeature feature = new BlockFeature();

    @Getter
    private static BlockDefinition currentDefinition;

    public Block build() {
        currentDefinition = this;

        var properties = BlockBehaviour.Properties.of();

        if (type.equalsIgnoreCase("flower")) {
            properties.mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY);
        } else if (type.equalsIgnoreCase("vine")) {
            properties.mapColor(MapColor.PLANT).replaceable().noCollission().randomTicks().strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY);
        } else if (type.equalsIgnoreCase("grass")) {
            properties.mapColor(MapColor.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS);
        } else if (type.equalsIgnoreCase("tall_flower")) {
            properties.mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY);
        } else if (type.equalsIgnoreCase("tall_grass")) {
            properties.mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY);
        }

        if (sound != null) properties.sound(sound.getSoundType());

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
        if (!collission) properties.noCollission();
        if (!occlusion) properties.noOcclusion();
        if (replaceable) properties.replaceable();

        if (light != null) properties.lightLevel(state -> light);

        Block block;
        if (type.equalsIgnoreCase("flower")) {
            block = new PFlowerBlock(properties, this);
        } else if (type.equalsIgnoreCase("grass")) {
            block = new PGrassBlock(properties, this);
        } else if (type.equalsIgnoreCase("tall_flower")) {
            block = new PTallFlowerBlock(properties, this);
        } else if (type.equalsIgnoreCase("tall_grass")) {
            block = new PTallGrassBlock(properties, this);
        } else if (type.equalsIgnoreCase("transparent")) {
            block = new PTransparentBlock(properties, this);
        }  else if (type.equalsIgnoreCase("carpet")) {
            block = new PCarpetBlock(properties, this);
        }  else if (type.equalsIgnoreCase("vine")) {
            block = new PCarpetBlock(properties, this);
        } else {
            block = new PBlock(properties, this);
        }

        if (group != null) {
            ItemGroupLoader.INSTANCE.addTabItem(group, block);
        }

        return block;
    }
}
