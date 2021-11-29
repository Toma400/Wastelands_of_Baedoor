package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class MerchantDataResetProcedure extends WobrModElements.ModElement {
	public MerchantDataResetProcedure(WobrModElements instance) {
		super(instance, 1515);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				WobrMod.LOGGER.warn("Failed to load dependency sourceentity for procedure MerchantDataReset!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		sourceentity.getPersistentData().putBoolean("T_SndVeil", (false));
		sourceentity.getPersistentData().putBoolean("T_BlckVeil", (false));
		sourceentity.getPersistentData().putBoolean("T_GlstrngSnd", (false));
		sourceentity.getPersistentData().putBoolean("T_Tsua", (false));
		sourceentity.getPersistentData().putBoolean("T_LngRvlvr", (false));
		sourceentity.getPersistentData().putBoolean("T_Wndswpr", (false));
		sourceentity.getPersistentData().putBoolean("T_GhldAtRfl", (false));
	}
}
