package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class HurtTimeResetProcedure extends WobrModElements.ModElement {
	public HurtTimeResetProcedure(WobrModElements instance) {
		super(instance, 2056);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure HurtTimeReset!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("HurtTime", 0);
	}
}
