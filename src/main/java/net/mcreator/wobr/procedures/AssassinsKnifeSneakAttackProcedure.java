package net.mcreator.wobr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AssassinsKnifeSneakAttackProcedure extends WobrModElements.ModElement {
	public AssassinsKnifeSneakAttackProcedure(WobrModElements instance) {
		super(instance, 1008);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure AssassinsKnifeSneakAttack!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure AssassinsKnifeSneakAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getBoolean("Cooldown")) == (false))) {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 22);
			(itemstack).getOrCreateTag().putDouble("Sneak", 0);
			(itemstack).getOrCreateTag().putBoolean("Cooldown", (true));
		}
	}
}
