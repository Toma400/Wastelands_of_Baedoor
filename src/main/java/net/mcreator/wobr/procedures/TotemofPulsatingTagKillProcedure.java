package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.TotemofPulsatingItem;
import net.mcreator.wobr.entity.TotemofPulsatingPlacedEntity;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@WobrModElements.ModElement.Tag
public class TotemofPulsatingTagKillProcedure extends WobrModElements.ModElement {
	public TotemofPulsatingTagKillProcedure(WobrModElements instance) {
		super(instance, 1731);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure TotemofPulsatingTagKill!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure TotemofPulsatingTagKill!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure TotemofPulsatingTagKill!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure TotemofPulsatingTagKill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure TotemofPulsatingTagKill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean is_totem_in_use = false;
		is_totem_in_use = (boolean) (false);
		{
			List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
					new AxisAlignedBB(x - ((WobrModVariables.MapVariables.get(world).KF_Av_Distance * 2) / 2d),
							y - ((WobrModVariables.MapVariables.get(world).KF_Av_Distance * 2) / 2d),
							z - ((WobrModVariables.MapVariables.get(world).KF_Av_Distance * 2) / 2d),
							x + ((WobrModVariables.MapVariables.get(world).KF_Av_Distance * 2) / 2d),
							y + ((WobrModVariables.MapVariables.get(world).KF_Av_Distance * 2) / 2d),
							z + ((WobrModVariables.MapVariables.get(world).KF_Av_Distance * 2) / 2d)),
					null).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (((entityiterator instanceof PlayerEntity) && ((((entityiterator instanceof LivingEntity)
						? ((LivingEntity) entityiterator).getHeldItemMainhand()
						: ItemStack.EMPTY).getItem() == TotemofPulsatingItem.block)
						|| (((entityiterator instanceof LivingEntity) ? ((LivingEntity) entityiterator).getHeldItemOffhand() : ItemStack.EMPTY)
								.getItem() == TotemofPulsatingItem.block)))) {
					is_totem_in_use = (boolean) (true);
				} else if ((entityiterator instanceof TotemofPulsatingPlacedEntity.CustomEntity)) {
					is_totem_in_use = (boolean) (true);
				}
			}
		}
		if ((is_totem_in_use == (true))) {
			if ((((((EntityTypeTags.getCollection()
					.getOrCreate(new ResourceLocation(("forge:avoider_killable").toLowerCase(java.util.Locale.ENGLISH))).contains(entity.getType()))
					|| (EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:avoider_wobr").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType())))
					|| ((EntityTypeTags.getCollection()
							.getOrCreate(new ResourceLocation(("forge:avoider_vanilla").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType()))
							|| (EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_vanilla_16").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))))
					|| (entity instanceof ZombieEntity))
					|| (((((((EntityTypeTags.getCollection()
							.getOrCreate(new ResourceLocation(("forge:avoider_mowzie").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType()))
							|| (EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_desolat").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType())))
							|| ((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_arcaneum_15").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| (EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_arcaneum").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))))
							|| ((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_atum").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| ((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_infernal_exp").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(
															new ResourceLocation(("forge:avoider_better_end").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType())))))
							|| ((((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_eidolon").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| (EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_eternal_tales").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType())))
									|| ((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_elvenation").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(
															("forge:avoider_ice_and_fire").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))))
									|| (((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_phytolands").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(("forge:avoider_murky").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType())))
											|| ((EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(
															("forge:avoider_better_nether").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))
													|| (EntityTypeTags.getCollection()
															.getOrCreate(new ResourceLocation(
																	("forge:avoider_xenoclus").toLowerCase(java.util.Locale.ENGLISH)))
															.contains(entity.getType()))))))
							|| (((((EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_voidcraft").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))
									|| (EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_klsts").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType())))
									|| ((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_betteranimals").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(
															new ResourceLocation(("forge:avoider_afterlight").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))))
									|| (((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_cazfps_chr").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(
															new ResourceLocation(("forge:avoider_midnight").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType())))
											|| ((EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(("forge:avoider_quark").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))
													|| (EntityTypeTags.getCollection()
															.getOrCreate(new ResourceLocation(
																	("forge:avoider_refreshed_nether").toLowerCase(java.util.Locale.ENGLISH)))
															.contains(entity.getType())))))
									|| ((((EntityTypeTags.getCollection()
											.getOrCreate(new ResourceLocation(("forge:avoider_undergarden").toLowerCase(java.util.Locale.ENGLISH)))
											.contains(entity.getType()))
											|| (EntityTypeTags.getCollection()
													.getOrCreate(
															new ResourceLocation(("forge:avoider_neverdark").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType())))
											|| ((EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(
															("forge:avoider_greek_fantasy").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))
													|| (EntityTypeTags.getCollection()
															.getOrCreate(new ResourceLocation(
																	("forge:avoider_undead_exp").toLowerCase(java.util.Locale.ENGLISH)))
															.contains(entity.getType()))))
											|| (((EntityTypeTags.getCollection()
													.getOrCreate(new ResourceLocation(
															("forge:avoider_rotten_creatures").toLowerCase(java.util.Locale.ENGLISH)))
													.contains(entity.getType()))
													|| (EntityTypeTags.getCollection()
															.getOrCreate(new ResourceLocation(
																	("forge:avoider_elementals").toLowerCase(java.util.Locale.ENGLISH)))
															.contains(entity.getType())))
													|| ((EntityTypeTags.getCollection()
															.getOrCreate(new ResourceLocation(
																	("forge:avoider_klsts_15").toLowerCase(java.util.Locale.ENGLISH)))
															.contains(entity.getType()))
															|| (EntityTypeTags.getCollection()
																	.getOrCreate(new ResourceLocation(
																			("forge:avoider_elementals_15").toLowerCase(java.util.Locale.ENGLISH)))
																	.contains(entity.getType())))))))
							|| (EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_blue_skies").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))))) {
				System.out.println("I don't know how you get it here. Something is coming, anyway...");
			}
		}
	}
}
