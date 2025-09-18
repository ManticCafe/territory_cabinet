package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
    // 存储结构中的其他方块位置
    private List<BlockPos> structureBlocks = new ArrayList<>();

    public MultiBlockEntity(BlockPos pos, BlockState state) {
        super(MultiblockRegistry.MULTI_BLOCK_ENTITY.get(), pos, state); // 修正：使用 BlockEntityType 而不是 Block
    }

    public void setupStructure(Level world, BlockPos pos) {
        // 清除旧结构
        clearStructure(world);

        // 定义2x2x1结构
        Direction direction = Direction.NORTH; // 可以根据玩家朝向调整
        BlockPos[] positions = {
                pos,
                pos.relative(direction.getClockWise()),
                pos.above(),
                pos.above().relative(direction.getClockWise())
        };

        // 存储结构方块位置
        structureBlocks.addAll(Arrays.asList(positions));

        // 标记这些位置为结构的一部分
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

    // 保存和加载数据
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        // 保存结构信息
        ListTag positionsTag = new ListTag();
        for (BlockPos pos : structureBlocks) {
            positionsTag.add(NbtUtils.writeBlockPos(pos));
        }
        tag.put("StructureBlocks", positionsTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        // 加载结构信息
        structureBlocks.clear();
        ListTag positionsTag = tag.getList("StructureBlocks", Tag.TAG_COMPOUND);
        for (int i = 0; i < positionsTag.size(); i++) {
            structureBlocks.add(NbtUtils.readBlockPos(positionsTag.getCompound(i)));
        }
    }
}