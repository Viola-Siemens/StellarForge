package com.hexagram2021.stellarforge.common;

import com.hexagram2021.stellarforge.api.events.ChiselBlockEvent;
import com.hexagram2021.stellarforge.api.events.CrackBlockEvent;
import com.hexagram2021.stellarforge.api.events.MossifyBlockEvent;
import com.hexagram2021.stellarforge.api.events.SmoothBlockEvent;
import com.hexagram2021.stellarforge.common.register.SFBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Consumer;

import static com.hexagram2021.stellarforge.StellarForge.MODID;

@SuppressWarnings("UnnecessaryReturnStatement")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MODID)
public final class ForgeEventHandler {
	@SubscribeEvent
	public static void onMossifyBlock(MossifyBlockEvent event) {
		BlockState state = event.getState();

		if(tryTransfer(state, SFBlocks.Igneous.ANDESITE_BRICKS, SFBlocks.Igneous.MOSSY_ANDESITE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Igneous.DIORITE_BRICKS, SFBlocks.Igneous.MOSSY_DIORITE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Igneous.GRANITE_BRICKS, SFBlocks.Igneous.MOSSY_GRANITE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Stone.COBBLED_BLACKSTONE, SFBlocks.Stone.MOSSY_COBBLED_BLACKSTONE, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL, SFBlocks.Bricks.MOSSY_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL, SFBlocks.Stone.MOSSY_POLISHED_BLACKSTONE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Stone.POLISHED_BLACKSTONE_TILES, SFBlocks.Stone.MOSSY_POLISHED_BLACKSTONE_TILES, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_WALL, SFBlocks.Stone.MOSSY_COBBLED_DEEPSLATE, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Stone.STONE_TILES, SFBlocks.Stone.MOSSY_STONE_TILES, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_WALL, SFBlocks.Stone.MOSSY_DEEPSLATE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.DEEPSLATE_TILES, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_WALL, SFBlocks.Stone.MOSSY_DEEPSLATE_TILES, event::setTarget)) {
			return;
		}
	}
	@SubscribeEvent
	public static void onCrackBlock(CrackBlockEvent event) {
		BlockState state = event.getState();

		if(tryTransfer(state, SFBlocks.Igneous.ANDESITE_BRICKS, SFBlocks.Igneous.CRACKED_ANDESITE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Igneous.DIORITE_BRICKS, SFBlocks.Igneous.CRACKED_DIORITE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Igneous.GRANITE_BRICKS, SFBlocks.Igneous.CRACKED_GRANITE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Igneous.GRANITE_BRICKS, SFBlocks.Igneous.CRACKED_GRANITE_BRICKS, event::setTarget)) {
			return;
		}

		if(tryTransfer(state, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL, SFBlocks.Stone.CRACKED_STONE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_WALL, SFBlocks.Stone.CRACKED_DEEPSLATE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_WALL, SFBlocks.Stone.CRACKED_DEEPSLATE_TILES, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL, SFBlocks.Bricks.CRACKED_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_WALL, SFBlocks.Bricks.CRACKED_NETHER_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL, SFBlocks.Stone.CRACKED_POLISHED_BLACKSTONE_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Stone.POLISHED_BLACKSTONE_TILES, SFBlocks.Stone.CRACKED_POLISHED_BLACKSTONE_TILES, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Stone.STONE_TILES, SFBlocks.Stone.CRACKED_STONE_TILES, event::setTarget)) {
			return;
		}
	}
	@SubscribeEvent
	public static void onChiselBlock(ChiselBlockEvent event) {
		BlockState state = event.getState();
		if(state.is(Blocks.BRICKS)) {
			event.setTarget(SFBlocks.Bricks.CHISELED_BRICKS.defaultBlockState());
		} else if(state.is(SFBlocks.Igneous.ANDESITE_BRICKS.getFullBlock().get())) {
			event.setTarget(SFBlocks.Igneous.CHISELED_ANDESITE_BRICKS.defaultBlockState());
		} else if(state.is(SFBlocks.Igneous.DIORITE_BRICKS.getFullBlock().get())) {
			event.setTarget(SFBlocks.Igneous.CHISELED_DIORITE_BRICKS.defaultBlockState());
		} else if(state.is(SFBlocks.Igneous.GRANITE_BRICKS.getFullBlock().get())) {
			event.setTarget(SFBlocks.Igneous.CHISELED_GRANITE_BRICKS.defaultBlockState());
		} else if(state.is(Blocks.BLACKSTONE)) {
			event.setTarget(SFBlocks.Stone.CHISELED_BLACKSTONE.defaultBlockState());
		} else if(state.is(Blocks.POLISHED_BLACKSTONE_BRICKS)) {
			event.setTarget(SFBlocks.Stone.CHISELED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState());
		} else if(state.is(SFBlocks.Stone.POLISHED_BLACKSTONE_TILES.getFullBlock().get())) {
			event.setTarget(SFBlocks.Stone.CHISELED_POLISHED_BLACKSTONE_TILES.defaultBlockState());
		} else if(state.is(Blocks.STONE)) {
			event.setTarget(SFBlocks.Stone.CHISELED_STONE.defaultBlockState());
		} else if(state.is(SFBlocks.Stone.POLISHED_STONE.getFullBlock().get())) {
			event.setTarget(SFBlocks.Stone.CHISELED_POLISHED_STONE.defaultBlockState());
		} else if(state.is(Blocks.POLISHED_DEEPSLATE)) {
			event.setTarget(SFBlocks.Stone.CHISELED_POLISHED_DEEPSLATE.defaultBlockState());
		} else if(state.is(Blocks.DEEPSLATE_BRICKS)) {
			event.setTarget(SFBlocks.Stone.CHISELED_DEEPSLATE_BRICKS.defaultBlockState());
		} else if(state.is(Blocks.DEEPSLATE_TILES)) {
			event.setTarget(SFBlocks.Stone.CHISELED_DEEPSLATE_TILES.defaultBlockState());
		}
	}
	@SubscribeEvent
	public static void onSmoothBlock(SmoothBlockEvent event) {
		BlockState state = event.getState();
		if(tryTransfer(state, Blocks.BLACKSTONE, Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE_SLAB, Blocks.BLACKSTONE_WALL, SFBlocks.Stone.SMOOTH_BLACKSTONE, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, SFBlocks.Stone.DEEPSLATE, SFBlocks.Stone.SMOOTH_DEEPSLATE, event::setTarget)) {
			return;
		}
	}

	private static boolean tryTransfer(BlockState state, SFBlocks.IDecorGroup group1, SFBlocks.IDecorGroup group2, Consumer<BlockState> setTarget) {
		for(Pair<Block, Block> trans: SFBlocks.paired(group1, group2)) {
			if(state.is(trans.getKey())) {
				setTarget.accept(trans.getValue().withPropertiesOf(state));
				return true;
			}
		}
		return false;
	}
	private static boolean tryTransfer(BlockState state, Block stairs, Block slab, Block wall, SFBlocks.IDecorGroup group, Consumer<BlockState> setTarget) {
		for(Pair<Block, Block> trans: SFBlocks.paired(stairs, slab, wall, group)) {
			if(state.is(trans.getKey())) {
				setTarget.accept(trans.getValue().withPropertiesOf(state));
				return true;
			}
		}
		return false;
	}
	private static boolean tryTransfer(BlockState state, Block full, Block stairs, Block slab, Block wall, SFBlocks.IDecorGroup group, Consumer<BlockState> setTarget) {
		for(Pair<Block, Block> trans: SFBlocks.paired(full, stairs, slab, wall, group)) {
			if(state.is(trans.getKey())) {
				setTarget.accept(trans.getValue().withPropertiesOf(state));
				return true;
			}
		}
		return false;
	}
}
