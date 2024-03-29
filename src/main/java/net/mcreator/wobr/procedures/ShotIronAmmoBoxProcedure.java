package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.AmmoBoxIronProjectileItem;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ShotIronAmmoBoxProcedure extends WobrModElements.ModElement {
	public ShotIronAmmoBoxProcedure(WobrModElements instance) {
		super(instance, 763);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShotIronAmmoBox!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure ShotIronAmmoBox!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ShotIronAmmoBox!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if ((WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled == (true))) {
			if ((((itemstack).getOrCreateTag().getDouble("Ammo")) >= 30)) {
				if ((((itemstack).getOrCreateTag().getDouble("Shoot_Mode")) == 1)) {
					if (entity instanceof LivingEntity) {
						Entity _ent = entity;
						if (!_ent.world.isRemote) {
							AmmoBoxIronProjectileItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 1, (float) 0, (int) 3);
						}
					}
					((itemstack)).shrink((int) 1);
				} else {
					entity.getPersistentData().putString("Message", "You are in mode not allowing you to throw!");
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						MessageManagerProcedure.executeProcedure($_dependencies);
					}
				}
			} else {
				entity.getPersistentData().putString("Message", "          Not enough bullets to throw!");
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					MessageManagerProcedure.executeProcedure($_dependencies);
				}
			}
		}
	}
}
