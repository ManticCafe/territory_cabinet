package com.ManticCafe.Territory_cabinet.common.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.Territory_cabinet.main;

public class blockRegister {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    // register:

//    public static final RegistryObject<Block> territory_cabinet = BLOCKS
//            .register("territory_cabinet",() -> new Block(BlockBehaviour.Properties.of().strength(5.0f).sound(SoundType.METAL)));
//
//    public static final RegistryObject<Item> TerritoryCabinetItem = ITEMS
//            .register("territory_cabinet",() -> new BlockItem(territory_cabinet.get(),new Item.Properties()));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

}
