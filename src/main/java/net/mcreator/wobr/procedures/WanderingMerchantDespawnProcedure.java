package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class WanderingMerchantDespawnProcedure extends WobrModElements.ModElement {
	public WanderingMerchantDespawnProcedure(WobrModElements instance) {
		super(instance, 1512);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WanderingMerchantDespawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WanderingMerchantDespawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getDouble("dspwn_time")) < 48000)) {
			entity.getPersistentData().putDouble("dspwn_time", ((entity.getPersistentData().getDouble("dspwn_time")) + 1));
		} else {
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 1000);
			WobrModVariables.MapVariables.get(world).Merchant = (boolean) (false);
			WobrModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
