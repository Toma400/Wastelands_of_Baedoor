package net.mcreator.wobr.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;
import net.mcreator.wobr.Config;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ConfigDeepReaderProcedure extends WobrModElements.ModElement {
	public ConfigDeepReaderProcedure(WobrModElements instance) {
		super(instance, 1786);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ConfigDeepReader!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		WobrModVariables.MapVariables.get(world).KF_Str_Airship_General = (boolean) Config.DO_AIRSHIPS_SPAWN.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Str_Bandit_Airship = (boolean) Config.AIRSHIP_BANDIT_BOOL.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Str_Mage_Airship = (boolean) Config.AIRSHIP_MAGE_BOOL.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Str_Trader_Airship = (boolean) Config.AIRSHIP_MERCHANT_BOOL.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Str_Military_Airship = (boolean) Config.AIRSHIP_MILITARY_BOOL.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Str_Jungle_Airship = (boolean) Config.AIRSHIP_JUNGLE_BOOL.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Av_Distance = (double) Config.AVOIDER_DISTANCE.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Av_Pigman = (boolean) Config.ZOMBIE_PIGMAN_KILLED.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Av_Villager = (boolean) Config.ZOMBIE_VILLAGER_KILLED.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled = (boolean) Config.GUNS_ENABLED.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Drop_Glister = (boolean) Config.DOES_ASH_DROP.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Drop_Glister_A = (double) Config.ASH_DROP_CHANCE.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Drop_Essence = (boolean) Config.DOES_ESSENCE_DROP.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Drop_Essence_A = (double) Config.ESSENCE_DROP_CHANCE.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Ent_Merchant = (boolean) Config.DO_MERCHANT_SPAWN.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Ent_Merchant_A = (double) Config.CHANCES_OF_REPLACING.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Ent_Wind_Spirit = (boolean) Config.WIND_SPIRIT_SPAWN.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Ent_Orm_Raider = (boolean) Config.ORMATH_RAIDERS_SPAWN.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Xp_Developer = (boolean) Config.DEVELOPERS_MODE.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Xp_Ticker = (boolean) Config.NETHER_TICKER_USE.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
		WobrModVariables.MapVariables.get(world).KF_Xp_Structures = (boolean) Config.ADDITIONAL_STRUCTURES_GENERATING.get();
		WobrModVariables.MapVariables.get(world).syncData(world);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", entity.getPosX());
		dependencies.put("y", entity.getPosY());
		dependencies.put("z", entity.getPosZ());
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
