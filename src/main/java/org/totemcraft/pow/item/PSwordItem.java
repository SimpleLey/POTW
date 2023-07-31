package org.totemcraft.pow.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class PSwordItem extends SwordItem {
    private ItemDefinition definition;

    public PSwordItem(Properties properties, ItemDefinition definition) {
        super(Optional.ofNullable((Tier) definition.getTier()).orElse(definition.getSword()), definition.getSword().getDamage(), -2.4F, properties);
        this.definition = definition;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        var name = definition.getDisplayName().getSingleString();
        if (name == null) {
            return super.getName(itemStack);
        }
        return Component.literal(name);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (definition.getLores() != null) {
            for (String currentString : definition.getLores().getCurrentStrings()) {
                components.add(Component.literal(currentString));
            }
        }
    }
}
