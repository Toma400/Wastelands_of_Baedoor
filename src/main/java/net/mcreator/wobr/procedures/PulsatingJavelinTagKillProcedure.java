package net.mcreator.wobr.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

@WobrModElements.ModElement.Tag
public class PulsatingJavelinTagKillProcedure extends WobrModElements.ModElement {
	public PulsatingJavelinTagKillProcedure(WobrModElements instance) {
		super(instance, 1720);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure PulsatingJavelinTagKill!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure PulsatingJavelinTagKill!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure PulsatingJavelinTagKill!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure PulsatingJavelinTagKill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure PulsatingJavelinTagKill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double counter = 0;
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
				if ((((world.canBlockSeeSky(new BlockPos((int) (entityiterator.getPosX()), (int) (entityiterator.getPosY()),
						(int) (entityiterator.getPosZ())))) == (true))
						&& ((((EntityTypeTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:avoider_killable").toLowerCase(java.util.Locale.ENGLISH)))
								.contains(entityiterator.getType()))
								|| (EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_wobr").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entityiterator.getType())))
								|| ((EntityTypeTags.getCollection()
										.getOrCreate(new ResourceLocation(("forge:avoider_vanilla").toLowerCase(java.util.Locale.ENGLISH)))
										.contains(entityiterator.getType()))
										|| (EntityTypeTags.getCollection()
												.getOrCreate(new ResourceLocation(("forge:avoider_vanilla_16").toLowerCase(java.util.Locale.ENGLISH)))
												.contains(entityiterator.getType()))))
								|| (entityiterator instanceof ZombieEntity)))) {
					counter = (double) (counter + 1);
					if (world instanceof ServerWorld)
						((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world.getWorld(), (int) (entityiterator.getPosX()),
								(int) (entityiterator.getPosY()), (int) (entityiterator.getPosZ()), false));
					if (!world.getWorld().isRemote) {
						world.playSound(null,
								new BlockPos((int) (entityiterator.getPosX()), (int) (entityiterator.getPosY()), (int) (entityiterator.getPosZ())),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.thunder")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						world.getWorld().playSound((entityiterator.getPosX()), (entityiterator.getPosY()), (entityiterator.getPosZ()),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.thunder")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
				}
			}
		}
		if ((counter > 4)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:riderofthe_lightning"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}
}
