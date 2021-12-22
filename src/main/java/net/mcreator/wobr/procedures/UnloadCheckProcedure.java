package net.mcreator.wobr.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.SmallBulletItem;
import net.mcreator.wobr.item.SlugItem;
import net.mcreator.wobr.item.LargeBulletItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class UnloadCheckProcedure extends WobrModElements.ModElement {
	public UnloadCheckProcedure(WobrModElements instance) {
		super(instance, 750);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure UnloadCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		while ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getDouble("Ammo")) > 0)
				&& ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getDouble("Ammo")) < 20))) {
			if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getString("Ammo_Using"))).equals("Small_Bullet"))) {
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getDouble("Ammo")) - 1));
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(SmallBulletItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getString("Ammo_Using"))).equals("Large_Bullet"))) {
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getDouble("Ammo")) - 1));
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(LargeBulletItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getString("Ammo_Using"))).equals("Slug"))) {
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getDouble("Ammo")) - 1));
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(SlugItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getString("Ammo_Using"))).equals("Gunpowder"))) {
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getOrCreateTag().getDouble("Ammo")) - 1));
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.GUNPOWDER);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		}
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getDouble("Ammo")) >= 20)) {
			if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getString("Ammo_Using"))).equals("Small_Bullet"))) {
				for (int index1 = 0; index1 < (int) (10); index1++) {
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getOrCreateTag().getDouble("Ammo")) - 1));
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(SmallBulletItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
			} else if ((((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getString("Ammo_Using"))).equals("Large_Bullet"))) {
				for (int index2 = 0; index2 < (int) (10); index2++) {
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getOrCreateTag().getDouble("Ammo")) - 1));
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(LargeBulletItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
			}
		}
		{
			Entity _ent = entity;
			if (_ent instanceof PlayerEntity) {
				Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
				ScoreObjective _so = _sc.getObjective("reloadscore");
				if (_so == null) {
					_so = _sc.addObjective("reloadscore", ScoreCriteria.DUMMY, new StringTextComponent("reloadscore"),
							ScoreCriteria.RenderType.INTEGER);
				}
				Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
				_scr.setScorePoints((int) 0);
			}
		}
	}
}
