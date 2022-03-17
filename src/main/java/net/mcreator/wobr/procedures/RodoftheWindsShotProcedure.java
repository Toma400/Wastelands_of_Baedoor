package net.mcreator.wobr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.potion.RodoftheWindsGlowPotionEffect;
import net.mcreator.wobr.item.WindProjectileItem;
import net.mcreator.wobr.item.StrongerWindProjectileItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Random;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class RodoftheWindsShotProcedure extends WobrModElements.ModElement {
	public RodoftheWindsShotProcedure(WobrModElements instance) {
		super(instance, 1178);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure RodoftheWindsShot!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == Items.TOTEM_OF_UNDYING)) {
			if (entity instanceof LivingEntity) {
				Entity _ent = entity;
				if (!_ent.world.isRemote) {
					StrongerWindProjectileItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 4, (float) 1, (int) 5);
				}
			}
		} else {
			if (entity instanceof LivingEntity) {
				Entity _ent = entity;
				if (!_ent.world.isRemote) {
					WindProjectileItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 4, (float) 1, (int) 5);
				}
			}
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(RodoftheWindsGlowPotionEffect.potion, (int) 60, (int) 1, (false), (false)));
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) < 20)) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem(), (int) 200);
		} else if (((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) >= 20)
				&& (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) < 101))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem(),
						(int) ((10 - Math.floor((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) / 20))) * 20));
		} else {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem(), (int) 80);
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
