package org.totemcraft.pow.group;


import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.item.ItemDefinition;
import org.totemcraft.pow.loader.ContentLoader;
import org.totemcraft.pow.util.Json;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = PicnicOfTheWorld.Id, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public enum ItemGroupLoader implements ContentLoader<CreativeModeTab> {
    INSTANCE;

    private final DeferredRegister<CreativeModeTab> register = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PicnicOfTheWorld.Id);
    private Map<String, RegistryObject<CreativeModeTab>> loadedItems = new HashMap<>();
    private Map<ResourceLocation, List<ItemLike>> tabItems = new HashMap<>();

    ItemGroupLoader() {
        register.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public void addTabItem(ResourceLocation tab, ItemLike item) {
        tabItems.computeIfAbsent(tab, k -> Lists.newArrayList()).add(item);
    }

    @Override
    public List<Path> contentDirs(Path root) {
        return List.of(root.resolve("creativeTab"));
    }

    @Override
    public CreativeModeTab getLoaded(String id) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.get(new ResourceLocation(id));
    }

    @Override
    public void load(JsonElement input) {
        var definition = Json.from(input, ItemDefinition.class);

        loadedItems.put(
                definition.getId().getPath(),
                register.register(definition.getId().getPath(), () -> CreativeModeTab.builder()
                        .title(Component.translatable(""))
                        .icon(() -> Optional.ofNullable(tabItems.get(definition.getId()))
                                .map(it -> !it.isEmpty() ? it.get(0) : null)
                                .map(it -> new ItemStack(it.asItem(), 1))
                                .orElse(null))
                        .withSearchBar()
                        .build())
        );
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        var items = INSTANCE.tabItems.get(event.getTabKey().location());
        if (items != null) {
            items.forEach(event::accept);
        }
    }
}
