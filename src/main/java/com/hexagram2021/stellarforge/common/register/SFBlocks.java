package com.hexagram2021.stellarforge.common.register;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.stellarforge.StellarForge.MODID;

public final class SFBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	public static final class Igneous {
		public static final DecorBlockEntry ANDESITE_BRICKS = new DecorBlockEntry("andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_ANDESITE_BRICKS = new DecorBlockEntry("mossy_andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_ANDESITE_BRICKS = new BlockEntry<>("chiseled_andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CRACKED_ANDESITE_BRICKS = new BlockEntry<>("cracked_andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS), Block::new, SFBlocks::toItem);

		public static final DecorBlockEntry DIORITE_BRICKS = new DecorBlockEntry("diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_DIORITE_BRICKS = new DecorBlockEntry("mossy_diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_DIORITE_BRICKS = new BlockEntry<>("chiseled_diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CRACKED_DIORITE_BRICKS = new BlockEntry<>("cracked_diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS), Block::new, SFBlocks::toItem);

		public static final DecorBlockEntry GRANITE_BRICKS = new DecorBlockEntry("granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_GRANITE_BRICKS = new DecorBlockEntry("mossy_granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_GRANITE_BRICKS = new BlockEntry<>("chiseled_granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CRACKED_GRANITE_BRICKS = new BlockEntry<>("cracked_granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS), Block::new, SFBlocks::toItem);

		private Igneous() {
		}

		private static void init() {
		}
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);

		Igneous.init();
	}

	public static <T extends Block> BlockItem toItem(T block) {
		return new BlockItem(block, new Item.Properties());
	}

	public static final class BlockEntry<T extends Block> implements Supplier<T>, ItemLike {
		private final RegistryObject<T> regObject;
		private final Supplier<BlockBehaviour.Properties> properties;

		public BlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<BlockBehaviour.Properties, T> make) {
			this.properties = properties;
			this.regObject = REGISTER.register(name, () -> make.apply(properties.get()));
		}

		public BlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<BlockBehaviour.Properties, T> make, Function<T, Item> toItem) {
			this(name, properties, make);
			SFItems.ItemEntry.register(name, () -> toItem.apply(this.regObject.get()));
		}

		BlockEntry(RegistryObject<T> regObject) {
			this.regObject = regObject;
			this.properties = () -> regObject.get().properties;
		}

		@Override
		public T get() {
			return this.regObject.get();
		}

		public BlockState defaultBlockState() {
			return this.get().defaultBlockState();
		}

		public ResourceLocation getId() {
			return this.regObject.getId();
		}

		public BlockBehaviour.Properties getProperties() {
			return this.properties.get();
		}

		@Override
		public Item asItem() {
			return this.get().asItem();
		}
	}

	public interface IDecorGroup {
		BlockEntry<Block> getFullBlock();
		BlockEntry<StairBlock> getStairsBlock();
		BlockEntry<SlabBlock> getSlabBlock();
		BlockEntry<WallBlock> getWallBlock();
	}

	public static final class DecorBlockEntry implements Supplier<Block>, ItemLike, IDecorGroup {
		private final RegistryObject<Block> full;
		private final RegistryObject<StairBlock> stairs;
		private final RegistryObject<SlabBlock> slab;
		private final RegistryObject<WallBlock> wall;
		private final Supplier<BlockBehaviour.Properties> properties;

		private static String changeNameTo(String name, String postfix) {
			if(name.endsWith("_block")) {
				name = name.replaceAll("_block", postfix);
			} else if(name.endsWith("_bricks")) {
				name = name.replaceAll("_bricks", "_brick" + postfix);
			} else if(name.endsWith("_planks")) {
				name = name.replaceAll("_planks", postfix);
			} else {
				name = name + postfix;
			}
			return name;
		}

		public DecorBlockEntry(String name, Supplier<BlockBehaviour.Properties> properties) {
			this.properties = properties;
			this.full = REGISTER.register(name, () -> new Block(properties.get()));
			this.stairs = REGISTER.register(changeNameTo(name, "_stairs"), () -> new StairBlock(this.full.get()::defaultBlockState, properties.get()));
			this.slab = REGISTER.register(changeNameTo(name, "_slab"), () -> new SlabBlock(
					properties.get()
							.isSuffocating((state, level, pos) ->
									this.full.get().defaultBlockState().isSuffocating(level, pos) && state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE)
							.isRedstoneConductor((state, level, pos) ->
									this.full.get().defaultBlockState().isRedstoneConductor(level, pos) && state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE)
			));
			this.wall = REGISTER.register(changeNameTo(name, "_wall"), () -> new WallBlock(properties.get()));
		}

		public DecorBlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<Block, Item> toItem) {
			this(name, properties);

			SFItems.ItemEntry.register(name, () -> toItem.apply(this.full.get()));
			SFItems.ItemEntry.register(changeNameTo(name, "_stairs"), () -> toItem.apply(this.stairs.get()));
			SFItems.ItemEntry.register(changeNameTo(name, "_slab"), () -> toItem.apply(this.slab.get()));
			SFItems.ItemEntry.register(changeNameTo(name, "_wall"), () -> toItem.apply(this.wall.get()));
		}

		@Override
		public Block get() {
			return this.full.get();
		}

		@Override
		public Item asItem() {
			return this.get().asItem();
		}

		@Override
		public BlockEntry<Block> getFullBlock() {
			return new BlockEntry<>(this.full);
		}

		@Override
		public BlockEntry<StairBlock> getStairsBlock() {
			return new BlockEntry<>(this.stairs);
		}

		@Override
		public BlockEntry<SlabBlock> getSlabBlock() {
			return new BlockEntry<>(this.slab);
		}

		@Override
		public BlockEntry<WallBlock> getWallBlock() {
			return new BlockEntry<>(this.wall);
		}

		public BlockBehaviour.Properties getProperties() {
			return this.properties.get();
		}
	}

	public static Iterable<Pair<Block, Block>> paired(IDecorGroup group1, IDecorGroup group2) {
		return ImmutableList.of(
				Pair.of(group1.getFullBlock().get(), group2.getFullBlock().get()),
				Pair.of(group1.getStairsBlock().get(), group2.getStairsBlock().get()),
				Pair.of(group1.getSlabBlock().get(), group2.getSlabBlock().get()),
				Pair.of(group1.getWallBlock().get(), group2.getWallBlock().get())
		);
	}

	private SFBlocks() {
	}
}
