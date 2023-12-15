package com.hexagram2021.stellarforge.common;

import com.hexagram2021.stellarforge.api.events.CrackBlockEvent;
import com.hexagram2021.stellarforge.api.events.MossifyBlockEvent;
import com.hexagram2021.stellarforge.common.register.SFBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Consumer;

import static com.hexagram2021.stellarforge.StellarForge.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MODID)
public final class ForgeEventHandler {
	@SuppressWarnings("UnnecessaryReturnStatement")
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
	}
	@SuppressWarnings("UnnecessaryReturnStatement")
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
		if(tryTransfer(state, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_WALL, SFBlocks.Stone.CRACKED_NETHER_BRICKS, event::setTarget)) {
			return;
		}
		if(tryTransfer(state, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL, SFBlocks.Stone.CRACKED_POLISHED_BLACKSTONE_BRICKS, event::setTarget)) {
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
}
