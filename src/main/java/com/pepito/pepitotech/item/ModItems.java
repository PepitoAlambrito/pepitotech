package com.pepito.pepitotech.item;

import com.pepito.pepitotech.Pepitotech;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Pepitotech.MODID);

    // MISC
    public static final DeferredItem<Item> GOLD_COIN = ITEMS.registerSimpleItem("gold_coin");
    public static final DeferredItem<Item> IRON_COIN = ITEMS.registerSimpleItem("iron_coin");
    public static final DeferredItem<Item> COPPER_GRAVEL = ITEMS.registerSimpleItem("copper_gravel");
    public static final DeferredItem<Item> IRON_GRAVEL = ITEMS.registerSimpleItem("iron_gravel");

    // TOOLS
    public static final DeferredItem<HammerItem> COPPER_HAMMER = ITEMS.registerItem(
            "copper_hammer", properties -> new HammerItem(ToolMaterial.STONE, 64, properties));
    public static final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.registerItem(
            "iron_hammer", properties -> new HammerItem(ToolMaterial.IRON, 256, properties));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
