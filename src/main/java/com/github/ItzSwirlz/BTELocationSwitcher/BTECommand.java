package com.github.ItzSwirlz.BTELocationSwitcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.json.simple.parser.ParseException;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class BTECommand extends CommandBase {

	// Alias's for the server names people can go by
	// This shows in the tab
	public String[] acceptableIps = {
			"bte",
            "germany",
            "nj",
            "southwest_us",
            "us_southwest",
            "southwest",
            "nordic",
            "baltic",
            "midwest_us",
            "us_midwest",
            "midwest",
            "northeast_us",
            "us_northeast",
            "northeast",
            "alps",
            "montreal",
            "canada",
            "russia",
            "cis",
            "southeast_us",
            "us_southeast",
            "southeast",
            "carolinas_us",
            "us_carolinas",
            "carolinas",
            "japan",
            "argentina",
            "chile",
            "hong_kong",
            "macau",
            "hk_mu",
            "hkmu",
            "israel",
            "israel_wb",
            "wb",
            "taiwan",
            "controversy",
            "france"};

	@Override
	public List<String> getAliases() {
		return Arrays.asList("buildtheearth");
	}

	@Override
	public String getName() {
		return "bte";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/bte <server>";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		String serverIp = "";

		try {
			serverIp = BTEServerIPGetter.getIp(args[0].toLowerCase());
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		// I would not know how to do this, thank you KaiKikuchi for saving my time.
		// https://github.com/KaiKikuchi/ServerRedirect/tree/forge1.12.2
		final Minecraft mc = Minecraft.getMinecraft();
		
		mc.world.sendQuittingDisconnectingPacket();
		mc.loadWorld((WorldClient) null);
		
		mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
		mc.displayGuiScreen(new GuiConnecting(mc.currentScreen, mc, new ServerData("ServerRedirect", serverIp, false)));
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, acceptableIps) : Collections.emptyList();
    }

}
