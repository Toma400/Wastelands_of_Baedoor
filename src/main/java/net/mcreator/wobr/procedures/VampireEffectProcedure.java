package net.mcreator.wobr.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class VampireEffectProcedure extends WobrModElements.ModElement {
	public VampireEffectProcedure(WobrModElements instance) {
		super(instance, 1078);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				WobrMod.LOGGER.warn("Failed to load dependency sourceentity for procedure VampireEffect!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHealth() : -1) < ((sourceentity instanceof LivingEntity)
				? ((LivingEntity) sourceentity).getMaxHealth()
				: -1))) {
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity)
						.setHealth((float) (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHealth() : -1) + 3));
		}
	}
}
