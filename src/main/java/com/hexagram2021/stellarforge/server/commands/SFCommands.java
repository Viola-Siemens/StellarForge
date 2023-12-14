package com.hexagram2021.stellarforge.server.commands;

import com.google.common.collect.Lists;
import com.hexagram2021.stellarforge.common.config.SFCommonConfig;
import com.hexagram2021.stellarforge.common.function.BlockFunction;
import com.hexagram2021.stellarforge.common.register.SFRegistries;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;
import java.util.function.Consumer;

public final class SFCommands {
	@SuppressWarnings("unchecked")
	public static LiteralArgumentBuilder<CommandSourceStack> register() {
		return Commands.literal("stellarforge").requires(stack -> stack.hasPermission(SFCommonConfig.COMMAND_PERMISSION.get())).then(
				Commands.literal("function").then(
						Commands.argument("func", ResourceKeyArgument.key(SFRegistries.BLOCK_FUNCTIONS)).then(
								Commands.argument("from", BlockPosArgument.blockPos()).then(
										Commands.argument("to", BlockPosArgument.blockPos())
												.executes(context -> solve(
														context.getSource().getLevel(),
														BoundingBox.fromCorners(BlockPosArgument.getLoadedBlockPos(context, "from"), BlockPosArgument.getLoadedBlockPos(context, "to")),
														context.getSource().getServer().registryAccess().lookupOrThrow(SFRegistries.BLOCK_FUNCTIONS).getOrThrow((ResourceKey<BlockFunction>)context.getArgument("func", ResourceKey.class)),
														2.0F,
														component -> context.getSource().sendSuccess(() -> component, true)
												)).then(
														Commands.argument("possibility", FloatArgumentType.floatArg())
																.executes(context -> solve(
																		context.getSource().getLevel(),
																		BoundingBox.fromCorners(BlockPosArgument.getLoadedBlockPos(context, "from"), BlockPosArgument.getLoadedBlockPos(context, "to")),
																		context.getSource().getServer().registryAccess().lookupOrThrow(SFRegistries.BLOCK_FUNCTIONS).getOrThrow((ResourceKey<BlockFunction>)context.getArgument("func", ResourceKey.class)),
																		FloatArgumentType.getFloat(context, "possibility"),
																		component -> context.getSource().sendSuccess(() -> component, true)
																))
												)
								)
						)
				)
		);
	}

	private static final Dynamic2CommandExceptionType ERROR_AREA_TOO_LARGE = new Dynamic2CommandExceptionType(
			(count, maxCount) -> Component.translatable("commands.fill.toobig", count, maxCount)
	);
	private static final SimpleCommandExceptionType ERROR_FAILED = new SimpleCommandExceptionType(Component.translatable("commands.fill.failed"));

	private static int solve(ServerLevel level, BoundingBox boundingBox, Holder<BlockFunction> blockFunction, float possibility, Consumer<Component> sendSuccess) throws CommandSyntaxException {
		int count = boundingBox.getXSpan() * boundingBox.getYSpan() * boundingBox.getZSpan();
		int maxCount = level.getGameRules().getInt(GameRules.RULE_COMMAND_MODIFICATION_BLOCK_LIMIT);
		if(count > maxCount) {
			throw ERROR_AREA_TOO_LARGE.create(count, maxCount);
		}
		List<BlockPos> list = Lists.newArrayList();
		int k = 0;
		for(BlockPos blockPos : BlockPos.betweenClosed(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ(), boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ())) {
			BlockState blockState = blockFunction.get().transfer(level, blockPos, level.getBlockState(blockPos));
			if (blockState != null && level.getRandom().nextFloat() < possibility) {
				++k;
				list.add(blockPos.immutable());
				level.setBlock(blockPos, blockState, Block.UPDATE_CLIENTS);
			}
		}

		list.forEach(blockPos -> level.blockUpdated(blockPos, level.getBlockState(blockPos).getBlock()));

		if (k == 0) {
			throw ERROR_FAILED.create();
		}
		sendSuccess.accept(Component.translatable("commands.fill.success", k));
		return k;
	}

	private SFCommands() {
	}
}
