package com.hexagram2021.stellarforge.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ChiselBlockEvent extends BlockFunctionBlockEvent {
	public ChiselBlockEvent(LevelAccessor level, BlockPos pos, BlockState state, @Nullable BlockState target) {
		super(level, pos, state, target);
	}
}
