package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.TribeReputationRaisePotion;
import net.mcreator.wobr.potion.TribeReputationDropPotion;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class TribeAttackValueProcedure extends WobrModElements.ModElement {
	public TribeAttackValueProcedure(WobrModElements instance) {
		super(instance, 1368);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TribeAttackValue!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == TribeReputationDropPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			entity.getPersistentData().putDouble("tribe_reputation",
					((entity.getPersistentData().getDouble("tribe_reputation")) - (25 * (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == TribeReputationDropPotion.potion)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity)))));
		}
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == TribeReputationRaisePotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			if (((entity.getPersistentData().getDouble("tribe_reputation")) < 250)) {
				entity.getPersistentData().putDouble("tribe_reputation",
						((entity.getPersistentData().getDouble("tribe_reputation")) + (1 * (new Object() {
							int check(Entity _entity) {
								if (_entity instanceof LivingEntity) {
									Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
									for (EffectInstance effect : effects) {
										if (effect.getPotion() == TribeReputationRaisePotion.potion)
											return effect.getAmplifier();
									}
								}
								return 0;
							}
						}.check(entity)))));
			}
		}
		if (((entity.getPersistentData().getDouble("tribe_reputation")) <= (-50))) {
			entity.getPersistentData().putBoolean("tribe_attack", (true));
		} else {
			entity.getPersistentData().putBoolean("tribe_attack", (false));
		}
		return (entity.getPersistentData().getBoolean("tribe_attack"));
	}
}
