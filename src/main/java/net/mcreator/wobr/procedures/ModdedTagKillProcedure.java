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
		Entity entity = (Entity) dependencies.get("entity");
		return ((((((EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:avoider_mowzie").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(entity.getType()))
				|| (EntityTypeTags.getCollection()
						.getOrCreate(new ResourceLocation(("forge:avoider_arcaneum_15").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(entity.getType())))
				|| ((EntityTypeTags.getCollection()
						.getOrCreate(new ResourceLocation(("forge:avoider_arcaneum").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(entity.getType()))
						|| (EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_atum").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))))
				|| (((EntityTypeTags.getCollection()
						.getOrCreate(new ResourceLocation(("forge:avoider_infernal_exp").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(entity.getType()))
						|| (EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_better_end").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType())))
						|| ((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_eidolon").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_eternal_tales").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType())))))
				|| ((((EntityTypeTags.getCollection()
						.getOrCreate(new ResourceLocation(("forge:avoider_elvenation").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(entity.getType()))
						|| (EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_ice_and_fire").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType())))
						|| ((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_phytolands").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_murky").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType()))))
						|| (((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_better_nether").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_xenoclus").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType())))
								|| ((EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_voidcraft").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType()))
										|| (EntityTypeTags.getCollection()
												.getOrCreate(new ResourceLocation(("forge:avoider_klsts").toLowerCase(java.util.Locale.ENGLISH)))
												.contains(entity.getType()))))))
				|| (((((EntityTypeTags.getCollection()
						.getOrCreate(new ResourceLocation(("forge:avoider_betteranimals").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(entity.getType()))
						|| (EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_afterlight").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType())))
						|| ((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_cazfps_chr").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_midnight").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType()))))
						|| (((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_quark").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_refreshed_nether").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType())))
								|| ((EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_undergarden").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType()))
										|| (EntityTypeTags.getCollection()
												.getOrCreate(new ResourceLocation(("forge:avoider_neverdark").toLowerCase(java.util.Locale.ENGLISH)))
												.contains(entity.getType())))))
						|| ((((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_greek_fantasy").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entity.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_undead_exp").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType())))
								|| ((EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_rotten_creatures").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType()))
										|| (EntityTypeTags.getCollection()
												.getOrCreate(new ResourceLocation(("forge:avoider_elementals").toLowerCase(java.util.Locale.ENGLISH)))
												.contains(entity.getType()))))
								|| (((EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_klsts_15").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entity.getType()))
										|| (EntityTypeTags.getCollection()
												.getOrCreate(
														new ResourceLocation(("forge:avoider_elementals_15").toLowerCase(java.util.Locale.ENGLISH)))
												.contains(entity.getType())))
										|| (EntityTypeTags.getCollection()
												.getOrCreate(new ResourceLocation(("forge:avoider_blue_skies").toLowerCase(java.util.Locale.ENGLISH)))
												.contains(entity.getType()))))));
	}
}
