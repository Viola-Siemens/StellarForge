package com.hexagram2021.stellarforge.common;

import com.hexagram2021.stellarforge.common.register.*;
import com.hexagram2021.stellarforge.server.commands.SFCommands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

public class SFContent {
	public static void modConstruction(IEventBus bus) {
		SFBlocks.init(bus);
		SFItems.init(bus);
		SFBlockFunctions.init(bus);
		SFCreativeModeTabs.init(bus);
	}

	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(SFCommands.register());
	}

	public static void onNewRegistry(NewRegistryEvent event) {
		event.create(new RegistryBuilder<>().setName(SFRegistries.BLOCK_FUNCTIONS.location()).disableSync().disableSaving().hasTags());
	}
}
