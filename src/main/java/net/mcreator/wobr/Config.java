package net.mcreator.wobr;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import java.io.File;

import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.fml.ModLoadingContext;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

	public static final String WORLD_CONFIG_PATH = FMLPaths.GAMEDIR.get().resolve("wobr-server.toml").toString();
	// ------------------------------------------------------------------
	// CATEGORIES FOR VALUES
	// ------------------------------------------------------------------
	// Worldgen
	public static final String TYPE_AIRSHIP = "Airship Spawning";
	public static final String SUBTYPE_AIRSHIP_BOOLEANS = "Airship Types";
	public static final String SUBTYPE_AIRSHIP_CHANCES = "Airship Weights";
	// Mechanics
	public static final String TYPE_MECHANICS = "Mechanics";
	public static final String SUBTYPE_AVOIDER = "Avoider Settings";
	public static final String SUBTYPE_GUN_CONTROL = "Gun Control";
	// Drops
	public static final String TYPE_LOOT = "Loot";
	public static final String SUBTYPE_GLISTERING_ASH = "Glistering Ash";
	public static final String SUBTYPE_NETHER_SOUL_ESSENCE = "Nether Soul Essence";
	// Entities
	public static final String TYPE_MOB_SPAWN = "Mob Spawning";
	public static final String SUBTYPE_MERCHANT = "Wandering Merchant";
	// Others
	public static final String TYPE_EXPERIMENTAL = "Experimental";
	private static final Integer VERSION = 0;
	public static ForgeConfigSpec WORLD_CONFIG;
	// ------------------------------------------------------------------
	
	// ------------------------------------------------------------------
	// VALUES
	// ------------------------------------------------------------------
	// Worldgen: General
	public static ForgeConfigSpec.BooleanValue DO_AIRSHIPS_SPAWN;
	// Worldgen: Airship Booleans
	public static ForgeConfigSpec.BooleanValue AIRSHIP_BANDIT_BOOL;
	public static ForgeConfigSpec.BooleanValue AIRSHIP_MERCHANT_BOOL;
	public static ForgeConfigSpec.BooleanValue AIRSHIP_MILITARY_BOOL;
	public static ForgeConfigSpec.BooleanValue AIRSHIP_MAGE_BOOL;
	public static ForgeConfigSpec.BooleanValue AIRSHIP_JUNGLE_BOOL;
	// Worldgen: Airship Chances
	public static ForgeConfigSpec.IntValue AIRSHIP_BANDIT_CHANCE;
	public static ForgeConfigSpec.IntValue AIRSHIP_MERCHANT_CHANCE;
	public static ForgeConfigSpec.IntValue AIRSHIP_MILITARY_CHANCE;
	public static ForgeConfigSpec.IntValue AIRSHIP_MAGE_CHANCE;
	public static ForgeConfigSpec.IntValue AIRSHIP_JUNGLE_CHANCE;
	// Mechanics: Avoider
	public static ForgeConfigSpec.IntValue AVOIDER_DISTANCE;
	public static ForgeConfigSpec.BooleanValue ZOMBIE_VILLAGER_KILLED;
	public static ForgeConfigSpec.BooleanValue ZOMBIE_PIGMAN_KILLED;
	// Mechanics: Gun Control
	public static ForgeConfigSpec.BooleanValue GUNS_ENABLED;
	// Drops: Glistering Ash
	public static ForgeConfigSpec.BooleanValue DOES_ASH_DROP;
	public static ForgeConfigSpec.IntValue ASH_DROP_CHANCE;
	// Drops: Nether Soul Essence
	public static ForgeConfigSpec.BooleanValue DOES_ESSENCE_DROP;
	public static ForgeConfigSpec.IntValue ESSENCE_DROP_CHANCE;
	// Entities: Wandering Merchant
	public static ForgeConfigSpec.BooleanValue DO_MERCHANT_SPAWN;
	public static ForgeConfigSpec.IntValue CHANCES_OF_REPLACING;
	// Entities: Other
	public static ForgeConfigSpec.BooleanValue WIND_SPIRIT_SPAWN;
	public static ForgeConfigSpec.BooleanValue ORMATH_RAIDERS_SPAWN;
	// Experimental
	public static ForgeConfigSpec.BooleanValue DEVELOPERS_MODE;
	public static ForgeConfigSpec.BooleanValue NETHER_TICKER_USE;
	public static ForgeConfigSpec.BooleanValue ADDITIONAL_STRUCTURES_GENERATING;
	public static ForgeConfigSpec.IntValue CONFIG_VERSION;
	// ------------------------------------------------------------------

	private static void configValuesBuilder(ForgeConfigSpec.Builder WORLD_CONFIG_BUILDER) {
		// Config versioning
		CONFIG_VERSION = WORLD_CONFIG_BUILDER.comment("Does not change this value. It is for internal code reference.").defineInRange("config_version", VERSION, VERSION, VERSION);
		//---------------------------------------
        // AIRSHIPS CATEGORY
        //---------------------------------------
        // Main
        WORLD_CONFIG_BUILDER.push(TYPE_AIRSHIP);
        DO_AIRSHIPS_SPAWN = WORLD_CONFIG_BUILDER
        	.comment("Defines whether airships spawn in general. Overwrites anything below. Since airships are main source of mod features, use this option with caution.")
        	.define("do_airships_spawn", true);
        // Subtype 1
        WORLD_CONFIG_BUILDER
        	.comment("Defines whether particular type of airships spawn.").push(SUBTYPE_AIRSHIP_BOOLEANS);
        AIRSHIP_BANDIT_BOOL = WORLD_CONFIG_BUILDER.define("bandit_airship", true);
        AIRSHIP_MERCHANT_BOOL = WORLD_CONFIG_BUILDER.define("merchant_airship", true);
        AIRSHIP_MILITARY_BOOL = WORLD_CONFIG_BUILDER.define("military_airship", true);
        AIRSHIP_MAGE_BOOL = WORLD_CONFIG_BUILDER.define("mage_airship", true);
        AIRSHIP_JUNGLE_BOOL = WORLD_CONFIG_BUILDER.define("jungle_airship", true);
        WORLD_CONFIG_BUILDER.pop();
        // Subtype 2
        WORLD_CONFIG_BUILDER
        	.comment("Defines rarity of airships. The bigger the value, the more frequently airship will spawn.").push(SUBTYPE_AIRSHIP_CHANCES);
        AIRSHIP_BANDIT_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("bandit_airship_weight", 200, 0, 10000);
        AIRSHIP_MERCHANT_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("merchant_airship_weight", 240, 0, 10000);
        AIRSHIP_MILITARY_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("military_airship_weight", 170, 0, 10000);
        AIRSHIP_MAGE_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("mage_airship_weight", 170, 0, 10000);
        AIRSHIP_JUNGLE_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("jungle_airship_weight", 240, 0, 10000);
        WORLD_CONFIG_BUILDER.pop();
        WORLD_CONFIG_BUILDER.pop();
        //---------------------------------------
        // MECHANICS CATEGORY
        //---------------------------------------
        // Nesting
        WORLD_CONFIG_BUILDER.push(TYPE_MECHANICS);
        // Subtype 1
        WORLD_CONFIG_BUILDER
        	.comment("Defines some general settings related to avoider.").push(SUBTYPE_AVOIDER);
        AVOIDER_DISTANCE = WORLD_CONFIG_BUILDER.defineInRange("avoider_distance", 50, 0, 500);
        ZOMBIE_VILLAGER_KILLED = WORLD_CONFIG_BUILDER.define("zombie_villagers_killed", true);
        ZOMBIE_PIGMAN_KILLED = WORLD_CONFIG_BUILDER.define("zombie_pigmen_killed", false);
        WORLD_CONFIG_BUILDER.pop();
        // Subtype 2
        WORLD_CONFIG_BUILDER
        	.comment("Defines whether guns can be used or not. Useful if you like the mod, but find guns unnecessary.").push(SUBTYPE_GUN_CONTROL);
        GUNS_ENABLED = WORLD_CONFIG_BUILDER.define("guns_enabled", true);
        WORLD_CONFIG_BUILDER.pop();
        WORLD_CONFIG_BUILDER.pop();
        //---------------------------------------
        // LOOT CATEGORY
        //---------------------------------------
        // Nesting
        WORLD_CONFIG_BUILDER.push(TYPE_LOOT);
        // Subtype 1
        WORLD_CONFIG_BUILDER
        	.comment("Sets possibility and chance of endstone dropping glistering ash when mined in End dimension.").push(SUBTYPE_GLISTERING_ASH);
        DOES_ASH_DROP = WORLD_CONFIG_BUILDER.define("does_ash_drop", true);
        ASH_DROP_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("ash_drop_chance", 2, 0, 100);
        WORLD_CONFIG_BUILDER.pop();
        // Subtype 2
        WORLD_CONFIG_BUILDER
        	.comment("Sets possibility and chance of nether soul essence dropping from mobs killed in Nether dimension.").push(SUBTYPE_NETHER_SOUL_ESSENCE);
        DOES_ESSENCE_DROP = WORLD_CONFIG_BUILDER.define("does_essence_drop", true);
        ESSENCE_DROP_CHANCE = WORLD_CONFIG_BUILDER.defineInRange("essence_drop_chance", 8, 0, 100);
        WORLD_CONFIG_BUILDER.pop();
        WORLD_CONFIG_BUILDER.pop();
        //---------------------------------------
        // MOB SPAWN CATEGORY
        //---------------------------------------
        // Nesting
        WORLD_CONFIG_BUILDER.push(TYPE_MOB_SPAWN);
        // Merchant
        WORLD_CONFIG_BUILDER
        	.comment("Sets whether wandering merchants spawn, and how often. Wandering merchants replace wandering trader.").push(SUBTYPE_MERCHANT);
        DO_MERCHANT_SPAWN = WORLD_CONFIG_BUILDER.define("do_merchant_spawn", true);
        CHANCES_OF_REPLACING = WORLD_CONFIG_BUILDER.defineInRange("chances_of_replacing", 50, 0, 100);
        WORLD_CONFIG_BUILDER.pop();
        // Other mobs
        WIND_SPIRIT_SPAWN = WORLD_CONFIG_BUILDER.define("wind_spirit_spawn", true);
        ORMATH_RAIDERS_SPAWN = WORLD_CONFIG_BUILDER.define("ormath_raiders_spawn", true);
        WORLD_CONFIG_BUILDER.pop();
        //---------------------------------------
        // EXPERIMENTAL CATEGORY
        //---------------------------------------
        WORLD_CONFIG_BUILDER.push(TYPE_EXPERIMENTAL);
        // Values
        DEVELOPERS_MODE = WORLD_CONFIG_BUILDER.define("developers_mode", false);
        NETHER_TICKER_USE = WORLD_CONFIG_BUILDER.define("nether_ticker_use", false);
        ADDITIONAL_STRUCTURES_GENERATING = WORLD_CONFIG_BUILDER.define("additional_structures_generating", false);
        WORLD_CONFIG_BUILDER.pop();
	}

	// ----------------------------------------------------------------
	// TECHNICALS
	// ----------------------------------------------------------------
	static {
		ForgeConfigSpec.Builder WORLD_CONFIG_BUILDER = new ForgeConfigSpec.Builder();
		configValuesBuilder(WORLD_CONFIG_BUILDER);
		WORLD_CONFIG = WORLD_CONFIG_BUILDER.build();
	}
	public static void loadConfigFile(ForgeConfigSpec config, String path) {
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		config.setConfig(file);
	}
}