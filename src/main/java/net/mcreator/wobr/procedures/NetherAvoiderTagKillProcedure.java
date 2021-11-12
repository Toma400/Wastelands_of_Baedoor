package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.wobr.potion.NetherAvoiderHiddenPotion;
import net.mcreator.wobr.entity.WindSpiritEntity;
import net.mcreator.wobr.entity.BanditEntity;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class NetherAvoiderTagKillProcedure extends WobrModElements.ModElement {
	public NetherAvoiderTagKillProcedure(WobrModElements instance) {
		super(instance, 1323);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure NetherAvoiderTagKill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((WobrModVariables.WorldVariables.get(world).Avoider_Reapering) == (false)) || (new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == NetherAvoiderHiddenPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			if (((((entity.getPersistentData().getBoolean("avoider_killable")) == (true)) || (((((entity instanceof SlimeEntity)
					|| (entity instanceof BlazeEntity)) || ((entity instanceof GhastEntity) || (entity instanceof MagmaCubeEntity)))
					|| ((entity instanceof WitherSkeletonEntity) || (entity instanceof HuskEntity)))
					|| ((((entity instanceof BanditEntity.CustomEntity) || (entity instanceof WindSpiritEntity.CustomEntity))
							|| ((entity instanceof SkeletonEntity) || (entity instanceof SpiderEntity)))
							|| ((((entity instanceof ZombieEntity) && (!(entity instanceof ZombiePigmanEntity))) || (entity instanceof CreeperEntity))
									|| ((entity instanceof WitchEntity) || (entity instanceof EndermanEntity))))))
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
			if (((!(((entity instanceof PlayerEntity) || (entity instanceof ServerPlayerEntity)) || (entity instanceof VillagerEntity)))
					&& ((entity.getPersistentData().getBoolean("avoider_proof")) == (false)))) {
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
