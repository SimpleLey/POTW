package org.totemcraft.potw.block.type;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.totemcraft.potw.block.BlockDefinition;

import java.util.List;

public class PBlockItem extends BlockItem {
    private BlockDefinition definition;

    public PBlockItem(Block block, Properties properties, BlockDefinition definition) {
        super(block, properties);
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
