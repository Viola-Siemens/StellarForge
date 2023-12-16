package com.hexagram2021.stellarforge.common.block;

import com.hexagram2021.stellarforge.common.register.SFBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

@SuppressWarnings("deprecation")
public class PillarBlock extends RotatedPillarBlock {
	public static final BooleanProperty HEAD = SFBlockStateProperties.HEAD;
	public static final BooleanProperty HEEL = SFBlockStateProperties.HEEL;

	public PillarBlock(Properties props) {
		super(props);
		this.registerDefaultState(this.defaultBlockState().setValue(HEAD, true).setValue(HEEL, true));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HEAD).add(HEEL);
	}

	@Override
	public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState oldBlockState, boolean piston) {
		for(Direction direction : Direction.values()) {
			level.updateNeighborsAt(blockPos.relative(direction), this);
		}
	}

	@Override
	public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block neighbor, BlockPos neighborPos, boolean piston) {
		Direction.Axis axis = blockState.getValue(AXIS);
		Direction delta = Direction.fromDelta(blockPos.getX() - neighborPos.getX(), blockPos.getY() - neighborPos.getY(), blockPos.getZ() - neighborPos.getZ());

		if(delta != null && axis.test(delta)) {
			Direction.AxisDirection axisDirection = delta.getAxisDirection();
			BooleanProperty effected = axisDirection.getStep() > 0 ? HEEL : HEAD;
			BlockState neighborState = level.getBlockState(neighborPos);
			boolean flag = neighborState.getBlock() == this && neighborState.hasProperty(AXIS) && neighborState.getValue(AXIS) == axis;
			if(blockState.getValue(effected) == flag) {
				level.setBlockAndUpdate(blockPos, blockState.setValue(effected, !flag));
			}
		}
	}
}
