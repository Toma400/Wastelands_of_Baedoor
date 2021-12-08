package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.wobr.block.AirshipMilitaryChestBlock;
import net.mcreator.wobr.block.AirshipMerchantChestBlock;
import net.mcreator.wobr.block.AirshipBanditChestBlock;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AirshipLootTablesManagerProcedure extends WobrModElements.ModElement {
	public AirshipLootTablesManagerProcedure(WobrModElements instance) {
		super(instance, 1890);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure AirshipLootTablesManager!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure AirshipLootTablesManager!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure AirshipLootTablesManager!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure AirshipLootTablesManager!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AirshipBanditChestBlock.block)) {
			System.out.println("Awaiting.");
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AirshipMerchantChestBlock.block)) {
			System.out.println("Awaiting.");
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AirshipMilitaryChestBlock.block)) {
			System.out.println("Awaiting.");
		}
	}
}
