package com.hexagram2021.stellarforge.common.function;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public interface BlockFunction {
	@Nullable
	BlockState transfer(ServerLevel level, BlockPos pos, BlockState state);
}
