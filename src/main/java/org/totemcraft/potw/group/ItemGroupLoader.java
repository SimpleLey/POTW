package org.totemcraft.potw.group;


import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.totemcraft.potw.PicnicOfTheWorld;
import org.totemcraft.potw.item.ItemDefinition;
import org.totemcraft.potw.loader.ContentLoader;
import org.totemcraft.potw.localization.LocalizedComponentContents;
import org.totemcraft.potw.util.Json;

import java.nio.file.Path;
import java.util.*;

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
        var definition = Json.from(input, ItemGroupDefinition.class);

        loadedItems.put(
                definition.getId().getPath(),
                register.register(definition.getId().getPath(), () -> CreativeModeTab.builder()
                        .title(MutableComponent.create(new LocalizedComponentContents(definition.getDisplayName())))
                        .icon(() -> Optional.ofNullable(tabItems.get(definition.getId()))
                                .map(it -> {
                                    if (definition.getIcon() != null) {
                                        var found = it.stream().filter(item -> Objects.equals(ForgeRegistries.ITEMS.getKey(item.asItem()), definition.getIcon())).findFirst();
                                        if (found.isPresent()) return found.get();
                                    }

                                    return !it.isEmpty() ? it.get(it.size() - 1) : null;
                                })
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
