package net.mcreator.wobr.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.RodoftheWindsGlowPotion;
import net.mcreator.wobr.item.WindProjectileItem;
import net.mcreator.wobr.item.StrongerWindProjectileItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Random;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class RodoftheWindsShotProcedure extends WobrModElements.ModElement {
	public RodoftheWindsShotProcedure(WobrModElements instance) {
		super(instance, 1052);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RodoftheWindsShot!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RodoftheWindsShot!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.TOTEM_OF_UNDYING, (int) (1)).getItem())) {
			if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
				StrongerWindProjectileItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 4, (float) 1, (int) 5);
			}
		} else {
			if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
				WindProjectileItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 4, (float) 1, (int) 5);
			}
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(RodoftheWindsGlowPotion.potion, (int) 60, (int) 1, (false), (false)));
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) < 20)) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(
						(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getItem(), (int) 200);
		} else if (((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) >= 20)
				&& (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) < 101))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(
						(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getItem(),
						(int) ((10 - Math.floor((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) / 20))) * 20));
		} else {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(
						(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getItem(), (int) 80);
		}
		{
			ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
			if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
	}
}
