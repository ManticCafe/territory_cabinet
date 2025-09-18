package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlockEntity;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiblockRegistry;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlock;

import javax.annotation.Nullable;


public class MultiBlockPart extends Block implements EntityBlock {
    public MultiBlockPart(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MultiBlockPartEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (type == MultiblockRegistry.MULTI_BLOCK_PART_ENTITY.get()) {
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof MultiBlockPartEntity partEntity) {
                    MultiBlockPartEntity.tick(lvl, pos, st, partEntity);
                }
            };
        }
        return null;
    }

    @Nullable
    private static <T extends BlockEntity> BlockEntityTicker<T> createTickerHelper(BlockEntityType<T> type, BlockEntityType<?> targetType, BlockEntityTicker<?> ticker) {
        return targetType == type ? (BlockEntityTicker<T>) ticker : null;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof MultiBlockPartEntity partEntity) {
            BlockPos mainPos = partEntity.getMainBlockPos();
            if (mainPos != null) {
                BlockEntity mainBe = world.getBlockEntity(mainPos);
                if (mainBe instanceof MultiBlockEntity multiBlockEntity) {
                    multiBlockEntity.breakStructure(world, mainPos);
                    world.removeBlock(mainPos, false);
                }
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }
}