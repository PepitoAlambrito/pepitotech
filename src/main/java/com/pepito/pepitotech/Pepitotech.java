package com.pepito.pepitotech;

import com.google.common.eventbus.Subscribe;
import com.pepito.pepitotech.item.ModItems;
import com.pepito.pepitotech.item.ModTabs;
import com.pepito.pepitotech.item.HammerEvents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Pepitotech.MODID)
public class Pepitotech {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "pepitotech";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final boolean POOPOO_PEEPEE = true;
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Pepitotech(IEventBus modEventBus, ModContainer modContainer) {
        // poo poo pee pee, boiii this so tuff
        // sorry mi englizh iss badidi
        // jk
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModTabs.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new HammerEvents());
        modEventBus.addListener(this::addCreative);
        LOGGER.info(MODID + " is loading!...");
        if(POOPOO_PEEPEE){
            LOGGER.debug("I did poo poo pee pee");
        } else {
            LOGGER.debug("I didnt poo poo pee pee");
        }

    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event){

    }
}
