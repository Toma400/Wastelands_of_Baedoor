package net.mcreator.wobr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class WanderingMerchantDespawnProcedure extends WobrModElements.ModElement {
	public WanderingMerchantDespawnProcedure(WobrModElements instance) {
		super(instance, 1266);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure WanderingMerchantDespawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("dspwn_time")) < 36000)) {
			entity.getPersistentData().putDouble("dspwn_time", ((entity.getPersistentData().getDouble("dspwn_time")) + 1));
		} else {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 1000);
		}
	}
}
