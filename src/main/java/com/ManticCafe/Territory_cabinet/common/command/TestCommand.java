// 创建一个简单的命令类
package com.ManticCafe.Territory_cabinet.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiblockRegistry;

public class TestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("testmultiblock")
                .executes(TestCommand::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        source.sendSuccess(() -> Component.literal("检查多方块注册状态:"), false);
        source.sendSuccess(() -> Component.literal("territory_cabinet: " + MultiblockRegistry.territory_cabinet.isPresent()), false);
        source.sendSuccess(() -> Component.literal("territory_cabinet_part: " + MultiblockRegistry.territory_cabinet_part.isPresent()), false);
        source.sendSuccess(() -> Component.literal("MULTI_BLOCK_ENTITY: " + MultiblockRegistry.MULTI_BLOCK_ENTITY.isPresent()), false);
        source.sendSuccess(() -> Component.literal("MULTI_BLOCK_PART_ENTITY: " + MultiblockRegistry.MULTI_BLOCK_PART_ENTITY.isPresent()), false);

        return 1;
    }
}