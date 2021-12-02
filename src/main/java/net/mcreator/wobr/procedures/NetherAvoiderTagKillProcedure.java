package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.wobr.world.AvoiderReaperModeGameRule;
import net.mcreator.wobr.entity.BanditEntity;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class NetherAvoiderTagKillProcedure extends WobrModElements.ModElement {
	public NetherAvoiderTagKillProcedure(WobrModElements instance) {
		super(instance, 1323);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure NetherAvoiderTagKill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getWorld().getGameRules().getBoolean(AvoiderReaperModeGameRule.gamerule)) == (false))) {
			if (((((entity.getPersistentData().getBoolean("avoider_killable")) == (true)) || ((((EntityTypeTags.getCollection()
					.getOrCreate(new ResourceLocation(("forge:avoider_wobr").toLowerCase(java.util.Locale.ENGLISH))).contains(entity.getType()))
					|| ((EntityTypeTags.getCollection()
							.getOrCreate(new ResourceLocation(("forge:avoider_vanilla").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType()))
							|| (EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_vanilla_16").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))))
					|| ((entity instanceof BanditEntity.CustomEntity)
							&& (((!(entity instanceof ZombiePigmanEntity)) && (WobrModVariables.MapVariables.get(world).KF_Av_Pigman == (false)))
									|| ((!(entity instanceof BanditEntity.CustomEntity))
											&& (WobrModVariables.MapVariables.get(world).KF_Av_Villager == (false))))))
					|| (((((EntityTypeTags.getCollection()
							.getOrCreate(new ResourceLocation(("forge:avoider_mowzie").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType()))
							|| (EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_desolat").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType())))
							|| ((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_arcaneum_15").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| (EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_arcaneum").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))))
							|| (((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_atum").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| (EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_adv_adv").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType())))
									|| ((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_infernal_exp").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(
															new ResourceLocation(("forge:avoider_better_end").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType())))))
							|| ((((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_eidolon").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| (EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_eternal_tales").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType())))
									|| ((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_elvenation").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(
															("forge:avoider_ice_and_fire").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))))
									|| (((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_phytolands").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(("forge:avoider_murky").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType())))
											|| ((EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(
															("forge:avoider_better_nether").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))
													|| (EntityTypeTags.getCollection()
															.getOrCreate(new ResourceLocation(
																	("forge:avoider_xenoclus").toLowerCase(java.util.Locale.ENGLISH)))
															.contains(entity.getType()))))))))
					&& ((entity.getPersistentData().getBoolean("avoider_proof")) == (false)))) {
				entity.attackEntityFrom(DamageSource.GENERIC, (float) 1000);
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"kill @e[type=item,distance=..1]");
				}
			}
		} else {
			if (((entity instanceof BanditEntity.CustomEntity) && ((entity.getPersistentData().getBoolean("avoider_proof")) == (false)))) {
				entity.attackEntityFrom(DamageSource.GENERIC, (float) 1000);
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"kill @e[type=item,distance=..1]");
				}
			}
		}
	}
}
