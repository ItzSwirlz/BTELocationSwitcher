package com.github.ItzSwirlz.BTELocationSwitcher;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BTELocationSwitcher.MODID, name = BTELocationSwitcher.NAME, version = BTELocationSwitcher.VERSION)
public class BTELocationSwitcher
{
    public static final String MODID = "btelocationswitcher";
    public static final String NAME = "Build The Earth Location Switcher";
    public static final String VERSION = "1.0.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(this);
    	ClientCommandHandler.instance.registerCommand(new BTECommand());
    }
}
