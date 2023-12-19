package com.hexagram2021.stellarforge.common.register;

import com.google.common.collect.ImmutableList;
import com.hexagram2021.stellarforge.common.block.PillarBlock;
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
import static com.hexagram2021.stellarforge.common.util.RegistryHelper.getRegistryName;

public final class SFBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	public static final class Bricks {
		public static final DecorBlockEntry MOSSY_BRICKS = new DecorBlockEntry("mossy_bricks", () -> BlockBehaviour.Properties.copy(Blocks.BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_BRICKS = new DecorBlockEntry("cracked_bricks", () -> BlockBehaviour.Properties.copy(Blocks.BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_BRICKS = new BlockEntry<>("chiseled_bricks", () -> BlockBehaviour.Properties.copy(Blocks.BRICKS), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_NETHER_BRICKS = DecorBlockEntry.createFromFull(Blocks.CRACKED_NETHER_BRICKS, SFBlocks::toItem);

		private Bricks() {
		}

		private static void init() {
		}
	}

	public static final class Igneous {
		public static final DecorBlockEntry ANDESITE_BRICKS = new DecorBlockEntry("andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_ANDESITE_BRICKS = new DecorBlockEntry("mossy_andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_ANDESITE_BRICKS = new BlockEntry<>("chiseled_andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_ANDESITE_BRICKS = new DecorBlockEntry("cracked_andesite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS), SFBlocks::toItem);

		public static final DecorBlockEntry DIORITE_BRICKS = new DecorBlockEntry("diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_DIORITE_BRICKS = new DecorBlockEntry("mossy_diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_DIORITE_BRICKS = new BlockEntry<>("chiseled_diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_DIORITE_BRICKS = new DecorBlockEntry("cracked_diorite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS), SFBlocks::toItem);

		public static final DecorBlockEntry GRANITE_BRICKS = new DecorBlockEntry("granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_GRANITE_BRICKS = new DecorBlockEntry("mossy_granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_GRANITE_BRICKS = new BlockEntry<>("chiseled_granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_GRANITE_BRICKS = new DecorBlockEntry("cracked_granite_bricks", () -> BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS), SFBlocks::toItem);

		private Igneous() {
		}

		private static void init() {
		}
	}

	public static final class Stone {
		//vanilla
		public static final DecorBlockEntry CRACKED_STONE_BRICKS = DecorBlockEntry.createFromFull(Blocks.CRACKED_STONE_BRICKS, SFBlocks::toItem);
		public static final DecorBlockEntry DEEPSLATE = DecorBlockEntry.createFromFull(Blocks.DEEPSLATE, SFBlocks::toItem);
		public static final BlockEntry<ButtonBlock> DEEPSLATE_BUTTON = new BlockEntry<>("deepslate_button", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), props -> new ButtonBlock(props, SFBlockSetTypes.DEEPSLATE, 20, false), SFBlocks::toItem);
		public static final BlockEntry<PressurePlateBlock> DEEPSLATE_PRESSURE_PLATE = new BlockEntry<>("deepslate_pressure_plate", () -> BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE), props -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, props, SFBlockSetTypes.DEEPSLATE), SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_DEEPSLATE_BRICKS = DecorBlockEntry.createFromFull(Blocks.CRACKED_DEEPSLATE_BRICKS, SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_DEEPSLATE_TILES = DecorBlockEntry.createFromFull(Blocks.CRACKED_DEEPSLATE_TILES, SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_POLISHED_BLACKSTONE_BRICKS = DecorBlockEntry.createFromFull(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, SFBlocks::toItem);

		//blackstone
		public static final DecorBlockEntry COBBLED_BLACKSTONE = new DecorBlockEntry("cobbled_blackstone", () -> BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_COBBLED_BLACKSTONE = new DecorBlockEntry("mossy_cobbled_blackstone", () -> BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), SFBlocks::toItem);
		public static final DecorBlockEntry SMOOTH_BLACKSTONE = new DecorBlockEntry("smooth_blackstone", () -> BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), SFBlocks::toItem);
		public static final BlockEntry<PillarBlock> POLISHED_BLACKSTONE_PILLAR = new BlockEntry<>("polished_blackstone_pillar", () -> BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), PillarBlock::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_BLACKSTONE = new BlockEntry<>("chiseled_blackstone", () -> BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_POLISHED_BLACKSTONE_BRICKS = new BlockEntry<>("chiseled_polished_blackstone_bricks", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_POLISHED_BLACKSTONE_TILES = new BlockEntry<>("chiseled_polished_blackstone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry POLISHED_BLACKSTONE_TILES = new DecorBlockEntry("polished_blackstone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_POLISHED_BLACKSTONE_TILES = new DecorBlockEntry("cracked_polished_blackstone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_POLISHED_BLACKSTONE_BRICKS = new DecorBlockEntry("mossy_polished_blackstone_bricks", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_POLISHED_BLACKSTONE_TILES = new DecorBlockEntry("mossy_polished_blackstone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS), SFBlocks::toItem);

		//stone
		public static final BlockEntry<PillarBlock> STONE_PILLAR = new BlockEntry<>("stone_pillar", () -> BlockBehaviour.Properties.copy(Blocks.STONE), PillarBlock::new, SFBlocks::toItem);
		public static final DecorBlockEntry STONE = DecorBlockEntry.createFromFullSlabStairs(Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_STAIRS, SFBlocks::toItem);
		public static final DecorBlockEntry SMOOTH_STONE = DecorBlockEntry.createFromFullSlab(Blocks.SMOOTH_STONE, Blocks.SMOOTH_STONE_SLAB, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_STONE = new BlockEntry<>("chiseled_stone", () -> BlockBehaviour.Properties.copy(Blocks.STONE), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry POLISHED_STONE = new DecorBlockEntry("polished_stone", () -> BlockBehaviour.Properties.copy(Blocks.STONE), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_POLISHED_STONE = new BlockEntry<>("chiseled_polished_stone", () -> BlockBehaviour.Properties.copy(Blocks.STONE), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry STONE_TILES = new DecorBlockEntry("stone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_STONE_TILES = new DecorBlockEntry("mossy_stone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry CRACKED_STONE_TILES = new DecorBlockEntry("cracked_stone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_STONE_TILES = new BlockEntry<>("chiseled_stone_tiles", () -> BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), Block::new, SFBlocks::toItem);

		//deepslate
		public static final DecorBlockEntry MOSSY_COBBLED_DEEPSLATE = new DecorBlockEntry("mossy_cobbled_deepslate", () -> BlockBehaviour.Properties.copy(Blocks.COBBLED_DEEPSLATE), SFBlocks::toItem);
		public static final DecorBlockEntry SMOOTH_DEEPSLATE = new DecorBlockEntry("smooth_deepslate", () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), SFBlocks::toItem);
		public static final BlockEntry<PillarBlock> DEEPSLATE_PILLAR = new BlockEntry<>("deepslate_pillar", () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), PillarBlock::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_POLISHED_DEEPSLATE = new BlockEntry<>("chiseled_polished_deepslate", () -> BlockBehaviour.Properties.copy(Blocks.POLISHED_DEEPSLATE), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_DEEPSLATE_BRICKS = new BlockEntry<>("chiseled_deepslate_bricks", () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS), Block::new, SFBlocks::toItem);
		public static final BlockEntry<Block> CHISELED_DEEPSLATE_TILES = new BlockEntry<>("chiseled_deepslate_tiles", () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES), Block::new, SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_DEEPSLATE_BRICKS = new DecorBlockEntry("mossy_deepslate_bricks", () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS), SFBlocks::toItem);
		public static final DecorBlockEntry MOSSY_DEEPSLATE_TILES = new DecorBlockEntry("mossy_deepslate_tiles", () -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS), SFBlocks::toItem);

		private Stone() {
		}

		private static void init() {
		}
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);

		Bricks.init();
		Igneous.init();
		Stone.init();
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
			} else if(name.endsWith("_tiles")) {
				name = name.replaceAll("_tiles", "_tile" + postfix);
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

		private DecorBlockEntry(Supplier<BlockBehaviour.Properties> properties, RegistryObject<Block> full,
								RegistryObject<StairBlock> stairs, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall) {
			this.properties = properties;
			this.full = full;
			this.stairs = stairs;
			this.slab = slab;
			this.wall = wall;
		}

		public static DecorBlockEntry createFromFull(Block full, Function<Block, Item> toItem) {
			ResourceLocation fullId = getRegistryName(full);
			String name = fullId.getPath();
			Supplier<BlockBehaviour.Properties> properties = () -> BlockBehaviour.Properties.copy(full);
			DecorBlockEntry ret = new DecorBlockEntry(
					properties, RegistryObject.create(fullId, ForgeRegistries.BLOCKS),
					REGISTER.register(changeNameTo(name, "_stairs"), () -> new StairBlock(full::defaultBlockState, properties.get())),
					REGISTER.register(changeNameTo(name, "_slab"), () -> new SlabBlock(
							properties.get()
									.isSuffocating((state, level, pos) ->
											full.defaultBlockState().isSuffocating(level, pos) && state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE)
									.isRedstoneConductor((state, level, pos) ->
											full.defaultBlockState().isRedstoneConductor(level, pos) && state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE)
					)),
					REGISTER.register(changeNameTo(name, "_wall"), () -> new WallBlock(properties.get()))
			);
			SFItems.ItemEntry.register(changeNameTo(name, "_stairs"), () -> toItem.apply(ret.stairs.get()));
			SFItems.ItemEntry.register(changeNameTo(name, "_slab"), () -> toItem.apply(ret.slab.get()));
			SFItems.ItemEntry.register(changeNameTo(name, "_wall"), () -> toItem.apply(ret.wall.get()));

			return ret;
		}
		public static DecorBlockEntry createFromFullSlab(Block full, Block slab, Function<Block, Item> toItem) {
			ResourceLocation fullId = getRegistryName(full);
			String name = fullId.getPath();
			Supplier<BlockBehaviour.Properties> properties = () -> BlockBehaviour.Properties.copy(full);
			DecorBlockEntry ret = new DecorBlockEntry(
					properties, RegistryObject.create(fullId, ForgeRegistries.BLOCKS),
					REGISTER.register(changeNameTo(name, "_stairs"), () -> new StairBlock(full::defaultBlockState, properties.get())),
					RegistryObject.create(getRegistryName(slab), ForgeRegistries.BLOCKS),
					REGISTER.register(changeNameTo(name, "_wall"), () -> new WallBlock(properties.get()))
			);
			SFItems.ItemEntry.register(changeNameTo(name, "_stairs"), () -> toItem.apply(ret.stairs.get()));
			SFItems.ItemEntry.register(changeNameTo(name, "_wall"), () -> toItem.apply(ret.wall.get()));

			return ret;
		}
		public static DecorBlockEntry createFromFullSlabStairs(Block full, Block slab, Block stairs, Function<Block, Item> toItem) {
			ResourceLocation fullId = getRegistryName(full);
			String name = fullId.getPath();
			Supplier<BlockBehaviour.Properties> properties = () -> BlockBehaviour.Properties.copy(full);
			DecorBlockEntry ret = new DecorBlockEntry(
					properties, RegistryObject.create(fullId, ForgeRegistries.BLOCKS),
					RegistryObject.create(getRegistryName(stairs), ForgeRegistries.BLOCKS),
					RegistryObject.create(getRegistryName(slab), ForgeRegistries.BLOCKS),
					REGISTER.register(changeNameTo(name, "_wall"), () -> new WallBlock(properties.get()))
			);
			SFItems.ItemEntry.register(changeNameTo(name, "_wall"), () -> toItem.apply(ret.wall.get()));

			return ret;
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
	public static Iterable<Pair<Block, Block>> paired(Block stairs, Block slab, Block wall, IDecorGroup group) {
		return ImmutableList.of(
				Pair.of(stairs, group.getStairsBlock().get()),
				Pair.of(slab, group.getSlabBlock().get()),
				Pair.of(wall, group.getWallBlock().get())
		);
	}
	public static Iterable<Pair<Block, Block>> paired(Block full, Block stairs, Block slab, Block wall, IDecorGroup group) {
		return ImmutableList.of(
				Pair.of(full, group.getFullBlock().get()),
				Pair.of(stairs, group.getStairsBlock().get()),
				Pair.of(slab, group.getSlabBlock().get()),
				Pair.of(wall, group.getWallBlock().get())
		);
	}

	private SFBlocks() {
	}
}
