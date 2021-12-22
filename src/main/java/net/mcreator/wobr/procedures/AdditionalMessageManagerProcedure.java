package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.AdditionalPlayerMessagePotionPotionEffect;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AdditionalMessageManagerProcedure extends WobrModElements.ModElement {
	public AdditionalMessageManagerProcedure(WobrModElements instance) {
		super(instance, 1048);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure AdditionalMessageManager!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity)
					.addPotionEffect(new EffectInstance(AdditionalPlayerMessagePotionPotionEffect.potion, (int) 60, (int) 1, (false), (false)));
	}
}
