package net.mcreator.wobr.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.wobr.item.BulletRangedItem;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ShotRustyReaperProcedure extends WobrModElements.ModElement {
	public ShotRustyReaperProcedure(WobrModElements instance) {
		super(instance, 1130);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShotRustyReaper!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure ShotRustyReaper!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure ShotRustyReaper!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure ShotRustyReaper!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure ShotRustyReaper!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ShotRustyReaper!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled == (true))) {
			if ((((itemstack).getOrCreateTag().getDouble("Gun_Locked")) == 0)) {
				if (((((itemstack).getOrCreateTag().getDouble("Ammo")) > 0) || ((new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayerEntity) {
							return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
						} else if (_ent instanceof PlayerEntity && _ent.world.isRemote) {
							NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
									.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
							return _npi != null && _npi.getGameType() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(entity)) == (true)))) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						GunStatisticsProcedure.executeProcedure($_dependencies);
					}
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						FirearmExperienceProcedure.executeProcedure($_dependencies);
					}
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						FirearmExperience2Procedure.executeProcedure($_dependencies);
					}
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						$_dependencies.put("x", x);
						$_dependencies.put("y", y);
						$_dependencies.put("z", z);
						$_dependencies.put("world", world);
						FirearmJammingProcedure.executeProcedure($_dependencies);
					}
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						FastDrawUseProcedure.executeProcedure($_dependencies);
					}
					if ((((itemstack).getOrCreateTag().getBoolean("jammed")) == (false))) {
						if ((((itemstack).getOrCreateTag().getDouble("Shoot_Mode")) == 1)) {
							(itemstack).getOrCreateTag().putDouble("Ammo", (((itemstack).getOrCreateTag().getDouble("Ammo")) - 1));
							if (entity instanceof LivingEntity) {
								Entity _ent = entity;
								if (!_ent.world.isRemote) {
									BulletRangedItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 3, (float) 3, (int) 0);
								}
							}
							if (!world.getWorld().isRemote) {
								world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("wobr:crocodile_shotgun_shot")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								world.getWorld().playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("wobr:crocodile_shotgun_shot")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
							if (entity instanceof PlayerEntity)
								((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), (int) 20);
							{
								ItemStack _ist = (itemstack);
								if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
									_ist.shrink(1);
									_ist.setDamage(0);
								}
							}
						} else {
							if ((((itemstack).getOrCreateTag().getDouble("Ammo")) > 1)) {
								(itemstack).getOrCreateTag().putDouble("Ammo", (((itemstack).getOrCreateTag().getDouble("Ammo")) - 2));
								if (entity instanceof LivingEntity) {
									Entity _ent = entity;
									if (!_ent.world.isRemote) {
										BulletRangedItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 3, (float) 3, (int) 0);
									}
								}
								if (entity instanceof LivingEntity) {
									Entity _ent = entity;
									if (!_ent.world.isRemote) {
										BulletRangedItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 3, (float) 3, (int) 0);
									}
								}
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("wobr:crocodile_shotgun_shot")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("wobr:crocodile_shotgun_shot")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((((itemstack).getOrCreateTag().getDouble("Ammo")) == 1)) {
								(itemstack).getOrCreateTag().putDouble("Ammo", (((itemstack).getOrCreateTag().getDouble("Ammo")) - 1));
								if (entity instanceof LivingEntity) {
									Entity _ent = entity;
									if (!_ent.world.isRemote) {
										BulletRangedItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 3, (float) 3, (int) 0);
									}
								}
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("wobr:crocodile_shotgun_shot")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("wobr:crocodile_shotgun_shot")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
								{
									ItemStack _ist = (itemstack);
									if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
										_ist.shrink(1);
										_ist.setDamage(0);
									}
								}
							}
						}
					} else {
						(itemstack).getOrCreateTag().putBoolean("jammed", (false));
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1);
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
						}
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), (int) 20);
					}
					{
						ItemStack _ist = (itemstack);
						if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
							_ist.shrink(1);
							_ist.setDamage(0);
						}
					}
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						FirearmAccuracyProcedure.executeProcedure($_dependencies);
					}
				} else {
					entity.getPersistentData().putString("Message", "             Shotgun out of bullets!");
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						MessageManagerProcedure.executeProcedure($_dependencies);
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
				}
			} else {
				entity.getPersistentData().putString("Message", "            Weapon is locked!");
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					MessageManagerProcedure.executeProcedure($_dependencies);
				}
			}
		}
	}
}
