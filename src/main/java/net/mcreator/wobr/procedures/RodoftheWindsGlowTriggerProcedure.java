package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.RodoftheWindsGlowPotionEffect;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class RodoftheWindsGlowTriggerProcedure extends WobrModElements.ModElement {
	public RodoftheWindsGlowTriggerProcedure(WobrModElements instance) {
		super(instance, 1292);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure RodoftheWindsGlowTrigger!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == RodoftheWindsGlowPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			entity.getPersistentData().putBoolean("rod_glow", (true));
		} else if (((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == RodoftheWindsGlowPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) && ((entity.getPersistentData().getBoolean("rod_glow")) == (true)))) {
			entity.getPersistentData().putBoolean("rod_glow", (false));
		}
		return (entity.getPersistentData().getBoolean("rod_glow"));
	}
}
