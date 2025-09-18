package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MultiBlockPartEntity extends BlockEntity {
    private BlockPos mainBlockPos;

    public MultiBlockPartEntity(BlockPos pos, BlockState state) {
        super(MultiblockRegistry.MULTI_BLOCK_PART_ENTITY.get(), pos, state);
    }

    public void setMainBlockPos(BlockPos pos) {
        this.mainBlockPos = pos;
        setChanged();
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

    // 移除了 tick 方法
}