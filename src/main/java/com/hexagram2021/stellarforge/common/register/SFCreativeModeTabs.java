package com.hexagram2021.stellarforge.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.hexagram2021.stellarforge.StellarForge.MODID;

@SuppressWarnings("unused")
public final class SFCreativeModeTabs {
	private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

	public static final RegistryObject<CreativeModeTab> REAL_PEACEFUL_MODE = register(
			"stellarforge", Component.translatable("itemGroup.stellarforge"), () -> new ItemStack(SFBlocks.Igneous.ANDESITE_BRICKS),
			(parameters, output) -> SFItems.ItemEntry.ALL_ITEMS.forEach(output::accept)
	);

	private SFCreativeModeTabs() {
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	@SuppressWarnings("SameParameterValue")
	private static RegistryObject<CreativeModeTab> register(String name, Component title, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator generator) {
		return REGISTER.register(
				name, () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.SPAWN_EGGS).title(title).icon(icon).displayItems(generator).build()
		);
	}
}
