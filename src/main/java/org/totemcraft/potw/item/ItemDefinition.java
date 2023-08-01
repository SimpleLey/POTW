package org.totemcraft.potw.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import org.totemcraft.potw.R;
import org.totemcraft.potw.group.ItemGroupLoader;
import org.totemcraft.potw.localization.LocalizationStrings;

@Data
@NoArgsConstructor
public class ItemDefinition {
    private ResourceLocation id;
    private String type = "item";
    private LocalizationStrings displayName;
    private ResourceLocation group = R.location("items");

    private byte maxStackSize = 64;
    private int durability = 0;
    private int defaultDurability = durability;
    private Rarity rarity = Rarity.COMMON;
    private FoodDefinition food;
    private LocalizationStrings lores;
    private SwordDefinition sword = new SwordDefinition();
    private Tiers tier;
    private ItemFeature feature = new ItemFeature();

    public Item build() {
        var properties = new Item.Properties()
                .durability(durability)
                .defaultDurability(defaultDurability)
                .stacksTo(maxStackSize)
                .rarity(rarity);

        if (food != null) properties.food(food.build());

        Item item;
        if (type.equals("bow")) {
            item = new PBowItem(properties, this);
        } else if (type.equalsIgnoreCase("sword")) {
            item = new PSwordItem(properties, this);
        } else {
            item = new PItem(properties, this);
        }

        if (group != null) {
            ItemGroupLoader.INSTANCE.addTabItem(group, item);
        }

        return item;
    }
}
