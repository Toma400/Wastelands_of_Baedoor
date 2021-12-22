package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class ShamanRegenerationProcedure extends WobrModElements.ModElement {
	public ShamanRegenerationProcedure(WobrModElements instance) {
		super(instance, 1187);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShamanRegeneration!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure ShamanRegeneration!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure ShamanRegeneration!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure ShamanRegeneration!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ShamanRegeneration!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) < 60)
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) > 15))) {
			if ((!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.REGENERATION)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 60, (int) 4, (false), (false)));
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, (int) 5, 3, 3, 3, 1);
				}
			}
		} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 15)) {
			if ((!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.REGENERATION)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 80, (int) 5, (false), (false)));
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, (int) 12, 3, 3, 3, 1);
				}
			}
		}
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			TribeAttackValueProcedure.executeProcedure($_dependencies);
		}
		if (((entity.getPersistentData().getDouble("bless_cooldown")) < 1200)) {
			entity.getPersistentData().putDouble("bless_cooldown", ((entity.getPersistentData().getDouble("bless_cooldown")) + 1));
		} else {
			entity.getPersistentData().putDouble("bless_cooldown", 0);
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"effect give @e[distance=..200] wobr:shaman_ormath_blessing 2 1");
			}
		}
	}
}
