package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.NetherAvoiderPulsatingPotion;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class AvoiderReturnProcedure extends WobrModElements.ModElement {
	public AvoiderReturnProcedure(WobrModElements instance) {
		super(instance, 1580);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AvoiderReturn!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == NetherAvoiderPulsatingPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			{
				boolean _setval = (boolean) (false);
				entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Under_Avoider = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				boolean _setval = (boolean) (false);
				entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Under_Avoider = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		return ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Under_Avoider);
	}
}
