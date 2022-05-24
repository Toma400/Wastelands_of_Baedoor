package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.MerchantBlockShulkerShellsPotionEffect;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class ShulkerBoxesShowingProcedure extends WobrModElements.ModElement {
	public ShulkerBoxesShowingProcedure(WobrModElements instance) {
		super(instance, 1781);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShulkerBoxesShowing!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == MerchantBlockShulkerShellsPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)));
	}
}
