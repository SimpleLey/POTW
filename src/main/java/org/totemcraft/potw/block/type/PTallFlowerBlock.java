package org.totemcraft.potw.block.type;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TallFlowerBlock;
import org.jetbrains.annotations.Nullable;
import org.totemcraft.potw.block.BlockDefinition;

import java.util.List;

public class PTallFlowerBlock extends TallFlowerBlock {
    private BlockDefinition definition;

    public PTallFlowerBlock(Properties properties, BlockDefinition definition) {
        super(properties);
        this.definition = definition;
    }

    @Override
    public MutableComponent getName() {
        var name = definition.getDisplayName().getSingleString();
        if (name == null) return super.getName();
        return Component.literal(name);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter level, List<Component> components, TooltipFlag flag) {
        if (definition.getLores() != null) {
            for (String currentString : definition.getLores().getCurrentStrings()) {
                components.add(Component.literal(currentString));
            }
        }
    }
}
