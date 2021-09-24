package net.mcreator.wobr.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class FirearmExperienceProcedure extends WobrModElements.ModElement {
	public FirearmExperienceProcedure(WobrModElements instance) {
		super(instance, 1207);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FirearmExperience!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) (new Object() {
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
			}.getScore("gun_experience"));
			entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.Gun_Experience = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) < 100)) {
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("gun_xp_draw");
					if (_so == null) {
						_so = _sc.addObjective("gun_xp_draw", ScoreCriteria.DUMMY, new StringTextComponent("gun_xp_draw"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 1);
				}
			}
		} else if (((((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) < 200)
				&& (((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) >= 100))) {
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("gun_xp_draw");
					if (_so == null) {
						_so = _sc.addObjective("gun_xp_draw", ScoreCriteria.DUMMY, new StringTextComponent("gun_xp_draw"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 0.8);
				}
			}
		} else if (((((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) < 300)
				&& (((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) >= 200))) {
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("gun_xp_draw");
					if (_so == null) {
						_so = _sc.addObjective("gun_xp_draw", ScoreCriteria.DUMMY, new StringTextComponent("gun_xp_draw"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 0.6);
				}
			}
		} else if (((((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) < 400)
				&& (((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) >= 300))) {
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("gun_xp_draw");
					if (_so == null) {
						_so = _sc.addObjective("gun_xp_draw", ScoreCriteria.DUMMY, new StringTextComponent("gun_xp_draw"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 0.4);
				}
			}
		} else if (((((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) < 500)
				&& (((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) >= 400))) {
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("gun_xp_draw");
					if (_so == null) {
						_so = _sc.addObjective("gun_xp_draw", ScoreCriteria.DUMMY, new StringTextComponent("gun_xp_draw"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 0.2);
				}
			}
		} else if ((((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) >= 500)) {
			{
				Entity _ent = entity;
				if (_ent instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective("gun_xp_draw");
					if (_so == null) {
						_so = _sc.addObjective("gun_xp_draw", ScoreCriteria.DUMMY, new StringTextComponent("gun_xp_draw"),
								ScoreCriteria.RenderType.INTEGER);
					}
					Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
					_scr.setScorePoints((int) 0);
				}
			}
		}
	}
}
