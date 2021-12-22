package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.PlayerMessagePotionPotionEffect;
import net.mcreator.wobr.potion.AdditionalPlayerMessagePotionPotionEffect;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class MessageCalloutProcedure extends WobrModElements.ModElement {
	public MessageCalloutProcedure(WobrModElements instance) {
		super(instance, 1088);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure MessageCallout!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == PlayerMessagePotionPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			{
				boolean _setval = (boolean) (true);
				entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Message_Active = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == AdditionalPlayerMessagePotionPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			{
				boolean _setval = (boolean) (true);
				entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Additional_Message_Active = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
