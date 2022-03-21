package net.mcreator.wobr.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ModdedTagKillProcedure extends WobrModElements.ModElement {
	public ModdedTagKillProcedure(WobrModElements instance) {
		super(instance, 1785);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ModdedTagKill!");
			return false;
		}
		//datapacks to make:
		//MOWZIE
		//REFRESHED NETHER
		//BETTERANIMALS
		//MIDNIGHT
		//QUARK
		//ROTTEN CREATURES
		//
		//
		//XENOCLUS 1 [new!] <forge:avoider_xenoclus>
		//INTERDIMENSIONAL TRAVELLER [new!]
		Entity entity = (Entity) dependencies.get("entity");
		return (((EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:avoider_killable")))
				.contains(entity.getType()))
				|| (EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:avoider_vanilla")))
						.contains(entity.getType())))
				|| ((EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:avoider_wobr")))
						.contains(entity.getType()))));
	}
}
