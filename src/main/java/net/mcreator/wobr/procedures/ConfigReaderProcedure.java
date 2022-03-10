package net.mcreator.wobr.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

@WobrModElements.ModElement.Tag
public class ConfigReaderProcedure extends WobrModElements.ModElement {
	public ConfigReaderProcedure(WobrModElements instance) {
		super(instance, 1813);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ConfigReader!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		File config = new File("");
		config = new File(
				((FMLPaths.GAMEDIR.get().toString()) + "" + ("/saves/") + "" + (world.getWorldInfo().getWorldName()) + "" + ("/serverconfig/")),
				File.separator + "wobr-common.json");
		if (!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(config));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				JsonObject config_exist = new Gson().fromJson(jsonstringbuilder.toString(), JsonObject.class);
				WobrModVariables.MapVariables.get(world).KF_Av_Distance = (double) config_exist.get("mechanics").getAsJsonObject()
						.get("avoider_settings").getAsJsonObject().get("avoider_distance").getAsDouble();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Av_Villager = (boolean) config_exist.get("mechanics").getAsJsonObject()
						.get("avoider_settings").getAsJsonObject().get("zombie_villager_killed").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Av_Pigman = (boolean) config_exist.get("mechanics").getAsJsonObject()
						.get("avoider_settings").getAsJsonObject().get("zombie_pigman_killed").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Str_Airship_General = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("do_airships_spawn").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Str_Bandit_Airship = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("airship_types").getAsJsonObject().get("airship_bandit").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Str_Mage_Airship = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("airship_types").getAsJsonObject().get("airship_mage").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Str_Trader_Airship = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("airship_types").getAsJsonObject().get("airship_trader").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Str_Military_Airship = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("airship_types").getAsJsonObject().get("airship_military").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Str_Jungle_Airship = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("airship_types").getAsJsonObject().get("airship_jungle").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Dmg = (double) config_exist.get("mechanics").getAsJsonObject().get("gun_control")
						.getAsJsonObject().get("damage_scaling").getAsDouble();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled = (boolean) config_exist.get("mechanics").getAsJsonObject()
						.get("gun_control").getAsJsonObject().get("guns_enabled").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Drop_Glister = (boolean) config_exist.get("drops").getAsJsonObject()
						.get("glistering_ash_from_mining_endstone").getAsJsonObject().get("does_ash_drop").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Drop_Glister_A = (double) config_exist.get("drops").getAsJsonObject()
						.get("glistering_ash_from_mining_endstone").getAsJsonObject().get("ash_chance").getAsDouble();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Drop_Essence = (boolean) config_exist.get("drops").getAsJsonObject()
						.get("nether_soul_essence").getAsJsonObject().get("does_essence_drop").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Drop_Essence_A = (double) config_exist.get("drops").getAsJsonObject()
						.get("nether_soul_essence").getAsJsonObject().get("essence_chance").getAsDouble();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Ent_Wind_Spirit = (boolean) config_exist.get("mob_spawn").getAsJsonObject()
						.get("wind_spirit").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Ent_Orm_Raider = (boolean) config_exist.get("mob_spawn").getAsJsonObject()
						.get("ormath_raiders").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Ent_Merchant = (boolean) config_exist.get("mob_spawn").getAsJsonObject().get("merchant")
						.getAsJsonObject().get("do_merchant_spawn").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Ent_Merchant_A = (double) config_exist.get("mob_spawn").getAsJsonObject().get("merchant")
						.getAsJsonObject().get("chance_of_replacing").getAsDouble();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Xp_Structures = (boolean) config_exist.get("experimental").getAsJsonObject()
						.get("additional_structures_generating").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Xp_Developer = (boolean) config_exist.get("experimental").getAsJsonObject()
						.get("developers_mode").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Xp_Ticker = (boolean) config_exist.get("experimental").getAsJsonObject()
						.get("nether_ticker_use").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
