package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MultiBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL; // Changed to MODEL for opaque rendering
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);

        BlockPos abovePos = pos.above();
        BlockState aboveState = world.getBlockState(abovePos);

        if (!aboveState.canBeReplaced() || aboveState.liquid()) {
            world.removeBlock(pos, false);
            if (!world.isClientSide && placer instanceof Player player) {
                player.displayClientMessage(Component.literal("空间不足，无法放置"), true);
                popResource(world, pos, new ItemStack(this));
            }
            return;
        }

        if (!world.isClientSide) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof MultiBlockEntity multiBlockEntity) {
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

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, world, pos, block, fromPos, isMoving);

        if (world.isClientSide) {
            BlockPos abovePos = pos.above();
            BlockState aboveState = world.getBlockState(abovePos);

            if (!aboveState.canBeReplaced() || aboveState.liquid()) {
                world.addParticle(ParticleTypes.SMOKE,
                        abovePos.getX() + 0.5, abovePos.getY() + 0.5, abovePos.getZ() + 0.5,
                        0, 0, 0);
            }
        }
    }
}