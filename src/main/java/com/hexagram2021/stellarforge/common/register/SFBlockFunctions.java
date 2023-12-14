package com.hexagram2021.stellarforge.common.register;

import com.hexagram2021.stellarforge.api.events.FreezeBlockEvent;
import com.hexagram2021.stellarforge.api.events.MossifyBlockEvent;
import com.hexagram2021.stellarforge.common.function.BlockFunction;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Optional;

import static com.hexagram2021.stellarforge.StellarForge.MODID;

public class SFBlockFunctions {
	public static final DeferredRegister<BlockFunction> REGISTER = DeferredRegister.create(SFRegistries.BLOCK_FUNCTIONS, MODID);

	private SFBlockFunctions() {
	}

	static {
		REGISTER.register("freeze", () -> (level, pos, state) -> {
			BlockState ret = null;
			try {
				if (state.is(Blocks.LAVA)) {
					// lava -> obsidian/basalt/cobblestone
					if (state.getValue(BlockStateProperties.LEVEL) == 0) {
						ret = Blocks.OBSIDIAN.defaultBlockState();
					} else if (level.getBlockState(pos.below()).is(Blocks.SOUL_SOIL)) {
						ret = Blocks.BASALT.defaultBlockState();
					} else {
						ret = Blocks.COBBLESTONE.defaultBlockState();
					}
				} else if (state.is(Blocks.WATER)) {
					// water -> ice/frosted_ice
					if (state.getValue(BlockStateProperties.LEVEL) == 0) {
						ret = Blocks.ICE.defaultBlockState();
					} else {
						ret = Blocks.FROSTED_ICE.defaultBlockState();
					}
				} else if (state.is(Blocks.WATER_CAULDRON)) {
					// water_cauldron -> powder_snow_cauldron
					ret = Blocks.POWDER_SNOW_CAULDRON.defaultBlockState();
				} else if (state.is(Blocks.FIRE) || state.is(Blocks.SOUL_FIRE)) {
					// fire/soul_fire -> air
					ret = Blocks.AIR.defaultBlockState();
				} else if (state.is(Blocks.CAMPFIRE) || state.is(Blocks.SOUL_CAMPFIRE) ||
						state.is(Blocks.REDSTONE_ORE) || state.is(Blocks.DEEPSLATE_REDSTONE_ORE)) {
					// campfire/redstone_ore -> (extinguished)
					ret = state.setValue(BlockStateProperties.LIT, false);
				}
			} catch (IllegalArgumentException ignored) {
			}

			FreezeBlockEvent event = new FreezeBlockEvent(level, pos, state, ret);
			MinecraftForge.EVENT_BUS.post(event);

			return event.getTarget();
		});
		REGISTER.register("mossify", () -> (level, pos, state) -> {
			BlockState ret = null;

			if(state.is(Blocks.COBBLESTONE)) {
				ret = Blocks.MOSSY_COBBLESTONE.defaultBlockState();
			} else if(state.is(Blocks.COBBLESTONE_STAIRS)) {
				ret = Blocks.MOSSY_COBBLESTONE_STAIRS.withPropertiesOf(state);
			} else if(state.is(Blocks.COBBLESTONE_SLAB)) {
				ret = Blocks.MOSSY_COBBLESTONE_SLAB.withPropertiesOf(state);
			} else if(state.is(Blocks.COBBLESTONE_WALL)) {
				ret = Blocks.MOSSY_COBBLESTONE_WALL.withPropertiesOf(state);
			} else if(state.is(Blocks.STONE_BRICKS)) {
				ret = Blocks.MOSSY_STONE_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.STONE_BRICK_STAIRS)) {
				ret = Blocks.MOSSY_STONE_BRICK_STAIRS.withPropertiesOf(state);
			} else if(state.is(Blocks.STONE_BRICK_SLAB)) {
				ret = Blocks.MOSSY_STONE_BRICK_SLAB.withPropertiesOf(state);
			} else if(state.is(Blocks.STONE_BRICK_WALL)) {
				ret = Blocks.MOSSY_STONE_BRICK_WALL.withPropertiesOf(state);
			}

			MossifyBlockEvent event = new MossifyBlockEvent(level, pos, state, ret);
			MinecraftForge.EVENT_BUS.post(event);

			return event.getTarget();
		});

		REGISTER.register("wax", () -> (level, pos, state) -> Optional.ofNullable(HoneycombItem.WAXABLES.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state)).orElse(null));
		REGISTER.register("wax_off", () -> (level, pos, state) -> Optional.ofNullable(HoneycombItem.WAX_OFF_BY_BLOCK.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state)).orElse(null));

		REGISTER.register("smelt", () -> (level, pos, state) -> {
			ItemStack itemStack = new ItemStack(state.getBlock());
			BlockState ret = null;
			Optional<SmeltingRecipe> optional = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(itemStack), level);
			if(optional.isPresent()) {
				ItemStack result = optional.get().getResultItem(level.registryAccess());
				if(result.getCount() == 1 && result.getItem() instanceof BlockItem blockItem) {
					ret = blockItem.getBlock().withPropertiesOf(state);
				}
			}

			return ret;
		});
		REGISTER.register("blast", () -> (level, pos, state) -> {
			ItemStack itemStack = new ItemStack(state.getBlock());
			BlockState ret = null;
			Optional<BlastingRecipe> optional = level.getRecipeManager().getRecipeFor(RecipeType.BLASTING, new SimpleContainer(itemStack), level);
			if(optional.isPresent()) {
				ItemStack result = optional.get().getResultItem(level.registryAccess());
				if(result.getCount() == 1 && result.getItem() instanceof BlockItem blockItem) {
					ret = blockItem.getBlock().withPropertiesOf(state);
				}
			}

			return ret;
		});
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
