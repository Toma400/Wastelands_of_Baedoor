package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class GunAddOnTableClosedProcedure extends WobrModElements.ModElement {
	public GunAddOnTableClosedProcedure(WobrModElements instance) {
		super(instance, 1504);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure GunAddOnTableClosed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putBoolean("gun_table_open", (false));
		entity.getPersistentData().putBoolean("recipe_corr", (false));
	}
}
