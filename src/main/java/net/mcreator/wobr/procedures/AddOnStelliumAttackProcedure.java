package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AddOnStelliumAttackProcedure extends WobrModElements.ModElement {
	public AddOnStelliumAttackProcedure(WobrModElements instance) {
		super(instance, 1682);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AddOnStelliumAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.dimension.getId()) == (1))) {
			if ((Math.random() >= 0.2)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, (int) 60, (int) 1));
			}
		}
	}
}
