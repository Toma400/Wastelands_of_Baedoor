package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.PlayerMessagePotionPotion;
import net.mcreator.wobr.potion.AdditionalPlayerMessagePotionPotion;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class MessageOutcallProcedure extends WobrModElements.ModElement {
	public MessageOutcallProcedure(WobrModElements instance) {
		super(instance, 1509);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MessageOutcall!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == PlayerMessagePotionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			{
				boolean _setval = (boolean) (false);
				entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Message_Active = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == AdditionalPlayerMessagePotionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			{
				boolean _setval = (boolean) (false);
				entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Additional_Message_Active = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
