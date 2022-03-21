package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.entity.SpawnEntityMageAirshipEntity;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@WobrModElements.ModElement.Tag
public class SpawnEntitiesKillProcedure extends WobrModElements.ModElement {
	public SpawnEntitiesKillProcedure(WobrModElements instance) {
		super(instance, 1594);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure SpawnEntitiesKill!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure SpawnEntitiesKill!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure SpawnEntitiesKill!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure SpawnEntitiesKill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure SpawnEntitiesKill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((Entity) world
				.getEntitiesWithinAABB(PlayerEntity.class,
						new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null) || (((Entity) world.getEntitiesWithinAABB(ServerPlayerEntity.class,
						new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null))) {
			if (((entity instanceof SpawnEntityMageAirshipEntity.CustomEntity)
					&& (((world.getDimension().getType().getId()) == (1)) && (!(((((Entity) world.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof ServerPlayerEntity)
							&& (((Entity) world.getEntitiesWithinAABB(PlayerEntity.class,
									new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)),
									null).stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
										}
									}.compareDistOf(x, y,
											z))
									.findFirst().orElse(null)).world instanceof ServerWorld))
											? ((ServerPlayerEntity) ((Entity) world
													.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d),
															z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null)
													.stream().sorted(new Object() {
														Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
															return Comparator.comparing(
																	(Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
														}
													}.compareDistOf(x, y, z)).findFirst().orElse(null))).getAdvancements()
															.getProgress(((MinecraftServer) ((ServerPlayerEntity) ((Entity) world
																	.getEntitiesWithinAABB(PlayerEntity.class,
																			new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d),
																					x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)),
																			null)
																	.stream().sorted(new Object() {
																		Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																			return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd
																					.getDistanceSq(_x, _y, _z)));
																		}
																	}.compareDistOf(x, y, z)).findFirst().orElse(null))).server)
																			.getAdvancementManager()
																			.getAdvancement(new ResourceLocation("wobr:message_from_xaine")))
															.isDone()
											: false))))) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("wobr", "xaine_note"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + 25), (int) z),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 1000);
		}
	}
}
