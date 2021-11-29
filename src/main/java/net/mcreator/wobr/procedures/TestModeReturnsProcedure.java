package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class TestModeReturnsProcedure extends WobrModElements.ModElement {
	public TestModeReturnsProcedure(WobrModElements instance) {
		super(instance, 1388);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure TestModeReturns!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		return WobrModVariables.MapVariables.get(world).Test_Features;
	}
}
