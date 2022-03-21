package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class SlowKnifeCooldownRegenerationProcedure extends WobrModElements.ModElement {
	public SlowKnifeCooldownRegenerationProcedure(WobrModElements instance) {
		super(instance, 857);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure SlowKnifeCooldownRegeneration!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("Sneak")) <= 800)) {
			(itemstack).getOrCreateTag().putDouble("Sneak", (((itemstack).getOrCreateTag().getDouble("Sneak")) + 1));
		} else {
			(itemstack).getOrCreateTag().putBoolean("Cooldown", (false));
		}
	}
}
