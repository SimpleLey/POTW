package org.totemcraft.potw;

import lombok.Getter;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.totemcraft.potw.loader.ContentLoader;

@Mod("potw")
public class PicnicOfTheWorld {
    public static final String Id = "potw";

    @Getter(lazy = true)
    private static final String version = ModList.get().getModContainerById("potw").map(it -> it.getModInfo().getVersion().toString()).orElse("1.0.0");

    @Getter
    private static final Logger logger = LogManager.getLogger("PicnicOfTheWorld");

    public PicnicOfTheWorld() {
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        eventBus.addListener(this::clientSetup);

        ContentLoader.loadAllContent();
//        EntityInit.init();
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
