package com.github.ItzSwirlz.BTEServerSwitcher;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BTEServerSwitcher.MODID, name = BTEServerSwitcher.NAME, version = BTEServerSwitcher.VERSION)
public class BTEServerSwitcher
{
    public static final String MODID = "bteserverswitchermod";
    public static final String NAME = "Build The Earth Server Switcher Mod";
    public static final String VERSION = "0.0.1";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(this);
    	ClientCommandHandler.instance.registerCommand(new BTECommand());
    }
}
