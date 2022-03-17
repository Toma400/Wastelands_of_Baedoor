package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.entity.SpawnEntityTraderAirshipEntity;
import net.mcreator.wobr.entity.SpawnEntityMilitaryAirshipEntity;
import net.mcreator.wobr.entity.SpawnEntityMageAirshipEntity;
import net.mcreator.wobr.entity.SpawnEntityJungleAirshipEntity;
import net.mcreator.wobr.entity.SpawnEntityBanditAirshipEntity;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class SpawnEntitiesUseProcedure extends WobrModElements.ModElement {
	public SpawnEntitiesUseProcedure(WobrModElements instance) {
		super(instance, 1591);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure SpawnEntitiesUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure SpawnEntitiesUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure SpawnEntitiesUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure SpawnEntitiesUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure SpawnEntitiesUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity instanceof SpawnEntityBanditAirshipEntity.CustomEntity)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("wobr", "airship_bandit"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + 10), (int) z),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		}
		if ((entity instanceof SpawnEntityTraderAirshipEntity.CustomEntity)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("wobr", "large_airship_trader"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + 10), (int) z),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		}
		if ((entity instanceof SpawnEntityMilitaryAirshipEntity.CustomEntity)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("wobr", "large_airship_military"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + 10), (int) z),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		}
		if ((entity instanceof SpawnEntityMageAirshipEntity.CustomEntity)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("wobr", "airship_mage"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + 10), (int) z),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		}
		if ((entity instanceof SpawnEntityJungleAirshipEntity.CustomEntity)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("wobr", "airship_jungle"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + 10), (int) z),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		}
	}
}
