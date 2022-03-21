package net.mcreator.wobr.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.block.NetherAvoiderInactiveBlock;
import net.mcreator.wobr.block.NetherAvoiderBlock;
import net.mcreator.wobr.block.LapisAvoiderInactiveBlock;
import net.mcreator.wobr.block.LapisAvoiderActiveBlock;
import net.mcreator.wobr.block.GoldenFluidConverterTankBlock;
import net.mcreator.wobr.block.GoldenFluidConverterActiveBlock;
import net.mcreator.wobr.block.GoldenAvoiderInactiveBlock;
import net.mcreator.wobr.block.GoldenAvoiderActiveBlock;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ConfiguratorCheckProcedure extends WobrModElements.ModElement {
	public ConfiguratorCheckProcedure(WobrModElements instance) {
		super(instance, 1768);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ConfiguratorCheck!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure ConfiguratorCheck!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure ConfiguratorCheck!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure ConfiguratorCheck!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ConfiguratorCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GoldenFluidConverterTankBlock.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GoldenFluidConverterActiveBlock.block))) {
			entity.getPersistentData().putString("Message", "             Energy stored by Heater:");
			entity.getPersistentData().putString("Message2", (new java.text.DecimalFormat("#####").format((new Object() {
				public int getEnergyStored(IWorld world, BlockPos pos) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
					return _retval.get();
				}
			}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))))));
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MessageManagerProcedure.executeProcedure($_dependencies);
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				AdditionalMessageManagerProcedure.executeProcedure($_dependencies);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherAvoiderInactiveBlock.block)
				|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GoldenAvoiderInactiveBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LapisAvoiderInactiveBlock.block)))) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ConfiguratorAvoiderReducerProcedure.executeProcedure($_dependencies);
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherAvoiderInactiveBlock.block)
				|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GoldenAvoiderInactiveBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LapisAvoiderInactiveBlock.block)))) {
			entity.getPersistentData().putString("Message", "             Distance setting of avoider:");
			if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "av_distance")) == 0)) {
				entity.getPersistentData().putString("Message2",
						(new java.text.DecimalFormat("#####").format(WobrModVariables.MapVariables.get(world).KF_Av_Distance)));
			} else if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "av_distance")) < 5)) {
				entity.getPersistentData().putString("Message2", (new java.text.DecimalFormat("#####").format(0)));
			} else {
				entity.getPersistentData().putString("Message2", (new java.text.DecimalFormat("#####").format(((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "av_distance")) - 5))));
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MessageManagerProcedure.executeProcedure($_dependencies);
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				AdditionalMessageManagerProcedure.executeProcedure($_dependencies);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherAvoiderBlock.block)
				|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GoldenAvoiderActiveBlock.block)
						|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LapisAvoiderActiveBlock.block)))) {
			entity.getPersistentData().putString("Message", "         Distance setting of avoider:");
			entity.getPersistentData().putString("Message2", (new java.text.DecimalFormat("#####").format((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "av_distance")))));
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MessageManagerProcedure.executeProcedure($_dependencies);
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				AdditionalMessageManagerProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
