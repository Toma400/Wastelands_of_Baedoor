package net.mcreator.wobr.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.wobr.enchantment.CylinderInspectionEnchantment;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class FirearmJammingProcedure extends WobrModElements.ModElement {
	public FirearmJammingProcedure(WobrModElements instance) {
		super(instance, 1329);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure FirearmJamming!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure FirearmJamming!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure FirearmJamming!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure FirearmJamming!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure FirearmJamming!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((EnchantmentHelper.getEnchantmentLevel(CylinderInspectionEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0))) {
			if ((Math.ceil(((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("firearm_quality")) * 10)
					/ (2 * (EnchantmentHelper.getEnchantmentLevel(CylinderInspectionEnchantment.enchantment,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)))))
					* (new Object() {
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
					}.getScore("gun_xp_draw")))) >= (Math.random() * 100))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.putBoolean("jammed", (true));
			}
		} else {
			if ((Math.ceil((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("firearm_quality")) * 10) * (new Object() {
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
					}.getScore("gun_xp_draw")))) >= (Math.random() * 100))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:failed_shot")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.putBoolean("jammed", (true));
			}
		}
	}
}
