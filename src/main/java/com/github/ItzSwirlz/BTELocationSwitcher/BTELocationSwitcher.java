package com.github.ItzSwirlz.BTELocationSwitcher;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class BTELocationSwitcher implements ModInitializer {

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
			BTECommand.register(dispatcher);
		}));
	}
}
