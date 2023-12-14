package com.hexagram2021.stellarforge.common.register;

import com.hexagram2021.stellarforge.common.function.BlockFunction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import static com.hexagram2021.stellarforge.StellarForge.MODID;

public final class SFRegistries {
	public static final ResourceKey<Registry<BlockFunction>> BLOCK_FUNCTIONS = ResourceKey.createRegistryKey(new ResourceLocation(MODID, "block_functions"));

	private SFRegistries() {
	}
}
