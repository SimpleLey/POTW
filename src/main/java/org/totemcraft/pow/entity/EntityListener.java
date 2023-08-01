package org.totemcraft.pow.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.pow.PicnicOfTheWorld;

@Mod.EventBusSubscriber(modid = PicnicOfTheWorld.Id, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityListener {
//    @SubscribeEvent
//    public static void entityAttributes(EntityAttributeCreationEvent event) {
//        for (RegistryObject<EntityType<?>> entry : EntityInit.getRegister().getEntries()) {
//            event.put((EntityType<? extends LivingEntity>) entry.get(), EntityPuppet.createAttributes().build());
//        }
//    }
}
