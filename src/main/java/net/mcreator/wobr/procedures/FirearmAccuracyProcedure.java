package net.mcreator.wobr.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.wobr.enchantment.StabilisationEnchantment;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class FirearmAccuracyProcedure extends WobrModElements.ModElement {
	public FirearmAccuracyProcedure(WobrModElements instance) {
		super(instance, 1128);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure FirearmAccuracy!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((EnchantmentHelper.getEnchantmentLevel(StabilisationEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0))) {
			((entity instanceof LivingEntity)
					? ((LivingEntity) entity).getHeldItemMainhand()
					: ItemStack.EMPTY).getOrCreateTag()
							.putDouble("aim_penalty_value",
									Math.round((20 / ((EnchantmentHelper.getEnchantmentLevel(StabilisationEnchantment.enchantment,
											((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)))
											+ 1))));
		} else {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.putDouble("aim_penalty_value", 20);
		}
		if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("wobr:wobr_firearm_shotgun").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putDouble(
					"aim_penalty_value", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getOrCreateTag().getDouble("aim_penalty_value")) + 2));
		}
		if (((entity.getPersistentData().getBoolean("google_user")) == (false))) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putDouble(
					"accuracy_penalty", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getOrCreateTag().getDouble("aim_penalty_value")) * (new Object() {
								public int getScore(String score) {
									if (entity instanceof PlayerEntity) {
										Scoreboard _sc = ((PlayerEntity) entity).getWorldScoreboard();
										ScoreObjective _so = _sc.getObjective(score);
										if (_so != null) {
											Score _scr = _sc.getOrCreateScore(((PlayerEntity) entity).getScoreboardName(), _so);
											return _scr.getScorePoints();
										}
									}
									return 0;
								}
							}.getScore("gun_xp_draw"))));
		} else {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putDouble(
					"accuracy_penalty", (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getOrCreateTag().getDouble("aim_penalty_value")) * (new Object() {
								public int getScore(String score) {
									if (entity instanceof PlayerEntity) {
										Scoreboard _sc = ((PlayerEntity) entity).getWorldScoreboard();
										ScoreObjective _so = _sc.getObjective(score);
										if (_so != null) {
											Score _scr = _sc.getOrCreateScore(((PlayerEntity) entity).getScoreboardName(), _so);
											return _scr.getScorePoints();
										}
									}
									return 0;
								}
							}.getScore("gun_xp_draw"))) * 0.5));
		}
		entity.rotationYaw = (float) ((entity.rotationYaw));
		entity.setRenderYawOffset(entity.rotationYaw);
		entity.prevRotationYaw = entity.rotationYaw;
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
			((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
			((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
		}
		entity.rotationPitch = (float) (((entity.rotationPitch)
				- Math.ceil(((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getDouble("accuracy_penalty")) / 2))));
	}
}
