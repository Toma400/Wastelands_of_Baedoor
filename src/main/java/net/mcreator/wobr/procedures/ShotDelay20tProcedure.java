package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ShotDelay20tProcedure extends WobrModElements.ModElement {
	public ShotDelay20tProcedure(WobrModElements instance) {
		super(instance, 734);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShotDelay20t!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure ShotDelay20t!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), (int) 20);
	}
}
