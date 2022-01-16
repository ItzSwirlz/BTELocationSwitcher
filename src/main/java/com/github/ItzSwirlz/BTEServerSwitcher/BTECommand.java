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
		switch(args[0].toLowerCase()) {
			case "":
				serverIp = "buildtheearth.net";
				serverName = "Build The Earth";
				break;
			case "minefact":
			case "ny":
			case "nyc":
				serverIp = "minefact.de";
				serverName = "New York - MineFact Network";
				break;
			case "germany":
				serverIp = "bte-germany.de";
				serverName = "Build The Earth Germany";
				break;
			case "nj":
				serverIp = "hub.andromedacraft.com";
				serverName = "1:1 New Jersey";
				break;
			case "nordic":
			case "baltic":
				serverIp = "nordicbalticbte.net";
				serverName = "Build The Earth Nordic + Baltic";
				break;
		}

		sender.sendMessage((new TextComponentString("Connecting to the ")).appendText(serverName + " server."));
		
		// I would not know how to do this, thank you KaiKikuchi for saving my time.
		// https://github.com/KaiKikuchi/ServerRedirect/tree/forge1.12.2
		final Minecraft mc = Minecraft.getMinecraft();
		
		mc.world.sendQuittingDisconnectingPacket();
		mc.loadWorld((WorldClient) null);
		
		mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
		mc.displayGuiScreen(new GuiConnecting(mc.currentScreen, mc, new ServerData("ServerRedirect", serverIp, false)));
	}

}
