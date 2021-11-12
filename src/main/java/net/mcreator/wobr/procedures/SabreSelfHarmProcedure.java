package net.mcreator.wobr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.BlackSabreItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class SabreSelfHarmProcedure extends WobrModElements.ModElement {
	public SabreSelfHarmProcedure(WobrModElements instance) {
		super(instance, 1309);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SabreSelfHarm!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure SabreSelfHarm!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getDouble("sabre_harm")) == 0)) {
			((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.putDouble("sabre_harm", 1);
		}
		if (((new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience")) < 41)) {
			if ((Math.random() < 0.2)) {
				if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BlackSabreItem.block, (int) (1)).getItem())) {
					sourceentity.attackEntityFrom(DamageSource.GENERIC, (float) 2);
				} else {
					sourceentity.attackEntityFrom(DamageSource.GENERIC,
							(float) (((((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY).getOrCreateTag().getDouble("weapon_attack")) / 2)
									* (((sourceentity instanceof LivingEntity)
											? ((LivingEntity) sourceentity).getHeldItemMainhand()
											: ItemStack.EMPTY).getOrCreateTag().getDouble("sabre_harm"))));
				}
			}
		} else if (((41 <= (new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience"))) && ((new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience")) < 81))) {
			if ((Math.random() < 0.15)) {
				if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BlackSabreItem.block, (int) (1)).getItem())) {
					sourceentity.attackEntityFrom(DamageSource.GENERIC, (float) 2);
				} else {
					sourceentity.attackEntityFrom(DamageSource.GENERIC,
							(float) (((((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY).getOrCreateTag().getDouble("weapon_attack")) / 2)
									* (((sourceentity instanceof LivingEntity)
											? ((LivingEntity) sourceentity).getHeldItemMainhand()
											: ItemStack.EMPTY).getOrCreateTag().getDouble("sabre_harm"))));
				}
			}
		} else if (((81 <= (new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience"))) && ((new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience")) < 126))) {
			if ((Math.random() < 0.07)) {
				if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BlackSabreItem.block, (int) (1)).getItem())) {
					sourceentity.attackEntityFrom(DamageSource.GENERIC, (float) 2);
				} else {
					sourceentity.attackEntityFrom(DamageSource.GENERIC,
							(float) (((((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY).getOrCreateTag().getDouble("weapon_attack")) / 2)
									* (((sourceentity instanceof LivingEntity)
											? ((LivingEntity) sourceentity).getHeldItemMainhand()
											: ItemStack.EMPTY).getOrCreateTag().getDouble("sabre_harm"))));
				}
			}
		} else if (((126 <= (new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience"))) && ((new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience")) < 251))) {
			if ((Math.random() < 0.03)) {
				if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BlackSabreItem.block, (int) (1)).getItem())) {
					sourceentity.attackEntityFrom(DamageSource.GENERIC, (float) 2);
				} else {
					sourceentity.attackEntityFrom(DamageSource.GENERIC,
							(float) (((((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY).getOrCreateTag().getDouble("weapon_attack")) / 2)
									* (((sourceentity instanceof LivingEntity)
											? ((LivingEntity) sourceentity).getHeldItemMainhand()
											: ItemStack.EMPTY).getOrCreateTag().getDouble("sabre_harm"))));
				}
			}
		} else if (((new Object() {
			public int getScore(String score) {
				if (sourceentity instanceof PlayerEntity) {
					Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
					ScoreObjective _so = _sc.getObjective(score);
					if (_so != null) {
						Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
						return _scr.getScorePoints();
					}
				}
				return 0;
			}
		}.getScore("sabre_experience")) >= 1000)) {
			if (((new Object() {
				public int getScore(String score) {
					if (sourceentity instanceof PlayerEntity) {
						Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
						ScoreObjective _so = _sc.getObjective(score);
						if (_so != null) {
							Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
							return _scr.getScorePoints();
						}
					}
					return 0;
				}
			}.getScore("sabre_experience")) < 8000)) {
				entity.attackEntityFrom(DamageSource.GENERIC, (float) ((new Object() {
					public int getScore(String score) {
						if (sourceentity instanceof PlayerEntity) {
							Scoreboard _sc = ((PlayerEntity) sourceentity).getWorldScoreboard();
							ScoreObjective _so = _sc.getObjective(score);
							if (_so != null) {
								Score _scr = _sc.getOrCreateScore(((PlayerEntity) sourceentity).getScoreboardName(), _so);
								return _scr.getScorePoints();
							}
						}
						return 0;
					}
				}.getScore("sabre_experience")) / 750));
			} else {
				entity.attackEntityFrom(DamageSource.GENERIC, (float) 11);
			}
		}
	}
}
