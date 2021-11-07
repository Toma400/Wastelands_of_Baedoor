package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class WanderingDeathProcedure extends WobrModElements.ModElement {
	public WanderingDeathProcedure(WobrModElements instance) {
		super(instance, 1473);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WanderingDeath!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		WobrModVariables.MapVariables.get(world).Merchant = (boolean) (false);
		WobrModVariables.MapVariables.get(world).syncData(world);
	}
}
