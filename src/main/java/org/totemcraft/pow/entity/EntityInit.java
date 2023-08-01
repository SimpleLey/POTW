package org.totemcraft.pow.entity;

import lombok.Getter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.R;

public class EntityInit {
    @Getter
    private static final DeferredRegister<EntityType<?>> register = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PicnicOfTheWorld.Id);

    public static void init() {
        register.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityType<EntityPuppet>> MushroomMan = register.register(
            "mushroom_man",
            () -> EntityType.Builder.of(EntityPuppet::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(R.location("mushroom_man").toString())
    );
}
