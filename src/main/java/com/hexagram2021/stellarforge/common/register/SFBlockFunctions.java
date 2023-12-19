package com.hexagram2021.stellarforge.common.register;

import com.hexagram2021.stellarforge.api.events.*;
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
		REGISTER.register("crack", () -> (level, pos, state) -> {
			BlockState ret = null;

			if(state.is(Blocks.STONE_BRICKS)) {
				ret = Blocks.CRACKED_STONE_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.DEEPSLATE_BRICKS)) {
				ret = Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.DEEPSLATE_TILES)) {
				ret = Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState();
			} else if(state.is(Blocks.NETHER_BRICKS)) {
				ret = Blocks.CRACKED_NETHER_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.POLISHED_BLACKSTONE_BRICKS)) {
				ret = Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.INFESTED_STONE_BRICKS)) {
				ret = Blocks.INFESTED_CRACKED_STONE_BRICKS.defaultBlockState();
			}

			CrackBlockEvent event = new CrackBlockEvent(level, pos, state, ret);
			MinecraftForge.EVENT_BUS.post(event);

			return event.getTarget();
		});
		REGISTER.register("chisel", () -> (level, pos, state) -> {
			BlockState ret = null;

			if(state.is(Blocks.BOOKSHELF)) {
				ret = Blocks.CHISELED_BOOKSHELF.defaultBlockState();
			} else if(state.is(Blocks.DEEPSLATE)) {
				ret = Blocks.CHISELED_DEEPSLATE.defaultBlockState();
			} else if(state.is(Blocks.NETHER_BRICKS)) {
				ret = Blocks.CHISELED_NETHER_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.POLISHED_BLACKSTONE)) {
				ret = Blocks.CHISELED_POLISHED_BLACKSTONE.defaultBlockState();
			} else if(state.is(Blocks.QUARTZ_BLOCK)) {
				ret = Blocks.CHISELED_QUARTZ_BLOCK.defaultBlockState();
			} else if(state.is(Blocks.RED_SANDSTONE)) {
				ret = Blocks.CHISELED_RED_SANDSTONE.defaultBlockState();
			} else if(state.is(Blocks.SANDSTONE)) {
				ret = Blocks.CHISELED_SANDSTONE.defaultBlockState();
			} else if(state.is(Blocks.STONE_BRICKS)) {
				ret = Blocks.CHISELED_STONE_BRICKS.defaultBlockState();
			} else if(state.is(Blocks.INFESTED_STONE_BRICKS)) {
				ret = Blocks.INFESTED_CHISELED_STONE_BRICKS.defaultBlockState();
			}

			ChiselBlockEvent event = new ChiselBlockEvent(level, pos, state, ret);
			MinecraftForge.EVENT_BUS.post(event);

			return event.getTarget();
		});
		REGISTER.register("smooth", () -> (level, pos, state) -> {
			BlockState ret = null;

			if(state.is(Blocks.BASALT)) {
				ret = Blocks.SMOOTH_BASALT.defaultBlockState();
			} else if(state.is(Blocks.QUARTZ_BLOCK)) {
				ret = Blocks.SMOOTH_QUARTZ.defaultBlockState();
			} else if(state.is(Blocks.QUARTZ_SLAB)) {
				ret = Blocks.SMOOTH_QUARTZ_SLAB.withPropertiesOf(state);
			} else if(state.is(Blocks.QUARTZ_STAIRS)) {
				ret = Blocks.SMOOTH_QUARTZ_STAIRS.withPropertiesOf(state);
			} else if(state.is(Blocks.RED_SANDSTONE)) {
				ret = Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState();
			} else if(state.is(Blocks.RED_SANDSTONE_SLAB)) {
				ret = Blocks.SMOOTH_RED_SANDSTONE_SLAB.withPropertiesOf(state);
			} else if(state.is(Blocks.RED_SANDSTONE_STAIRS)) {
				ret = Blocks.SMOOTH_RED_SANDSTONE_STAIRS.withPropertiesOf(state);
			} else if(state.is(Blocks.SANDSTONE)) {
				ret = Blocks.SMOOTH_SANDSTONE.defaultBlockState();
			} else if(state.is(Blocks.SANDSTONE_SLAB)) {
				ret = Blocks.SMOOTH_SANDSTONE_SLAB.withPropertiesOf(state);
			} else if(state.is(Blocks.SANDSTONE_STAIRS)) {
				ret = Blocks.SMOOTH_SANDSTONE_STAIRS.withPropertiesOf(state);
			} else if(state.is(Blocks.STONE)) {
				ret = Blocks.SMOOTH_STONE.defaultBlockState();
			} else if(state.is(Blocks.STONE_SLAB)) {
				ret = Blocks.SMOOTH_STONE_SLAB.withPropertiesOf(state);
			}

			SmoothBlockEvent event = new SmoothBlockEvent(level, pos, state, ret);
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
