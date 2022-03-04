package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.CirtainIngotItem;
import net.mcreator.wobr.entity.BanditEntity;
import net.mcreator.wobr.entity.BanditDespawningEntity;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@WobrModElements.ModElement.Tag
public class GiftsFromAirshipMerchantsProcedure extends WobrModElements.ModElement {
	public GiftsFromAirshipMerchantsProcedure(WobrModElements instance) {
		super(instance, 1799);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure GiftsFromAirshipMerchants!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure GiftsFromAirshipMerchants!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure GiftsFromAirshipMerchants!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure GiftsFromAirshipMerchants!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure GiftsFromAirshipMerchants!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getBoolean("WasGiftGiven")) == (false))) {
			if (((!(((Entity) world
					.getEntitiesWithinAABB(BanditDespawningEntity.CustomEntity.class,
							new AxisAlignedBB(x - (125 / 2d), y - (125 / 2d), z - (125 / 2d), x + (125 / 2d), y + (125 / 2d), z + (125 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null))
					&& (!(((Entity) world.getEntitiesWithinAABB(BanditEntity.CustomEntity.class,
							new AxisAlignedBB(x - (125 / 2d), y - (125 / 2d), z - (125 / 2d), x + (125 / 2d), y + (125 / 2d), z + (125 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null)))) {
				entity.getPersistentData().putBoolean("WasGiftGiven", (true));
				if (!world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(CirtainIngotItem.block));
					entityToSpawn.setPickupDelay((int) 10);
					entityToSpawn.setNoDespawn();
					world.addEntity(entityToSpawn);
				}
			}
		}
	}
}
