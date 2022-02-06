package com.github.ItzSwirlz.BTEServerSwitcher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class BTECommand extends CommandBase {
	
	@Override
	public String getName() {
		return "bte";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/bte [server]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		String serverIp = "";
		String serverName = "";

		// TODO: Always check, improve and add servers as they come and go.
		if(args.length == 0) {
			serverIp = "buildtheearth.net";
			serverName = "Build The Earth";
		} else {
			switch(args[0].toLowerCase()) {
				case "":
					serverIp = "buildtheearth.net";
					serverName = "Build The Earth";
					break;
				case "germany":
					serverIp = "bte-germany.de";
					serverName = "Build The Earth Germany";
					break;
				case "nj":
					serverIp = "hub.andromedacraft.com";
					serverName = "1:1 New Jersey";
					break;
				case "southwest_us":
				case "us_southwest":
				case "southwest":
					serverIp = "207.244.229.33:25565";
					serverName = "BTE Southwest US";
					break;
				case "nordic":
				case "baltic":
					serverIp = "nordicbalticbte.net";
					serverName = "Build The Earth Nordic + Baltic";
					break;
				case "midwest_us":
				case "us_midwest":
				case "midwest":
					serverIp = "midwestbte.net";
					serverName = "BTE Midwest";
					break;
				case "northeast_us":
				case "us_northeast":
				case "northeast":
					serverIp = "play.btene.com";
					serverName = "BTE Northeast USA";
					break;
				case "alps":
					serverIp = "mc.alps-bte.com";
					serverName = "Alps BTE (AT/CH/LI)";
					break;
				case "montreal":
				case "canada":
					serverIp = "btecanada.net";
					serverName = "BTE Canada";
					break;
				case "russia":
				case "cis":
					serverIp = "BuildTheEarth.ru";
					serverName = "TeamCIS";
					break;
				case "southeast_us":
				case "us_southeast":
				case "southeast":
					serverIp = "207.244.229.33:25567";
					serverName = "BTE Southeast US";
					break;
				case "carolinas_us":
				case "us_carolinas":
				case "carolinas":
					serverIp = "64.139.238.70:25565";
					serverName = "BTE Carolinas";
					break;
				case "japan":
					serverIp = "btejp.net";
					serverName = "BTE Japan";
					break;
				case "argentina":
				case "chile":
					serverIp = "bteconosur.com";
					serverName = "BuildTheEarth - Southern Cone";
					break;
				case "hong_kong":
				case "macau":
				case "hk_mu":
				case "hkmu":
					serverIp = "play.btehkmu.com";
					serverName = "Team HK-MU";
					break;
				case "wb":
				case "israel_wb":
				case "israel":
					serverIp = "play.bteisrael.online";
					serverName = "Israel & The West Bank";
					break;
				case "taiwan":
					serverIp = "btetw.duckarmada.com";
					serverName = "BTE Taiwan";
					break;
				case "controversy":
					serverIp = "teams.buildtheearth.net:25503";
					serverName = "BTE Controversy";
					break;
				case "france":
					serverIp = "bte.thesmyler.fr";
					serverName = "BTE France";
					break;
				default:
					serverIp = "buildtheearth.net";
					serverName = "Build The Earth";
					break;
			}
		}

		sender.sendMessage((new TextComponentString("Connecting to the ")).appendText(serverName + " server..."));
		
		// I would not know how to do this, thank you KaiKikuchi for saving my time.
		// https://github.com/KaiKikuchi/ServerRedirect/tree/forge1.12.2
		final Minecraft mc = Minecraft.getMinecraft();
		
		mc.world.sendQuittingDisconnectingPacket();
		mc.loadWorld((WorldClient) null);
		
		mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
		mc.displayGuiScreen(new GuiConnecting(mc.currentScreen, mc, new ServerData("ServerRedirect", serverIp, false)));
	}

}
