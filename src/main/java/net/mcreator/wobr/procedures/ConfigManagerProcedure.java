package net.mcreator.wobr.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@WobrModElements.ModElement.Tag
public class ConfigManagerProcedure extends WobrModElements.ModElement {
	public ConfigManagerProcedure(WobrModElements instance) {
		super(instance, 2073);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ConfigManager!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		File config = new File("");
		if ((!config.exists())) {
			config = new File(("dir/config".replace("dir", FMLPaths.GAMEDIR.get().toString())), File.separator + "wobr-common.json");
			if (!config.exists()) {
				try {
					config.createNewFile();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				JsonObject settings = new JsonObject();
				settings.addProperty("config_ver", 0);
				JsonObject airship_spawn = new JsonObject();
				airship_spawn.addProperty("do_airships_spawn", (true));
				JsonObject airship_types = new JsonObject();
				airship_types.addProperty("airship_bandit", (true));
				airship_types.addProperty("airship_trader", (true));
				airship_types.addProperty("airship_military", (true));
				airship_types.addProperty("airship_mage", (true));
				airship_types.addProperty("airship_jungle", (true));
				airship_spawn.add("airship_types", airship_types);
				settings.add("airship_spawn", airship_spawn);
				JsonObject mechanics = new JsonObject();
				JsonObject avoider_settings = new JsonObject();
				avoider_settings.addProperty("avoider_distance", 50);
				avoider_settings.addProperty("avoider_cross_indicator", (false));
				avoider_settings.addProperty("zombie_villager_killed", (true));
				avoider_settings.addProperty("zombie_pigman_killed", (false));
				mechanics.add("avoider_settings", avoider_settings);
				JsonObject gun_control = new JsonObject();
				gun_control.addProperty("guns_enabled", (true));
				gun_control.addProperty("damage_scaling", 1);
				mechanics.add("gun_control", gun_control);
				settings.add("mechanics", mechanics);
				JsonObject drops = new JsonObject();
				drops.addProperty("glistering_ash_from_mining_endstone", (true));
				drops.addProperty("nether_soul_essence", (true));
				settings.add("drops", drops);
				JsonObject mob_spawn = new JsonObject();
				mob_spawn.addProperty("wind_spirit", (true));
				mob_spawn.addProperty("ormath_raiders", (true));
				settings.add("mob_spawn", mob_spawn);
				JsonObject experimental = new JsonObject();
				experimental.addProperty("developers_mode", (false));
				experimental.addProperty("nether_ticker_use", (false));
				experimental.addProperty("additional_structures_generating", (false));
				settings.add("experimental", experimental);
				try {
					FileWriter fileWriter = new FileWriter(config);
					fileWriter.write(mainGSONBuilderVariable.toJson(settings));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		} else {
			System.out.println("Wastelands of Baedoor Config Settings Loaded. Version loaded: 0.");
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
				WobrModVariables.MapVariables.get(world).KF_Wp_Gun_Enabled = (boolean) config_exist.get("airship_spawn").getAsJsonObject()
						.get("gun_control").getAsJsonObject().get("guns_enabled").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Drop_Glister = (boolean) config_exist.get("drops").getAsJsonObject()
						.get("glistering_ash_from_mining_endstone").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Drop_Essence = (boolean) config_exist.get("drops").getAsJsonObject()
						.get("nether_soul_essence").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Ent_Wind_Spirit = (boolean) config_exist.get("mob_spawn").getAsJsonObject()
						.get("wind_spirit").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
				WobrModVariables.MapVariables.get(world).KF_Ent_Orm_Raider = (boolean) config_exist.get("mob_spawn").getAsJsonObject()
						.get("ormath_raiders").getAsBoolean();
				WobrModVariables.MapVariables.get(world).syncData(world);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		World world = event.getWorld().getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("world", world);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
