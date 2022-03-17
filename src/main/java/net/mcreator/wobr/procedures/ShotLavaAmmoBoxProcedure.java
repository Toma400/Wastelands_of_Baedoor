package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.AmmoBoxLavaProjectileItem;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ShotLavaAmmoBoxProcedure extends WobrModElements.ModElement {
	public ShotLavaAmmoBoxProcedure(WobrModElements instance) {
		super(instance, 763);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShotLavaAmmoBox!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure ShotLavaAmmoBox!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ShotLavaAmmoBox!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if ((WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled == (true))) {
			if ((((itemstack).getOrCreateTag().getDouble("Ammo")) >= 20)) {
				if ((((itemstack).getOrCreateTag().getDouble("Shoot_Mode")) == 1)) {
					if (entity instanceof LivingEntity) {
						Entity _ent = entity;
						if (!_ent.world.isRemote) {
							AmmoBoxLavaProjectileItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 1, (float) 0, (int) 5);
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
