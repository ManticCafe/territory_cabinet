package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiBlockEntity extends BlockEntity {

    private List<BlockPos> structureBlocks = new ArrayList<>();

    public MultiBlockEntity(BlockPos pos, BlockState state) {
        super(MultiblockRegistry.MULTI_BLOCK_ENTITY.get(), pos, state);
    }

    public void setupStructure(Level world, BlockPos pos) {
        clearStructure(world);

        BlockPos[] positions = {
                pos,
                pos.above()
        };

        structureBlocks.addAll(Arrays.asList(positions));

        for (BlockPos structurePos : structureBlocks) {
            if (!structurePos.equals(pos)) {
                world.setBlock(structurePos, MultiblockRegistry.territory_cabinet_part.get().defaultBlockState(), 3);
                BlockEntity be = world.getBlockEntity(structurePos);
                if (be instanceof MultiBlockPartEntity partEntity) {
                    partEntity.setMainBlockPos(pos);
                }
            }
        }
    }

    public void breakStructure(Level world, BlockPos pos) {
        clearStructure(world);
    }

    private void clearStructure(Level world) {
        for (BlockPos structurePos : structureBlocks) {
            if (!structurePos.equals(getBlockPos()) && world.getBlockState(structurePos).getBlock() == MultiblockRegistry.territory_cabinet_part.get()) {
                world.removeBlock(structurePos, false);
            }
        }
        structureBlocks.clear();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ListTag positionsTag = new ListTag();
        for (BlockPos pos : structureBlocks) {
            positionsTag.add(NbtUtils.writeBlockPos(pos));
        }
        tag.put("StructureBlocks", positionsTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        structureBlocks.clear();
        ListTag positionsTag = tag.getList("StructureBlocks", Tag.TAG_COMPOUND);
        for (int i = 0; i < positionsTag.size(); i++) {
            structureBlocks.add(NbtUtils.readBlockPos(positionsTag.getCompound(i)));
        }
    }
}