package org.totemcraft.pow.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.totemcraft.pow.R;
import org.totemcraft.pow.group.ItemGroupLoader;
import org.totemcraft.pow.localization.LocalizationStrings;

@Data
@NoArgsConstructor
public class ItemDefinition {
    private ResourceLocation id;
    private LocalizationStrings displayName;
    private ResourceLocation group = R.location("items");

    private byte maxStackSize = 64;
    private int durability = 0;
    private int defaultDurability = durability;
    private Rarity rarity = Rarity.COMMON;
    private FoodDefinition food;
    private LocalizationStrings lores;
    private ItemFeature feature;

    public Item build() {
        var properties = new Item.Properties()
                .durability(durability)
                .defaultDurability(defaultDurability)
                .stacksTo(maxStackSize)
                .rarity(rarity);

        if (food != null) properties.food(food.build());

        var item = new PItem(properties, this);

        if (group != null) {
            ItemGroupLoader.INSTANCE.addTabItem(group, item);
        }

        return item;
    }
}
