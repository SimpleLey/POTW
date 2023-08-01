package org.totemcraft.pow.entity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.R;
import org.totemcraft.pow.entity.model.ModelMushroomMan;

@Mod.EventBusSubscriber(modid = PicnicOfTheWorld.Id, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityModelRegister {
//    @SubscribeEvent
//    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerEntityRenderer(EntityInit.MushroomMan.get(), context -> new EntityPuppetRenderer(context, new ModelMushroomMan<>(context.bakeLayer(ModelMushroomMan.LAYER_LOCATION)), 1.0F, R.location("textures/entity/mushroom_man.png")));
//    }
//
//    @SubscribeEvent
//    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(ModelMushroomMan.LAYER_LOCATION, ModelMushroomMan::createBodyLayer);
//    }
}