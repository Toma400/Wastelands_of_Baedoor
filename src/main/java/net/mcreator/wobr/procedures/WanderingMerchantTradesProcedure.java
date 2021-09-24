package net.mcreator.wobr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wobr.entity.WanderingMerchantFirearmsEntity;
import net.mcreator.wobr.entity.WanderingMerchantEntity;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class WanderingMerchantTradesProcedure extends WobrModElements.ModElement {
	public WanderingMerchantTradesProcedure(WobrModElements instance) {
		super(instance, 1393);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WanderingMerchantTrades!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity instanceof WanderingMerchantEntity.CustomEntity)) {
			if ((33 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_SndVeil", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((33 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_BlckVeil", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((25 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_GlstrngSnd", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((25 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_Tsua", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((20 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_LngRvlvr", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((20 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_Glow_Obs", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((20 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_Am_Cluster", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
		}
		if ((entity instanceof WanderingMerchantFirearmsEntity.CustomEntity)) {
			if ((25 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_SndVeil", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((25 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_BlckVeil", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((25 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_GlstrngSnd", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((40 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_LngRvlvr", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((33 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_Wndswpr", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
			if ((25 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_GhldAtRfl", (true));
				entity.getPersistentData().putBoolean("Have_Trade", (true));
			}
		}
		if (((entity.getPersistentData().getBoolean("Have_Trade")) == (false))) {
			if ((50 >= (Math.random() * 100))) {
				entity.getPersistentData().putBoolean("T_SndVeil", (true));
			} else {
				entity.getPersistentData().putBoolean("T_BlckVeil", (true));
			}
		}
	}
}
