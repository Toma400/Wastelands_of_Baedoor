package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class MerchantDataMergeProcedure extends WobrModElements.ModElement {
	public MerchantDataMergeProcedure(WobrModElements instance) {
		super(instance, 1471);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MerchantDataMerge!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure MerchantDataMerge!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("sourceentity", sourceentity);
			MerchantDataResetProcedure.executeProcedure($_dependencies);
		}
		sourceentity.getPersistentData().putBoolean("T_SndVeil", (entity.getPersistentData().getBoolean("T_SndVeil")));
		sourceentity.getPersistentData().putBoolean("T_BlckVeil", (entity.getPersistentData().getBoolean("T_BlckVeil")));
		sourceentity.getPersistentData().putBoolean("T_GlstrngSnd", (entity.getPersistentData().getBoolean("T_GlstrngSnd")));
		sourceentity.getPersistentData().putBoolean("T_Tsua", (entity.getPersistentData().getBoolean("T_Tsua")));
		sourceentity.getPersistentData().putBoolean("T_LngRvlvr", (entity.getPersistentData().getBoolean("T_LngRvlvr")));
		sourceentity.getPersistentData().putBoolean("T_Wndswpr", (entity.getPersistentData().getBoolean("T_Wndswpr")));
		sourceentity.getPersistentData().putBoolean("T_GhldAtRfl", (entity.getPersistentData().getBoolean("T_GhldAtRfl")));
	}
}
