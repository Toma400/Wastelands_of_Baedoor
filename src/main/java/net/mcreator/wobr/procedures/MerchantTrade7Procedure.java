package net.mcreator.wobr.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.wobr.potion.MerchantBlockShulkerShellsPotionEffect;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class MerchantTrade7Procedure extends WobrModElements.ModElement {
	public MerchantTrade7Procedure(WobrModElements instance) {
		super(instance, 1914);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure MerchantTrade7!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure MerchantTrade7!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure MerchantTrade7!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure MerchantTrade7!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure MerchantTrade7!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double money = 0;
		money = (double) 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((Blocks.DIAMOND_BLOCK.asItem() == (itemstackiterator).getItem())) {
						money = (double) (money + (((itemstackiterator)).getCount()));
					}
				}
			}
		}
		if (((money >= 1) && ((new Object() {
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
		}.getScore("shulker_block")) < 2))) {
			if ((!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == MerchantBlockShulkerShellsPotionEffect.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.DIAMOND_BLOCK);
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"give @a[distance=..1] minecraft:shulker_shell 1");
				}
				if (((new Object() {
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
				}.getScore("shulker_block")) == 0)) {
					{
						Entity _ent = entity;
						if (_ent instanceof PlayerEntity) {
							Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
							ScoreObjective _so = _sc.getObjective("shulker_block");
							if (_so == null) {
								_so = _sc.addObjective("shulker_block", ScoreCriteria.DUMMY, new StringTextComponent("shulker_block"),
										ScoreCriteria.RenderType.INTEGER);
							}
							Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
							_scr.setScorePoints((int) 1);
						}
					}
				}
				if (((new Object() {
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
				}.getScore("shulker_block")) == 1)) {
					{
						Entity _ent = entity;
						if (_ent instanceof PlayerEntity) {
							Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
							ScoreObjective _so = _sc.getObjective("shulker_block");
							if (_so == null) {
								_so = _sc.addObjective("shulker_block", ScoreCriteria.DUMMY, new StringTextComponent("shulker_block"),
										ScoreCriteria.RenderType.INTEGER);
							}
							Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
							_scr.setScorePoints((int) 2);
						}
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(MerchantBlockShulkerShellsPotionEffect.potion, (int) 24000, (int) 1, (false), (false)));
				}
			}
		} else if (((money == 0) && ((new Object() {
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
		}.getScore("shulker_block")) < 2))) {
			money = (double) 0;
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if ((Items.DIAMOND == (itemstackiterator).getItem())) {
							money = (double) (money + (((itemstackiterator)).getCount()));
						}
					}
				}
			}
			if (((money >= 9) && ((new Object() {
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
			}.getScore("shulker_block")) < 2))) {
				if ((!(new Object() {
					boolean check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == MerchantBlockShulkerShellsPotionEffect.potion)
									return true;
							}
						}
						return false;
					}
				}.check(entity)))) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(Items.DIAMOND);
						((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 9);
					}
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"give @a[distance=..1] minecraft:shulker_shell 1");
					}
					if (((new Object() {
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
					}.getScore("shulker_block")) == 0)) {
						{
							Entity _ent = entity;
							if (_ent instanceof PlayerEntity) {
								Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
								ScoreObjective _so = _sc.getObjective("shulker_block");
								if (_so == null) {
									_so = _sc.addObjective("shulker_block", ScoreCriteria.DUMMY, new StringTextComponent("shulker_block"),
											ScoreCriteria.RenderType.INTEGER);
								}
								Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
								_scr.setScorePoints((int) 1);
							}
						}
					}
					if (((new Object() {
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
					}.getScore("shulker_block")) == 1)) {
						{
							Entity _ent = entity;
							if (_ent instanceof PlayerEntity) {
								Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
								ScoreObjective _so = _sc.getObjective("shulker_block");
								if (_so == null) {
									_so = _sc.addObjective("shulker_block", ScoreCriteria.DUMMY, new StringTextComponent("shulker_block"),
											ScoreCriteria.RenderType.INTEGER);
								}
								Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
								_scr.setScorePoints((int) 2);
							}
						}
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(
									new EffectInstance(MerchantBlockShulkerShellsPotionEffect.potion, (int) 24000, (int) 1, (false), (false)));
					}
				}
			}
		}
		return (!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == MerchantBlockShulkerShellsPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)));
	}
}
