package net.mcreator.wobr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ShamanIronGolemCrackingProcedure extends WobrModElements.ModElement {
	public ShamanIronGolemCrackingProcedure(WobrModElements instance) {
		super(instance, 1448);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ShamanIronGolemCracking!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof IronGolemEntity) || ((entity.getPersistentData().getBoolean("automaton_type")) == (true)))) {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 1000);
		}
	}
}
