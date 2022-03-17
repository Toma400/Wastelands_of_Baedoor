package net.mcreator.wobr.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.SmallBulletItem;
import net.mcreator.wobr.item.SlugItem;
import net.mcreator.wobr.item.LargeBulletItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class GunAddOnReloadProcedure extends WobrModElements.ModElement {
	public GunAddOnReloadProcedure(WobrModElements instance) {
		super(instance, 1296);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure GunAddOnReload!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure GunAddOnReload!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure GunAddOnReload!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure GunAddOnReload!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure GunAddOnReload!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double custom_ammo = 0;
		if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobr_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
			if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("Ammo")) < (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getOrCreateTag().getDouble("max_ammo")))) {
				if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("ammo_using"))).equals("Small_Bullet"))) {
					if (((entity instanceof PlayerEntity)
							? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(SmallBulletItem.block))
							: false)) {
						if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getString("rld_type"))).equals("Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("Quick_Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldFastSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("All_In_Once"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldAllOnceProcedure.executeProcedure($_dependencies);
							}
						}
					} else {
						entity.getPersistentData().putString("Message", "              No bullets in inventory!");
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							MessageManagerProcedure.executeProcedure($_dependencies);
						}
					}
				} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("ammo_using"))).equals("Large_Bullet"))) {
					if (((entity instanceof PlayerEntity)
							? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(LargeBulletItem.block))
							: false)) {
						if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getString("rld_type"))).equals("Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("Quick_Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldFastSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("All_In_Once"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldAllOnceProcedure.executeProcedure($_dependencies);
							}
						}
					} else {
						entity.getPersistentData().putString("Message", "              No bullets in inventory!");
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							MessageManagerProcedure.executeProcedure($_dependencies);
						}
					}
				} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("ammo_using"))).equals("Slug"))) {
					if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(SlugItem.block)) : false)) {
						if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getString("rld_type"))).equals("Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("Quick_Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldFastSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("All_In_Once"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldAllOnceProcedure.executeProcedure($_dependencies);
							}
						}
					} else {
						entity.getPersistentData().putString("Message", "              No bullets in inventory!");
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							MessageManagerProcedure.executeProcedure($_dependencies);
						}
					}
				} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("ammo_using"))).equals("Gunpowder"))) {
					if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.GUNPOWDER)) : false)) {
						if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getString("rld_type"))).equals("Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("Quick_Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldFastSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("All_In_Once"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldAllOnceProcedure.executeProcedure($_dependencies);
							}
						}
					} else {
						entity.getPersistentData().putString("Message", "              No bullets in inventory!");
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							MessageManagerProcedure.executeProcedure($_dependencies);
						}
					}
				} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("ammo_using"))).equals("Custom"))) {
					custom_ammo = (double) 0;
					{
						AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _iitemhandlerref.set(capability));
						if (_iitemhandlerref.get() != null) {
							for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
								ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
								if ((((((itemstackiterator).getDisplayName().getString()))
										.equals((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getOrCreateTag().getString("ammo_name"))))
										&& (ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(("forge:wobr_ammo").toLowerCase(java.util.Locale.ENGLISH)))
												.contains((itemstackiterator).getItem())))) {
									custom_ammo = (double) (custom_ammo + (((itemstackiterator)).getCount()));
								}
							}
						}
					}
					if ((custom_ammo > 0)) {
						if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getString("rld_type"))).equals("Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("Quick_Single"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldFastSingleProcedure.executeProcedure($_dependencies);
							}
						} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getString("rld_type"))).equals("All_In_Once"))) {
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("entity", entity);
								$_dependencies.put("x", x);
								$_dependencies.put("y", y);
								$_dependencies.put("z", z);
								$_dependencies.put("world", world);
								GunAddOnRldAllOnceProcedure.executeProcedure($_dependencies);
							}
						}
					} else {
						entity.getPersistentData().putString("Message", "              No bullets in inventory!");
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							MessageManagerProcedure.executeProcedure($_dependencies);
						}
					}
				}
			} else {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("wobr:revolver_reload_full_1")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("wobr:revolver_reload_full_1")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("reloadscore");
					if (_so == null) {
						_so = _sc.addObjective("reloadscore", ScoreCriteria.DUMMY, new StringTextComponent("reloadscore"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 0);
				}
			}
		}
	}
}
