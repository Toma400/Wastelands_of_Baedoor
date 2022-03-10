package net.mcreator.wobr.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.IWorld;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@WobrModElements.ModElement.Tag
public class ConfigCreatorProcedure extends WobrModElements.ModElement {
	public ConfigCreatorProcedure(WobrModElements instance) {
		super(instance, 1812);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure ConfigCreator!");
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
		if ((WobrModVariables.MapVariables.get(world).KF_Is_Config_Here == (false))) {
			System.out.println("Wastelands of Baedoor configuration file not found. Writing.");
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
				JsonObject glistering_ash_from_mining_endstone = new JsonObject();
				glistering_ash_from_mining_endstone.addProperty("does_ash_drop", (true));
				glistering_ash_from_mining_endstone.addProperty("ash_chance", 2);
				drops.add("glistering_ash_from_mining_endstone", glistering_ash_from_mining_endstone);
				JsonObject nether_soul_essence = new JsonObject();
				nether_soul_essence.addProperty("does_essence_drop", (true));
				nether_soul_essence.addProperty("essence_chance", 8);
				drops.add("nether_soul_essence", nether_soul_essence);
				settings.add("drops", drops);
				JsonObject mob_spawn = new JsonObject();
				JsonObject merchant = new JsonObject();
				merchant.addProperty("do_merchant_spawn", (true));
				merchant.addProperty("chance_of_replacing", 20);
				mob_spawn.add("merchant", merchant);
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
			WobrModVariables.MapVariables.get(world).KF_Is_Config_Here = (boolean) (true);
			WobrModVariables.MapVariables.get(world).syncData(world);
			System.out.println("Configuration file successfully written! Version wrote: 0.");
		} else {
			System.out.println("Wastelands of Baedoor configuration file loaded. Version loaded: 0.");
		}
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("world", world);
			ConfigReaderProcedure.executeProcedure($_dependencies);
		}
	}
}
