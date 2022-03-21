package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.wobr.block.AirshipMilitaryChestBlock;
import net.mcreator.wobr.block.AirshipMerchantChestBlock;
import net.mcreator.wobr.block.AirshipBanditChestBlock;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AirshipLootTablesManagerProcedure extends WobrModElements.ModElement {
	public AirshipLootTablesManagerProcedure(WobrModElements instance) {
		super(instance, 1540);
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
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:chests/airship_bandit\"} replace");
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AirshipMerchantChestBlock.block)) {
			if ((net.minecraftforge.fml.ModList.get().isLoaded("farmersrespite"))) {
				if ((9 >= (Math.random() * 100))) {
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:airship_merchant_loot_table_tea\"} replace");
					}
				} else {
					if ((net.minecraftforge.fml.ModList.get().isLoaded("byg"))) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:airship_merchant_loot_table_byg\"} replace");
						}
					} else {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:chests/airship_merchant\"} replace");
						}
					}
				}
			} else {
				if ((net.minecraftforge.fml.ModList.get().isLoaded("byg"))) {
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:airship_merchant_loot_table_byg\"} replace");
					}
				} else {
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:chests/airship_merchant\"} replace");
					}
				}
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AirshipMilitaryChestBlock.block)) {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"setblock ~ ~ ~ minecraft:chest{LootTable:\"wobr:chests/airship_military\"} replace");
			}
		}
	}
}
