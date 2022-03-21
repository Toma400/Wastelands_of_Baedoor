package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class LockableChestNBTEndProcedure extends WobrModElements.ModElement {
	public LockableChestNBTEndProcedure(WobrModElements instance) {
		super(instance, 1210);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure LockableChestNBTEnd!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putBoolean("l_chest_opened", (false));
	}
}
