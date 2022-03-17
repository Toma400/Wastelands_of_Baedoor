package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ShamanTreatmentProcedure extends WobrModElements.ModElement {
	public ShamanTreatmentProcedure(WobrModElements instance) {
		super(instance, 1251);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShamanTreatment!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((((entity.getPersistentData().getDouble("ormath_reputation")) >= 0)
				&& ((entity.getPersistentData().getBoolean("treat_cooldown")) == (false)))
				|| ((entity.getPersistentData().getDouble("ormath_reputation")) >= 100))) {
			entity.getPersistentData().putString("shaman_msg", "Shaman heals you");
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).clearActivePotions();
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 100, (int) 9, (false), (false)));
			entity.getPersistentData().putBoolean("treat_cooldown", (true));
		} else {
			entity.getPersistentData().putString("shaman_msg", "Shaman refused");
		}
	}
}
