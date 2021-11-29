package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class BuffaloRideProcedure extends WobrModElements.ModElement {
	public BuffaloRideProcedure(WobrModElements instance) {
		super(instance, 1480);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure BuffaloRide!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.isBeingRidden())) {
			entity.setSprinting((false));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 40, (int) 1, (false), (false)));
		}
	}
}
