package com.hexagram2021.stellarforge.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SFCommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec SPEC;

	static {
		BUILDER.push("stellarforge-common-config");
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

	public static ForgeConfigSpec getConfig() {
		return SPEC;
	}
}
