/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.mcreator.wobr as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.mcreator.wobr;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import net.mcreator.wobr.Config;
import net.mcreator.wobr.WobrModVariables;
import net.minecraft.world.World;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigReader {
	public ConfigReader() {
	}
	
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

			Entity entity = event.getPlayer();
			World world = entity.world;

			// AIRSHIPS
			WobrModVariables.MapVariables.get(world).KF_Str_Airship_General = Config.DO_AIRSHIPS_SPAWN.get();
			// AIRSHIPS: TYPES
			WobrModVariables.MapVariables.get(world).KF_Str_Bandit_Airship = Config.AIRSHIP_BANDIT_BOOL.get();
			WobrModVariables.MapVariables.get(world).KF_Str_Mage_Airship = Config.AIRSHIP_MAGE_BOOL.get();
			WobrModVariables.MapVariables.get(world).KF_Str_Trader_Airship = Config.AIRSHIP_MERCHANT_BOOL.get();
			WobrModVariables.MapVariables.get(world).KF_Str_Military_Airship = Config.AIRSHIP_MILITARY_BOOL.get();
			WobrModVariables.MapVariables.get(world).KF_Str_Jungle_Airship = Config.AIRSHIP_JUNGLE_BOOL.get();
			// MECHANICS
			WobrModVariables.MapVariables.get(world).KF_Av_Distance = (double) Config.AVOIDER_DISTANCE.get();
			WobrModVariables.MapVariables.get(world).KF_Av_Villager = Config.ZOMBIE_VILLAGER_KILLED.get();
			WobrModVariables.MapVariables.get(world).KF_Av_Pigman = Config.ZOMBIE_PIGMAN_KILLED.get();
			// GUN CONTROL
			WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled = Config.GUNS_ENABLED.get();
			// DROPS
			WobrModVariables.MapVariables.get(world).KF_Drop_Glister = Config.DOES_ASH_DROP.get();
			WobrModVariables.MapVariables.get(world).KF_Drop_Glister_A = (double) Config.ASH_DROP_CHANCE.get();
			WobrModVariables.MapVariables.get(world).KF_Drop_Essence = Config.DOES_ESSENCE_DROP.get();
			WobrModVariables.MapVariables.get(world).KF_Drop_Essence_A = (double) Config.ESSENCE_DROP_CHANCE.get();
			// MOBS: MERCHANT
			WobrModVariables.MapVariables.get(world).KF_Ent_Merchant = Config.DO_MERCHANT_SPAWN.get();
			WobrModVariables.MapVariables.get(world).KF_Ent_Merchant_A = (double) Config.CHANCES_OF_REPLACING.get();
			// MOBS
			WobrModVariables.MapVariables.get(world).KF_Ent_Wind_Spirit = Config.WIND_SPIRIT_SPAWN.get();
			WobrModVariables.MapVariables.get(world).KF_Ent_Orm_Raider = Config.ORMATH_RAIDERS_SPAWN.get();
			// EXPERIMENTAL
			WobrModVariables.MapVariables.get(world).KF_Xp_Developer = Config.DEVELOPERS_MODE.get();
			WobrModVariables.MapVariables.get(world).KF_Xp_Ticker = Config.NETHER_TICKER_USE.get();
			WobrModVariables.MapVariables.get(world).KF_Xp_Structures = Config.ADDITIONAL_STRUCTURES_GENERATING.get();			
			
			WobrModVariables.MapVariables.get(world).syncData(world);


			System.out.println((new java.text.DecimalFormat("####").format(WobrModVariables.MapVariables.get(world).KF_Av_Distance)));
		}
	}
}