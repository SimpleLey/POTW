package org.totemcraft.pow.item;

import lombok.Data;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

@Data
public class SwordDefinition implements Tier {
    private int damage;
    private int uses;
    private float speed;
    private float bonus;
    private int level;
    private int enchantment;

    @Override
    public float getAttackDamageBonus() {
        return bonus;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantment;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
