package com.ManticCafe.Territory_cabinet.common.blocks.multiblock.territory_cabinet;

import com.ManticCafe.Territory_cabinet.common.blocks.blockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import com.ManticCafe.Territory_cabinet.common.blocks.multiblock.territory_cabinet.HighTopBlock;
import com.ManticCafe.Territory_cabinet.common.blocks.blockRegister;

import javax.annotation.Nullable;

public class  BaseBlock extends Block {
    public  BaseBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (!world.isClientSide) {
            // 自动在上方放置顶部方块
            world.setBlockAndUpdate(pos.above(), blockRegister.territory_cabinet_high_top.get().defaultBlockState());
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        // 破坏基础方块时同时破坏顶部方块
        if (!world.isClientSide /*&& !player.isCreative()*/) {
            world.destroyBlock(pos.above(), true);
        }
        super.playerWillDestroy(world, pos, state, player);
    }
}