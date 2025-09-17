package com.ManticCafe.Territory_cabinet.common.GUI.creativeModeTabs;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import com.ManticCafe.Territory_cabinet.main;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.Territory_cabinet.common.blocks.blockRegister;
import com.ManticCafe.Territory_cabinet.common.items.itemRegister;



public class functionBlockTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,main.MODID);

    public static final RegistryObject<CreativeModeTab> Mod_Tab = CREATIVE_MODE_TABS.register("function_block_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(blockRegister.territory_cabinet.get())).title(Component.translatable("itemGroup.function_blocks"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(blockRegister.territory_cabinet.get());
            })
            .build());

    //注册事件
    public  static void register() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        CREATIVE_MODE_TABS.register(bus);
    }
}
