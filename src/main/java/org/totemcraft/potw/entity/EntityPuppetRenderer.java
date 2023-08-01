package org.totemcraft.potw.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EntityPuppetRenderer extends MobRenderer<EntityPuppet, EntityModel<EntityPuppet>> {
    private ResourceLocation texture;

    public EntityPuppetRenderer(EntityRendererProvider.Context context, EntityModel<EntityPuppet> model, float shadow, ResourceLocation texture) {
        super(context, model, shadow);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPuppet p_114482_) {
        return texture;
    }
}
