package com.mraof.minestuck.world.lands.title;

import java.util.Random;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.mraof.minestuck.world.lands.decorator.SingleBlockDecorator;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;
import com.mraof.minestuck.world.lands.terrain.TerrainAspect;

public class LandAspectSilence extends TitleAspect
{
	
	@Override
	public String getPrimaryName()
	{
		return "Silence";
	}
	
	@Override
	public String[] getNames()
	{
		return new String[]{"silence"};
	}
	
	@Override
	protected void prepareChunkProvider(ChunkProviderLands chunkProvider)
	{
		chunkProvider.dayCycle = 2;
		
		chunkProvider.mergeFogColor(new Vec3(0, 0, 0.1), 0.5F);
		
		if(chunkProvider.decorators != null)
		{
			chunkProvider.decorators.add(new PumpkinDecorator());
		}
	}
	
	@Override
	public boolean isAspectCompatible(TerrainAspect aspect)
	{
		return (aspect.getWeatherType() == -1 || (aspect.getWeatherType() & 1) != 0)/*rain is noisy*/ && aspect.getDayCycleMode() != 1;
	}
	
	private static class PumpkinDecorator extends SingleBlockDecorator
	{
		@Override
		public IBlockState pickBlock(Random random)
		{
			return Blocks.pumpkin.getDefaultState().withProperty(BlockPumpkin.FACING, EnumFacing.Plane.HORIZONTAL.random(random));
		}
		@Override
		public int getBlocksForChunk(int chunkX, int chunkZ, Random random)
		{
			return random.nextFloat() < 0.01 ? 1 : 0;
		}
		@Override
		public boolean canPlace(BlockPos pos, World world)
		{
			return !world.getBlockState(pos).getBlock().getMaterial().isLiquid();
		}
	}
}