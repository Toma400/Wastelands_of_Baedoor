package net.mcreator.wobr.procedures;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Score;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.entity.RedBuffaloEntity;
import net.mcreator.wobr.entity.OrmathWarriorEntity;
import net.mcreator.wobr.entity.OrmathVillagerEntity;
import net.mcreator.wobr.entity.OrmathRangedWarriorEntity;
import net.mcreator.wobr.entity.GreenBuffaloEntity;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ShamanOrmathBlessingEffectProcedure extends WobrModElements.ModElement {
	public ShamanOrmathBlessingEffectProcedure(WobrModElements instance) {
		super(instance, 1475);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ShamanOrmathBlessingEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((((entity instanceof RedBuffaloEntity.CustomEntity) || (entity instanceof GreenBuffaloEntity.CustomEntity))
				|| (entity instanceof OrmathVillagerEntity.CustomEntity))
				|| ((entity instanceof OrmathWarriorEntity.CustomEntity) || (entity instanceof OrmathRangedWarriorEntity.CustomEntity)))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 60, (int) 2, (false), (true)));
		} else if (((new Object() {
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
		}.getScore("tribe_reputation")) >= 150)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 60, (int) 2, (false), (true)));
		}
	}
}
