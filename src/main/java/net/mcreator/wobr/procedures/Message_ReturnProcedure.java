package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class Message_ReturnProcedure extends WobrModElements.ModElement {
	public Message_ReturnProcedure(WobrModElements instance) {
		super(instance, 1088);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure Message_Return!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Message_Active);
	}
}
