package org.totemcraft.pow;

import it.unimi.dsi.fastutil.Pair;
import lombok.Getter;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.totemcraft.pow.block.BlockDefinition;
import org.totemcraft.pow.block.BlockLoader;
import org.totemcraft.pow.group.ItemGroupLoader;
import org.totemcraft.pow.item.ItemLoader;
import org.totemcraft.pow.loader.ContentLoader;

import java.util.Map;

@Mod("pow")
public class PicnicOfTheWorld {
    public static final String Id = "pow";

    @Getter(lazy = true)
    private static final String version = ModList.get().getModContainerById("pow").map(it -> it.getModInfo().getVersion().toString()).orElse("1.0.0");

    @Getter
    private static final Logger logger = LogManager.getLogger("PicnicOfTheWorld");

    public PicnicOfTheWorld() {
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        eventBus.addListener(this::clientSetup);

        ContentLoader.loadAllContent();
    }

//    public void clientSetup(FMLClientSetupEvent event) {
//        for (Map.Entry<String, Pair<BlockDefinition, RegistryObject<Block>>> entry : BlockLoader.INSTANCE.getLoaded().entrySet()) {
//            var renderType = entry.getValue().first().getFeature().getRenderType();
//            if (renderType != null) {
//                ItemBlockRenderTypes.setRenderLayer(entry.getValue().second().get(), renderType);
//            }
//        }
//    }
}
