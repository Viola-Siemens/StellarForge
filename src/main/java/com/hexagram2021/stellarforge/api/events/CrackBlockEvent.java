package com.hexagram2021.stellarforge.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import org.jetbrains.annotations.Nullable;

public class CrackBlockEvent extends BlockEvent {
	@Nullable
	private BlockState target;

	public CrackBlockEvent(LevelAccessor level, BlockPos pos, BlockState state, @Nullable BlockState target) {
		super(level, pos, state);
		this.target = target;
	}

	@Nullable
	public BlockState getTarget() {
		return this.target;
	}

	public void setTarget(@Nullable BlockState target) {
		this.target = target;
	}
}
