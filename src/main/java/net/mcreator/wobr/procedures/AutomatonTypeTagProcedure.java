package net.mcreator.wobr.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AutomatonTypeTagProcedure extends WobrModElements.ModElement {
	public AutomatonTypeTagProcedure(WobrModElements instance) {
		super(instance, 2055);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure AutomatonTypeTagProcedure!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return ((EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:automaton_type")))
						.contains(entity.getType())));
	}
}
