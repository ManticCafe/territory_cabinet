package com.ManticCafe.Territory_cabinet.common.blocks.multiblock.territory_cabinet;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HighTopBlock extends Block {
    public HighTopBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        // 破坏顶部方块时同时破坏基础方块
        if (!world.isClientSide /*&& !player.isCreative()*/) {
            world.destroyBlock(pos.below(), true);
        }
        super.playerWillDestroy(world, pos, state, player);
    }
}