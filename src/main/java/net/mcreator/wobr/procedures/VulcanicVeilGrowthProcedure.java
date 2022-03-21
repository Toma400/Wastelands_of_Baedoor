package net.mcreator.wobr.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.block.VulcanicVeilBlock;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class VulcanicVeilGrowthProcedure extends WobrModElements.ModElement {
	public VulcanicVeilGrowthProcedure(WobrModElements instance) {
		super(instance, 637);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure VulcanicVeilGrowth!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure VulcanicVeilGrowth!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure VulcanicVeilGrowth!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure VulcanicVeilGrowth!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getDimension().getType().getId()) == (-1))) {
			if ((WobrModVariables.Growth_Stadium <= 330)) {
				if (world instanceof World)
					world.getWorld().notifyNeighborsOfStateChange(new BlockPos((int) x, (int) y, (int) z),
							world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock());
				if (((WobrModVariables.Growth_Stadium > 330) || (WobrModVariables.Growth_Stadium > 160))) {
					{
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockState _bs = VulcanicVeilBlock.block.getDefaultState();
						world.setBlockState(_bp, _bs, 3);
					}
				}
			}
		} else {
			if ((WobrModVariables.Growth_Stadium <= 330)) {
				if (world instanceof World)
					world.getWorld().notifyNeighborsOfStateChange(new BlockPos((int) x, (int) y, (int) z),
							world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock());
			} else {
				if ((WobrModVariables.Growth_Stadium > 330)) {
					{
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						BlockState _bs = VulcanicVeilBlock.block.getDefaultState();
						world.setBlockState(_bp, _bs, 3);
					}
				} else {
					System.out.println("Global \"Growth Stadium\" out of reach.");
				}
			}
		}
	}
}
