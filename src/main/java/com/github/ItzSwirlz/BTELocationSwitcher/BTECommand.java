package com.github.ItzSwirlz.BTELocationSwitcher;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;


public class BTECommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		//dispatcher.register(literal("bte")
		//		.then(argument("server", greedyString())
		//		.executes((ctx -> run(ctx.getSource(), getString(ctx, "server"))))));
		dispatcher.register(literal("bte").executes(ctx -> run(ctx.getSource(), "bte.net")));
	}
	
	@Environment(EnvType.CLIENT)
	public static int run(ServerCommandSource source, String server) {
		//String[] args = server.split(" ");
		String serverIp = "";
		String serverName = "";

		// TODO: Always check, improve and add servers as they come and go.
		/*if(args.length == 0) {
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
		}*/

		//source.sendFeedback((Text.of(("Connecting to the " + serverName + " server..."))), false);
		
		// I would not know how to do this, thank you KaiKikuchi for saving my time.
		// https://github.com/KaiKikuchi/ServerRedirect/blob/fabric1.18.1/
		
		if (!MinecraftClient.getInstance().isOnThread()) {
			throw new IllegalStateException("Not in the main thread");
		}

		final MinecraftClient mc = MinecraftClient.getInstance();
		
		ClientPlayerInteractionManager manager = new ClientPlayerInteractionManager(mc, null);
		mc.interactionManager = manager;
		
		if (mc.world != null) {
			mc.world.disconnect();
		}

		mc.disconnect();
		
		mc.setScreen(new MultiplayerScreen(new TitleScreen()));
		ConnectScreen.connect(mc.currentScreen, mc, ServerAddress.parse("buildtheearth.net"), new ServerInfo("ServerRedirect", "buildtheearth.net", false));

		return 1;
	}
}


