package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AirshipBanditConditionProcedure extends WobrModElements.ModElement {
	public AirshipBanditConditionProcedure(WobrModElements instance) {
		super(instance, 1605);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure AirshipBanditCondition!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		return (WobrModVariables.MapVariables.get(world).KF_Str_Airship_General && WobrModVariables.MapVariables.get(world).KF_Str_Bandit_Airship);
	}
}
