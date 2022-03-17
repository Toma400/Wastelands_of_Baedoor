package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class CirtainResistanceProcedure extends WobrModElements.ModElement {
	public CirtainResistanceProcedure(WobrModElements instance) {
		super(instance, 1738);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure CirtainResistance!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		(itemstack).getOrCreateTag().putBoolean("Invulnerable", (true));
	}
}
