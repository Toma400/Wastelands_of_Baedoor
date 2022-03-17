package net.mcreator.wobr.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.block.SandyVeilBlock;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class SandyVeilGrowthProcedure extends WobrModElements.ModElement {
	public SandyVeilGrowthProcedure(WobrModElements instance) {
		super(instance, 635);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure SandyVeilGrowth!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure SandyVeilGrowth!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure SandyVeilGrowth!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure SandyVeilGrowth!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((WobrModVariables.Growth_Stadium <= 320)) {
			if (world instanceof World)
				world.getWorld().notifyNeighborsOfStateChange(new BlockPos((int) x, (int) y, (int) z),
						world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock());
		} else {
			if ((WobrModVariables.Growth_Stadium > 320)) {
				{
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					BlockState _bs = SandyVeilBlock.block.getDefaultState();
					world.setBlockState(_bp, _bs, 3);
				}
			} else {
				System.out.println("Global \"Growth Stadium\" out of reach.");
			}
		}
	}
}
