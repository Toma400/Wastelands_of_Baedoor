package net.mcreator.wobr.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
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
public class GunAddOnShotProcedure extends WobrModElements.ModElement {
	public GunAddOnShotProcedure(WobrModElements instance) {
		super(instance, 1310);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure GunAddOnShot!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure GunAddOnShot!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure GunAddOnShot!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure GunAddOnShot!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure GunAddOnShot!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled == (true))
				&& (ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobr_firearms").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())))) {
			if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("Gun_Locked")) == 0)) {
				if (((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobr_firearms").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))
						&& ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getBoolean("block_shot")) == (false)))) {
					if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.getDouble("Ammo")) > 0) || ((new Object() {
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
						if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getBoolean("jammed")) == (false))) {
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
									.putDouble("Ammo",
											((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
													.getOrCreateTag().getDouble("Ammo")) - 1));
							if (entity instanceof LivingEntity) {
								Entity _ent = entity;
								if (!_ent.world.isRemote) {
									BulletRangedItem.shoot(_ent.world, (LivingEntity) entity, new Random(),
											(float) (((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getHeldItemMainhand()
													: ItemStack.EMPTY).getOrCreateTag().getDouble("shot_pwr")),
											(float) (((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getHeldItemMainhand()
													: ItemStack.EMPTY).getOrCreateTag().getDouble("shot_dmg")),
											(int) (((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getHeldItemMainhand()
													: ItemStack.EMPTY).getOrCreateTag().getDouble("shot_knc")));
								}
							}
							if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getOrCreateTag().getBoolean("shot_sound")) == (true))) {
								((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
										.putBoolean("shot_trig", (true));
							} else {
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("wobr:long_revolver_shot")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("wobr:long_revolver_shot")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							}
							if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getOrCreateTag().getBoolean("notify_shot")) == (true))) {
								((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
										.putBoolean("trig_shot", (true));
							}
						} else {
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
									.putBoolean("jammed", (false));
							if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getOrCreateTag().getBoolean("notify_jam")) == (true))) {
								((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
										.putBoolean("trig_jam", (true));
							}
							if (!world.getWorld().isRemote) {
								world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("wobr:failed_shot")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								world.getWorld().playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("wobr:failed_shot")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						}
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).getCooldownTracker().setCooldown(
									((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem(),
									(int) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
											.getOrCreateTag().getDouble("shot_delay")));
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
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
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1);
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
						}
						entity.getPersistentData().putString("Message", "            Weapon out of bullets!");
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							MessageManagerProcedure.executeProcedure($_dependencies);
						}
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

	@SubscribeEvent
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
