package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.CurarePoisonPotion;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ShamanProjectileEntityProcedure extends WobrModElements.ModElement {
	public ShamanProjectileEntityProcedure(WobrModElements instance) {
		super(instance, 1056);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ShamanProjectileEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).clearActivePotions();
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).removePotionEffect(Effects.LEVITATION);
		}
		WobrModVariables.For_Random_Uses = (double) (Math.random() * 100);
		if (((WobrModVariables.For_Random_Uses) <= 5)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(CurarePoisonPotion.potion, (int) 60, (int) 1, (false), (true)));
		} else if ((((WobrModVariables.For_Random_Uses) <= 25) && ((WobrModVariables.For_Random_Uses) > 5))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, (int) 60, (int) 1, (false), (true)));
		} else if ((((WobrModVariables.For_Random_Uses) <= 60) && ((WobrModVariables.For_Random_Uses) > 25))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 1, (false), (true)));
		} else if ((((WobrModVariables.For_Random_Uses) <= 75) && ((WobrModVariables.For_Random_Uses) > 60))) {
			entity.setFire((int) 6);
		}
	}
}
