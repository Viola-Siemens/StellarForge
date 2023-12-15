package com.hexagram2021.stellarforge.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SFCommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue ENABLE_REGISTRY_CHECK;
	public static final ForgeConfigSpec.IntValue COMMAND_PERMISSION;

	static {
		BUILDER.push("stellarforge-common-config");

		COMMAND_PERMISSION = BUILDER.comment("Permission level for command `/stellarforge`").defineInRange("COMMAND_PERMISSION", 2, 0, 4);
		ENABLE_REGISTRY_CHECK = BUILDER.comment("Enable registry check. This function is good for debugging, especially for developers, server operators, and players who find the game not working. But it takes time to check everytime the game starts. It is recommended for you to run it in game test server.").define("ENABLE_REGISTRY_CHECK", false);

		BUILDER.pop();

		SPEC = BUILDER.build();
	}

	public static ForgeConfigSpec getConfig() {
		return SPEC;
	}
}
