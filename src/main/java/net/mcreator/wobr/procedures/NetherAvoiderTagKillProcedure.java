package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.world.AvoiderReaperModeGameRule;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class NetherAvoiderTagKillProcedure extends WobrModElements.ModElement {
	public NetherAvoiderTagKillProcedure(WobrModElements instance) {
		super(instance, 1115);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure NetherAvoiderTagKill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure NetherAvoiderTagKill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getWorld().getGameRules().getBoolean(AvoiderReaperModeGameRule.gamerule)) == (false))) {
			if (((((entity.getPersistentData().getBoolean("avoider_killable")) == (true)) || ((((EntityTypeTags.getCollection()
					.getOrCreate(new ResourceLocation(("forge:avoider_killable").toLowerCase(java.util.Locale.ENGLISH))).contains(entity.getType()))
					|| (EntityTypeTags.getCollection().getOrCreate(new ResourceLocation(("forge:avoider_wobr").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType())))
					|| ((EntityTypeTags.getCollection()
							.getOrCreate(new ResourceLocation(("forge:avoider_vanilla").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(entity.getType()))
							|| (EntityTypeTags.getCollection()
									.getOrCreate(new ResourceLocation(("forge:avoider_vanilla_16").toLowerCase(java.util.Locale.ENGLISH)))
									.contains(entity.getType()))))
					|| (((!((entity instanceof ZombiePigmanEntity) && (WobrModVariables.MapVariables.get(world).KF_Av_Pigman == (false))))
							&& (!((entity instanceof ZombieVillagerEntity) && (WobrModVariables.MapVariables.get(world).KF_Av_Villager == (false)))))
							&& (entity instanceof ZombieEntity))))
					&& ((entity.getPersistentData().getBoolean("avoider_proof")) == (false)))) {
				if (!entity.world.isRemote)
					entity.remove();
			}
		} else {
			if (((entity instanceof MonsterEntity) && ((entity.getPersistentData().getBoolean("avoider_proof")) == (false)))) {
				if (!entity.world.isRemote)
					entity.remove();
			}
		}
	}
}
