package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlockEntity;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlockPart;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiBlock;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiblockRegistry;


public class MultiBlockPartEntity extends BlockEntity {
    private BlockPos mainBlockPos;

    public MultiBlockPartEntity(BlockPos pos, BlockState state) {
        super(MultiblockRegistry.MULTI_BLOCK_PART_ENTITY.get(), pos, state);
    }

    public void setMainBlockPos(BlockPos pos) {
        this.mainBlockPos = pos;
    }

    public BlockPos getMainBlockPos() {
        return mainBlockPos;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (mainBlockPos != null) {
            tag.put("MainPos", NbtUtils.writeBlockPos(mainBlockPos));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("MainPos")) {
            mainBlockPos = NbtUtils.readBlockPos(tag.getCompound("MainPos"));
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MultiBlockPartEntity entity) {
        if (entity.mainBlockPos != null && level.getGameTime() % 20 == 0) {
            BlockState mainState = level.getBlockState(entity.mainBlockPos);
            if (!mainState.is(MultiblockRegistry.territory_cabinet.get())) {
                level.removeBlock(pos, false);
            }
        }
    }
}