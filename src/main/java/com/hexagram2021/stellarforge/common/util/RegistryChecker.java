package com.hexagram2021.stellarforge.common.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.function.Supplier;

import static com.hexagram2021.stellarforge.common.util.RegistryHelper.getRegistryName;

@SuppressWarnings({"deprecation", "unused", "UnusedReturnValue"})
public interface RegistryChecker {
	Set<Block> WHITELIST_NO_LOOT_TABLE_BLOCKS = ImmutableSet.of(
			Blocks.AIR, Blocks.VOID_AIR, Blocks.CAVE_AIR, Blocks.WATER, Blocks.LAVA, Blocks.LIGHT,
			Blocks.PISTON_HEAD, Blocks.MOVING_PISTON, Blocks.FIRE, Blocks.SOUL_FIRE, Blocks.END_PORTAL,
			Blocks.FROSTED_ICE, Blocks.END_GATEWAY, Blocks.END_PORTAL_FRAME, Blocks.COMMAND_BLOCK,
			Blocks.REPEATING_COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.STRUCTURE_BLOCK, Blocks.STRUCTURE_VOID,
			Blocks.BUBBLE_COLUMN, Blocks.JIGSAW, Blocks.BARRIER
	);
	Set<Block> WHITELIST_NOT_IN_MINEABLE_TAGS = ImmutableSet.of(
			Blocks.COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK,
			Blocks.STRUCTURE_BLOCK, Blocks.JIGSAW
	);
	Set<Block> WHITELIST_NO_ITEM_BLOCKS = ImmutableSet.of(
			Blocks.AIR, Blocks.VOID_AIR, Blocks.CAVE_AIR, Blocks.WATER, Blocks.LAVA, Blocks.PISTON_HEAD,
			Blocks.MOVING_PISTON, Blocks.FIRE, Blocks.SOUL_FIRE, Blocks.END_PORTAL, Blocks.NETHER_PORTAL,
			Blocks.CANDLE_CAKE, Blocks.END_GATEWAY, Blocks.FROSTED_ICE, Blocks.BUBBLE_COLUMN
	);

	static void registryCheck(LootDataManager lootDataManager) {
		ForgeRegistries.BLOCKS.forEach(block -> {
			ResourceLocation id = getRegistryName(block);
			Item blockItem = block.asItem();
			if(!WHITELIST_NO_ITEM_BLOCKS.contains(block) && (!id.getPath().startsWith("potted_") && !id.getPath().endsWith("_candle_cake")) && blockItem.equals(Items.AIR)) {
				SFLogger.warn("[Registry Check] No BlockItem registered for block %s.".formatted(id));
			}
			tagCheckSuffix(id, block, blockItem, "_slab", BlockTags.SLABS, ItemTags.SLABS);
			tagCheckSuffix(id, block, blockItem, "_stairs", BlockTags.STAIRS, ItemTags.STAIRS);
			tagCheckSuffix(id, block, blockItem, "_wall", BlockTags.WALLS, ItemTags.WALLS);
			tagCheckSuffix(id, block, blockItem, "_planks", BlockTags.PLANKS, ItemTags.PLANKS);
			tagCheckSuffix(id, block, blockItem, "_button", BlockTags.BUTTONS, ItemTags.BUTTONS);
			tagCheckSuffix(id, block, blockItem, "_door", BlockTags.DOORS, ItemTags.DOORS);
			tagCheckSuffix(id, block, blockItem, "_trapdoor", BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
			tagCheckSuffix(id, block, blockItem, "_fence", BlockTags.FENCES, ItemTags.FENCES);
			tagCheckSuffix(id, block, blockItem, "_fence_gate", BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
			tagCheckSuffix(id, block, blockItem, "_leaves", BlockTags.LEAVES, ItemTags.LEAVES);
			tagCheckSuffix(id, block, "_wall_hanging_sign", BlockTags.WALL_HANGING_SIGNS).ifFailed(
					() -> tagCheckSuffix(id, block, blockItem, "_hanging_sign", BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS).or(
							tagCheckSuffix(id, block, "_wall_sign", BlockTags.WALL_SIGNS)
					)
			).ifFailed(
					() -> tagCheckSuffix(id, block, blockItem, "_sign", BlockTags.STANDING_SIGNS, ItemTags.SIGNS)
			);
			tagCheckSuffix(id, block, blockItem, "_bed", BlockTags.BEDS, ItemTags.BEDS);
			tagCheckPrefix(id, block, "potted_", BlockTags.FLOWER_POTS).ifFailed(
					() -> tagCheckSuffix(id, block, blockItem, "_sapling", BlockTags.SAPLINGS, ItemTags.SAPLINGS)
			);
			tagCheckSuffix(id, block, blockItem, "_rail", BlockTags.RAILS, ItemTags.RAILS);
			tagCheckSuffix(id, block, "_cauldron", BlockTags.CAULDRONS);

			tagCheckSuffix(id, block, blockItem, "_wool", BlockTags.WOOL, ItemTags.WOOL);
			tagCheckSuffix(id, block, blockItem, "_log", BlockTags.LOGS, ItemTags.LOGS);
			tagCheckSuffix(id, block, blockItem, "_wood", BlockTags.LOGS, ItemTags.LOGS);
			tagCheckSuffix(id, block, blockItem, "_stem", BlockTags.LOGS, ItemTags.LOGS);
			tagCheckSuffix(id, block, blockItem, "_hyphae", BlockTags.LOGS, ItemTags.LOGS);
			tagCheckSuffix(id, block, "_nylium", BlockTags.NYLIUM);
			tagCheckSuffix(id, block, blockItem, "_sand", BlockTags.SAND, ItemTags.SAND);
			tagCheckSuffix(id, "_glazed_terracotta").ifFailed(
					() -> tagCheckSuffix(id, block, blockItem, "_terracotta", BlockTags.TERRACOTTA, ItemTags.TERRACOTTA)
			);

			tagCheckSubstr(id, block, blockItem, "gold", BlockTags.GUARDED_BY_PIGLINS, ItemTags.PIGLIN_LOVED);

			if(!WHITELIST_NO_LOOT_TABLE_BLOCKS.contains(block)) {
				if((block.getLootTable().equals(BuiltInLootTables.EMPTY) || lootDataManager.getLootTable(block.getLootTable()).equals(LootTable.EMPTY))) {
					SFLogger.warn("[Registry Check] Missing loot table for block %s.".formatted(id));
				}
				if(block.defaultBlockState().requiresCorrectToolForDrops()) {
					checkIn(id, block, "mineable", BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_HOE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_SHOVEL);
				}
			}
		});
	}

	static CheckResult tagCheckSubstr(ResourceLocation id, String substr) {
		return new CheckResult(id.getPath().contains(substr));
	}
	static CheckResult tagCheckSubstr(ResourceLocation id, Block block, Item blockItem, String substr, TagKey<Block> blockTag, TagKey<Item> itemTag) {
		if(id.getPath().contains(substr)) {
			boolean error = false;
			substr = substr.replace('_', ' ');
			if(!block.builtInRegistryHolder().is(blockTag)) {
				SFLogger.warn("[Registry Check] Likely %s block %s is not in tag %s.".formatted(substr, id, blockTag));
				error = true;
			}
			if(!blockItem.builtInRegistryHolder().is(itemTag)) {
				SFLogger.warn("[Registry Check] Likely %s item %s is not in tag %s.".formatted(substr, id, itemTag));
				error = true;
			}
			return new CheckResult(true, error);
		}
		return new CheckResult(false);
	}
	static CheckResult tagCheckSubstr(ResourceLocation id, Block block, String substr, TagKey<Block> blockTag) {
		if(id.getPath().contains(substr)) {
			boolean error = false;
			substr = substr.replace('_', ' ');
			if(!block.builtInRegistryHolder().is(blockTag)) {
				SFLogger.warn("[Registry Check] Likely %s block %s is not in tag %s.".formatted(substr, id, blockTag));
				error = true;
			}
			return new CheckResult(true, error);
		}
		return new CheckResult(false);
	}

	static CheckResult tagCheckSuffix(ResourceLocation id, String suffix) {
		return new CheckResult(id.getPath().endsWith(suffix));
	}
	static CheckResult tagCheckSuffix(ResourceLocation id, Block block, Item blockItem, String suffix, TagKey<Block> blockTag, TagKey<Item> itemTag) {
		if(id.getPath().endsWith(suffix)) {
			boolean error = false;
			suffix = suffix.substring(1).replace('_', ' ');
			if(!block.builtInRegistryHolder().is(blockTag)) {
				SFLogger.warn("[Registry Check] Likely %s block %s is not in tag %s.".formatted(suffix, id, blockTag));
				error = true;
			}
			if(!blockItem.builtInRegistryHolder().is(itemTag)) {
				SFLogger.warn("[Registry Check] Likely %s item %s is not in tag %s.".formatted(suffix, id, itemTag));
				error = true;
			}
			return new CheckResult(true, error);
		}
		return new CheckResult(false);
	}
	static CheckResult tagCheckSuffix(ResourceLocation id, Block block, String suffix, TagKey<Block> blockTag) {
		if(id.getPath().endsWith(suffix)) {
			boolean error = false;
			suffix = suffix.substring(1).replace('_', ' ');
			if(!block.builtInRegistryHolder().is(blockTag)) {
				SFLogger.warn("[Registry Check] Likely %s block %s is not in tag %s.".formatted(suffix, id, blockTag));
				error = true;
			}
			return new CheckResult(true, error);
		}
		return new CheckResult(false);
	}

	static CheckResult tagCheckPrefix(ResourceLocation id, String prefix) {
		return new CheckResult(id.getPath().startsWith(prefix));
	}
	static CheckResult tagCheckPrefix(ResourceLocation id, Block block, Item blockItem, String prefix, TagKey<Block> blockTag, TagKey<Item> itemTag) {
		if(id.getPath().startsWith(prefix)) {
			boolean error = false;
			prefix = prefix.substring(0, prefix.length() - 1).replace('_', ' ');
			if(!block.builtInRegistryHolder().is(blockTag)) {
				SFLogger.warn("[Registry Check] Likely %s block %s is not in tag %s.".formatted(prefix, id, blockTag));
				error = true;
			}
			if(!blockItem.builtInRegistryHolder().is(itemTag)) {
				SFLogger.warn("[Registry Check] Likely %s item %s is not in tag %s.".formatted(prefix, id, itemTag));
				error = true;
			}
			return new CheckResult(true, error);
		}
		return new CheckResult(false);
	}
	static CheckResult tagCheckPrefix(ResourceLocation id, Block block, String prefix, TagKey<Block> blockTag) {
		if(id.getPath().startsWith(prefix)) {
			boolean error = false;
			prefix = prefix.substring(0, prefix.length() - 1).replace('_', ' ');
			if(!block.builtInRegistryHolder().is(blockTag)) {
				SFLogger.warn("[Registry Check] Likely %s block %s is not in tag %s.".formatted(prefix, id, blockTag));
				error = true;
			}
			return new CheckResult(true, error);
		}
		return new CheckResult(false);
	}

	@SafeVarargs
	static CheckResult checkIn(ResourceLocation id, Block block, String tagDesc, TagKey<Block>... blockTags) {
		boolean error = true;
		Holder.Reference<Block> holder = block.builtInRegistryHolder();
		for(TagKey<Block> blockTag: blockTags) {
			if(holder.is(blockTag)) {
				error = false;
				break;
			}
		}
		if(error) {
			SFLogger.warn("[Registry Check] Block %s is not in any of the `%s` tags.".formatted(id, tagDesc));
		}
		return new CheckResult(true, error);
	}

	@SafeVarargs
	static CheckResult checkNotIn(ResourceLocation id, Block block, TagKey<Block>... blockTags) {
		boolean error = false;
		Holder.Reference<Block> holder = block.builtInRegistryHolder();
		for(TagKey<Block> blockTag: blockTags) {
			if(holder.is(blockTag)) {
				error = true;
				SFLogger.warn("[Registry Check] Block %s is not supposed to be in tag %s.".formatted(id, blockTag));
				break;
			}
		}
		return new CheckResult(true, error);
	}

	class CheckResult {
		private final boolean matches;
		private final boolean error;

		public CheckResult(boolean matches) {
			this(matches, false);
		}

		public CheckResult(boolean matches, boolean error) {
			this.matches = matches;
			this.error = error;
		}

		public CheckResult ifFailed(Supplier<CheckResult> next) {
			return this.matches ? this : next.get();
		}

		public CheckResult ifError(Supplier<CheckResult> next) {
			return this.error ? this : next.get();
		}

		public CheckResult or(CheckResult other) {
			return new CheckResult(this.matches || other.matches, this.error || other.error);
		}
	}
}
