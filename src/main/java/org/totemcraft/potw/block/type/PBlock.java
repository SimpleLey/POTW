package org.totemcraft.potw.block.type;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;
import org.totemcraft.potw.block.BlockDefinition;

import java.util.List;

public class PBlock extends Block {
    private BlockDefinition definition;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PBlock(Properties properties, BlockDefinition definition) {
        super(properties);
        this.definition = definition;
        if (definition.getFeature().getFacing()) {
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }
    }

    @Override
    public BlockState rotate(BlockState p_51848_, Rotation p_51849_) {
        return p_51848_.setValue(FACING, p_51849_.rotate(p_51848_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_51845_, Mirror p_51846_) {
        return p_51845_.rotate(p_51846_.getRotation(p_51845_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        if (BlockDefinition.getCurrentDefinition().getFeature().getFacing()) {
            builder.add(FACING);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (definition.getFeature().getFacing()) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
        } else {
            return this.defaultBlockState();
        }
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
