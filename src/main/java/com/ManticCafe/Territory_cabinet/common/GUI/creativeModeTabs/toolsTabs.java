package com.ManticCafe.Territory_cabinet.common.GUI.creativeModeTabs;

import com.ManticCafe.Territory_cabinet.common.blocks.blockRegister;
import com.ManticCafe.Territory_cabinet.common.items.itemRegister;
import com.ManticCafe.Territory_cabinet.main;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class toolsTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, main.MODID);

    public static final RegistryObject<CreativeModeTab> Tools_Tab = CREATIVE_MODE_TABS.register("tools_tab",
            () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(itemRegister.FireAxe.get()))
            .title(Component.translatable("itemGroup.tools_tabs"))
            .displayItems((pParameters, pOutput) -> {pOutput.accept(itemRegister.FireAxe.get());
            }).build());

    //注册事件
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
