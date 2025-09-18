package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlockEntity;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiblockRegistry;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlockPart;


public class MultiBlock extends Block implements EntityBlock {

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public MultiBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MultiBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);

        if (!world.isClientSide) {
            // 获取方块实体
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof MultiBlockEntity multiBlockEntity) {
                // 设置多方块结构
                multiBlockEntity.setupStructure(world, pos);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!world.isClientSide) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof MultiBlockEntity multiBlockEntity) {
                multiBlockEntity.breakStructure(world, pos);
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }
}