package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class MainGUINBTProcedure extends WobrModElements.ModElement {
	public MainGUINBTProcedure(WobrModElements instance) {
		super(instance, 1513);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure MainGUINBT!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("merchant_gui", 1);
	}
}
