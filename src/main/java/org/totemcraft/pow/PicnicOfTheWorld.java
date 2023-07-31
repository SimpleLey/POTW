package org.totemcraft.pow;

import lombok.Getter;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.totemcraft.pow.group.ItemGroupLoader;
import org.totemcraft.pow.item.ItemLoader;
import org.totemcraft.pow.loader.ContentLoader;

@Mod("pow")
public class PicnicOfTheWorld {
    public static final String Id = "pow";

    @Getter(lazy = true)
    private static final String version = ModList.get().getModContainerById("pow").map(it -> it.getModInfo().getVersion().toString()).orElse("1.0.0");

    @Getter
    private static final Logger logger = LogManager.getLogger("PicnicOfTheWorld");

    public PicnicOfTheWorld() {
        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::init);
        eventBus.addListener(this::post);

        ContentLoader.loadAllContent();
    }

    public void init(FMLCommonSetupEvent event) {
    }

    public void post(FMLLoadCompleteEvent event) {

    }
}
