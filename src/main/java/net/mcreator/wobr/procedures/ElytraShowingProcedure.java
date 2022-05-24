package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.MerchantBlockElytraPotionEffect;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class ElytraShowingProcedure extends WobrModElements.ModElement {
	public ElytraShowingProcedure(WobrModElements instance) {
		super(instance, 1779);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ElytraShowing!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == MerchantBlockElytraPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)));
	}
}
