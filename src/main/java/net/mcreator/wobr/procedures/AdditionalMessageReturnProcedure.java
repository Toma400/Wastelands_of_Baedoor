package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AdditionalMessageReturnProcedure extends WobrModElements.ModElement {
	public AdditionalMessageReturnProcedure(WobrModElements instance) {
		super(instance, 1296);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AdditionalMessageReturn!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Additional_Message_Active);
	}
}
