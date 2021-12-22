package net.mcreator.wobr.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

@WobrModElements.ModElement.Tag
public class ConfigInfoProcedure extends WobrModElements.ModElement {
	public ConfigInfoProcedure(WobrModElements instance) {
		super(instance, 1716);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		File info = new File("");
		info = new File(((FMLPaths.GAMEDIR.get().toString()) + "" + ("config")), File.separator + "wobr-config-info.toml");
		if (!info.exists()) {
			try {
				info.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter infowriter = new FileWriter(info);
			BufferedWriter infobw = new BufferedWriter(infowriter);
			{
				infobw.write("# IMPORTANT");
				infobw.newLine();
			}
			{
				infobw.write("# This file is for reference only");
				infobw.newLine();
			}
			{
				infobw.write("# For 1.15 version, you will find right configuration file in your save under \"serverconfig\" folder");
				infobw.newLine();
			}
			{
				infobw.write("# Do not delete it, it can cause crash");
				infobw.newLine();
			}
			{
				infobw.write("# If you did, then head back to CurseForge, config file will be linked");
				infobw.newLine();
			}
			{
				infobw.write("# Config file updates itself each time it gets development change, so there's no need to delete it yourself");
				infobw.newLine();
			}
			{
				infobw.write("# CONFIG REFRESHING");
				infobw.newLine();
			}
			{
				infobw.write("# Config file execution is once player joins the server");
				infobw.newLine();
			}
			{
				infobw.write("# Meaning you can refresh it by relogging the player, no need to reload the server");
				infobw.newLine();
			}
			{
				infobw.write("# ----------------");
				infobw.newLine();
			}
			{
				infobw.write("# DEFAULT VALUES");
				infobw.newLine();
			}
			{
				infobw.write("# ----------------");
				infobw.newLine();
			}
			{
				infobw.write("# MECHANICS");
				infobw.newLine();
			}
			{
				infobw.write("# avoider_distance: 50");
				infobw.newLine();
			}
			{
				infobw.write("# zombie_villager_killed: true");
				infobw.newLine();
			}
			{
				infobw.write("# zombie_pigman_killed: false");
				infobw.newLine();
			}
			{
				infobw.write("# GUNS");
				infobw.newLine();
			}
			{
				infobw.write("# damage_scaling: 1");
				infobw.newLine();
			}
			{
				infobw.write("# DROPS");
				infobw.newLine();
			}
			{
				infobw.write("# ash_chance: 2");
				infobw.newLine();
			}
			{
				infobw.write("# essence_chance: 8");
				infobw.newLine();
			}
			{
				infobw.write("# MOB SPAWN");
				infobw.newLine();
			}
			{
				infobw.write("# chance_of_replacing: 25");
				infobw.newLine();
			}
			{
				infobw.write("# wind_spirit: true");
				infobw.newLine();
			}
			{
				infobw.write("# ormath_raiders: false");
				infobw.newLine();
			}
			{
				infobw.write("# EXPERIMENTAL");
				infobw.newLine();
			}
			{
				infobw.write("# by default all these options are false");
				infobw.newLine();
			}
			infobw.close();
			infowriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
