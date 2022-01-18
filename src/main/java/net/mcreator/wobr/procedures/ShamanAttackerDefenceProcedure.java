package net.mcreator.wobr.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ShamanAttackerDefenceProcedure extends WobrModElements.ModElement {
	public ShamanAttackerDefenceProcedure(WobrModElements instance) {
		super(instance, 1783);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShamanAttackerDefence!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (!(entity instanceof PlayerEntity));
	}
}
