package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.WarhammerProtectionPotion;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class WarhammerFieldAttackResultProcedure extends WobrModElements.ModElement {
	public WarhammerFieldAttackResultProcedure(WobrModElements instance) {
		super(instance, 1605);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WarhammerFieldAttackResult!");
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
			entity.setMotion(0, 1, 0);
		}
	}
}
