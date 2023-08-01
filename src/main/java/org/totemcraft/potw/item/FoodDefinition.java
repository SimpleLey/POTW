package org.totemcraft.potw.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minecraft.world.food.FoodProperties;

@Data
@NoArgsConstructor
public class FoodDefinition {
    private int nutrition;
    private float saturationMod;
    private boolean meat = false;
    private boolean canAlwaysEat = false;
    private boolean fast = false;

    public FoodProperties build() {
        var builder = new FoodProperties.Builder();
        builder.nutrition(nutrition);
        builder.saturationMod(saturationMod);
        if (meat) builder.meat();
        if (canAlwaysEat) builder.alwaysEat();
        if (fast) builder.fast();

        return builder.build();
    }
}
