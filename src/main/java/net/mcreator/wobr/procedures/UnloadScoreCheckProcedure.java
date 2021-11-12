package net.mcreator.wobr.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class UnloadScoreCheckProcedure extends WobrModElements.ModElement {
	public UnloadScoreCheckProcedure(WobrModElements instance) {
		super(instance, 1565);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure UnloadScoreCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobr_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))
				|| (ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobn_firearms").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem())))) {
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
					_scr.setScorePoints((int) 2);
				}
			}
		}
	}
}
