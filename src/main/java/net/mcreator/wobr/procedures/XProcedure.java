package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class XProcedure extends WobrModElements.ModElement {
	public XProcedure(WobrModElements instance) {
		super(instance, 1785);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure X!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		WobrModVariables.MapVariables.get(world).KF_Av_Distance = (double) 5;
		WobrModVariables.MapVariables.get(world).syncData(world);
	}
}
