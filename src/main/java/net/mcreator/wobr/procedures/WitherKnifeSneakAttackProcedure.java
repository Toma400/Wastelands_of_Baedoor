package net.mcreator.wobr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class WitherKnifeSneakAttackProcedure extends WobrModElements.ModElement {
	public WitherKnifeSneakAttackProcedure(WobrModElements instance) {
		super(instance, 1195);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WitherKnifeSneakAttack!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure WitherKnifeSneakAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getBoolean("Cooldown")) == (false))) {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 5);
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 200, (int) 1, (false), (false)));
			(itemstack).getOrCreateTag().putDouble("Sneak", 0);
			(itemstack).getOrCreateTag().putBoolean("Cooldown", (true));
		}
	}
}
