package com.ManticCafe.Territory_cabinet.common.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.ManticCafe.Territory_cabinet.main;

public class itemRegister {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    // register:



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
