package org.totemcraft.potw.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PBowItem extends BowItem {
    private ItemDefinition definition;

    public PBowItem(Properties properties, ItemDefinition definition) {
        super(properties);
        this.definition = definition;

        if (FMLLoader.getDist().isClient()) {
            ItemPropertiesRegister.registerBow(this);
        }
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
