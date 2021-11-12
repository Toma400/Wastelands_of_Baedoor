package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class WarhammerNauseaEndProcedure extends WobrModElements.ModElement {
	public WarhammerNauseaEndProcedure(WobrModElements instance) {
		super(instance, 1718);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WarhammerNauseaEnd!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 20, (int) 1, (true), (false)));
		entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), (entity.getMotion().getZ()));
	}
}
