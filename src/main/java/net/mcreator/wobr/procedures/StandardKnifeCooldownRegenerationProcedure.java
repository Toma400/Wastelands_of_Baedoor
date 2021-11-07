package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class StandardKnifeCooldownRegenerationProcedure extends WobrModElements.ModElement {
	public StandardKnifeCooldownRegenerationProcedure(WobrModElements instance) {
		super(instance, 1136);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure StandardKnifeCooldownRegeneration!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("Sneak")) <= 600)) {
			(itemstack).getOrCreateTag().putDouble("Sneak", (((itemstack).getOrCreateTag().getDouble("Sneak")) + 1));
		} else {
			(itemstack).getOrCreateTag().putBoolean("Cooldown", (false));
		}
	}
}
