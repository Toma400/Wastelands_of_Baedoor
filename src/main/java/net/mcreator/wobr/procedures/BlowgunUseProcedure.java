package net.mcreator.wobr.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.DartWitherProjectileItem;
import net.mcreator.wobr.item.DartWitherItem;
import net.mcreator.wobr.item.DartRegularProjectileItem;
import net.mcreator.wobr.item.DartRegularItem;
import net.mcreator.wobr.item.DartPoisonProjectileItem;
import net.mcreator.wobr.item.DartPoisonItem;
import net.mcreator.wobr.item.DartCurareProjectileItem;
import net.mcreator.wobr.item.DartCurareItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Random;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class BlowgunUseProcedure extends WobrModElements.ModElement {
	public BlowgunUseProcedure(WobrModElements instance) {
		super(instance, 1529);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BlowgunUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure BlowgunUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getBoolean("loaded")) == (false))) {
			if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:dart_projectile").toLowerCase(java.util.Locale.ENGLISH)))
					.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem()))) {
				if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(DartRegularItem.block, (int) (1)).getItem())) {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).shrink((int) 1);
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putBoolean("loaded", (true));
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("dart", 1);
				} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(DartCurareItem.block, (int) (1)).getItem())) {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).shrink((int) 1);
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putBoolean("loaded", (true));
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("dart", 2);
				} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(DartPoisonItem.block, (int) (1)).getItem())) {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).shrink((int) 1);
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putBoolean("loaded", (true));
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("dart", 3);
				} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(DartWitherItem.block, (int) (1)).getItem())) {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).shrink((int) 1);
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putBoolean("loaded", (true));
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("dart", 4);
				}
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).getCooldownTracker().setCooldown(
							(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getItem(),
							(int) 25);
			}
		} else {
			if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("dart")) == 1)) {
				if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
					DartRegularProjectileItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 8, (float) (Math.random() * 0.25),
							(int) 0);
				}
			} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("dart")) == 2)) {
				if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
					DartCurareProjectileItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 8, (float) (Math.random() * 0.25),
							(int) 0);
				}
			} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("dart")) == 3)) {
				if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
					DartPoisonProjectileItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 8, (float) (Math.random() * 0.25),
							(int) 0);
				}
			} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("dart")) == 4)) {
				if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
					DartWitherProjectileItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 8, (float) (Math.random() * 0.25),
							(int) 0);
				}
			}
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putBoolean("loaded",
					(false));
		}
	}
}
