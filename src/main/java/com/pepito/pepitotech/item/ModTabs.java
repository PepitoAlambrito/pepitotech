package com.pepito.pepitotech.item;

import com.pepito.pepitotech.Pepitotech;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab>  CR_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pepitotech.MODID);

    public static final Supplier<CreativeModeTab> PEPITOTECH_TAB = CR_MODE_TABS.register("pepitotech_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.GOLD_COIN.get()))
            .title(Component.translatable("creativetab.pepitotech.pepitotech_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.GOLD_COIN);
                output.accept(ModItems.IRON_COIN);
                output.accept(ModItems.COPPER_GRAVEL);
                output.accept(ModItems.IRON_GRAVEL);
                output.accept(ModItems.COPPER_HAMMER);
                output.accept(ModItems.IRON_HAMMER);
            })
            .build());

    public static void register(IEventBus eventBus) {
        CR_MODE_TABS.register(eventBus);
    }

}
