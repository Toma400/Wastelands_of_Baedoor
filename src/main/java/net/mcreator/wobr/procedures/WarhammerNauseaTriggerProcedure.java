package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.WarhammerProtectionPotion;
import net.mcreator.wobr.potion.WarhammerNauseaWalkPotion;
import net.mcreator.wobr.potion.WarhammerNauseaTriggeredPotion;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class WarhammerNauseaTriggerProcedure extends WobrModElements.ModElement {
	public WarhammerNauseaTriggerProcedure(WobrModElements instance) {
		super(instance, 1602);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WarhammerNauseaTrigger!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == WarhammerProtectionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(WarhammerNauseaWalkPotion.potion, (int) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == WarhammerNauseaTriggeredPotion.potion)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity)), (int) 1, (false), (false)));
		}
	}
}
