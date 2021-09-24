package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AddOnStelliumBehaviourProcedure extends WobrModElements.ModElement {
	public AddOnStelliumBehaviourProcedure(WobrModElements instance) {
		super(instance, 1681);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AddOnStelliumBehaviour!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.dimension.getId()) == (1))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, (int) 25, (int) 1, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 25, (int) 1, (false), (false)));
		}
	}
}
