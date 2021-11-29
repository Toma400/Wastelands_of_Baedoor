package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.CurarePoisonPotionEffect;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class RodoftheWindsPoisonProcedure extends WobrModElements.ModElement {
	public RodoftheWindsPoisonProcedure(WobrModElements instance) {
		super(instance, 1403);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure RodoftheWindsPoison!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((5 > (Math.random() * 100))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(CurarePoisonPotionEffect.potion, (int) 400, (int) 1, (false), (false)));
		}
	}
}
