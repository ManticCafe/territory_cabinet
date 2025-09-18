package com.ManticCafe.Territory_cabinet;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import com.ManticCafe.Territory_cabinet.common.blocks.blockRegister;
import com.ManticCafe.Territory_cabinet.common.items.itemRegister;
import com.ManticCafe.Territory_cabinet.common.GUI.creativeModeTabs.functionBlockTabs;
import com.ManticCafe.Territory_cabinet.common.multiblock.MultiblockRegistry;

@Mod(main.MODID)
public class main {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "territory_cabinet";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // 主函数
    public main(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        System.out.println("Starting mod initialization...");

        modEventBus.addListener(this::onCommonSetup);

        // 方块注册
        blockRegister.register(modEventBus);
        // 物品注册
        itemRegister.register(modEventBus);
        // 多方块注册
        MultiblockRegistry.register(modEventBus);
        // 功能方块物品栏注册
        functionBlockTabs.register(modEventBus);
    }


    private void onCommonSetup(final FMLCommonSetupEvent event) {
        // 这里可以放置需要在模组初始化时执行的代码
        LOGGER.info("Territory Cabinet mod common setup complete");
    }
}