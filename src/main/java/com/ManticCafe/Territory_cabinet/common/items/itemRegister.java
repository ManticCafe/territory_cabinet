package com.ManticCafe.Territory_cabinet.common.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.ManticCafe.Territory_cabinet.main;
import com.ManticCafe.Territory_cabinet.common.blocks.blockRegister;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.Territory_cabinet.common.items.tools.FireAxe;

public class itemRegister {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, main.MODID);

    // register:

    public static final RegistryObject<Item> TerritoryCabinetItem = ITEMS
            .register("territory_cabinet",() -> new BlockItem(
                    blockRegister.territory_cabinet.get(),
                    new Item.Properties()
            ));

    public static final RegistryObject<Item> FireAxe = ITEMS
            .register("fire_axe", () -> new FireAxe(
                    Tiers.NETHERITE,
                    7.0F,
                    -3.2F,
                    new Item.Properties()
                    .durability(3276)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
