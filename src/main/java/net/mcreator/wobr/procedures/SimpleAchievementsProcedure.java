package net.mcreator.wobr.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Score;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.wobr.potion.NetherAvoiderShieldingPotionEffect;
import net.mcreator.wobr.potion.NetherAvoiderPulsatingPotionEffect;
import net.mcreator.wobr.item.ThrownDaggerItem;
import net.mcreator.wobr.item.StoneJavelinItem;
import net.mcreator.wobr.item.CardSpadeAceWinfletonItem;
import net.mcreator.wobr.item.CardHeartAceWinfletonItem;
import net.mcreator.wobr.item.CardDiamondAceWinfletonItem;
import net.mcreator.wobr.item.CardClubAceWinfletonItem;
import net.mcreator.wobr.item.BoneJavelinItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class SimpleAchievementsProcedure extends WobrModElements.ModElement {
	public SimpleAchievementsProcedure(WobrModElements instance) {
		super(instance, 1251);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure SimpleAchievements!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(CardDiamondAceWinfletonItem.block))
				: false)
				&& ((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(CardHeartAceWinfletonItem.block))
						: false))
				&& (((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(CardClubAceWinfletonItem.block))
						: false)
						&& ((entity instanceof PlayerEntity)
								? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(CardSpadeAceWinfletonItem.block))
								: false)))) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:winfletons_collector"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		} else if (((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(StoneJavelinItem.block))
				: false)
				|| ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(BoneJavelinItem.block)) : false))
				|| (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ThrownDaggerItem.block)) : false)
						|| ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.BOW)) : false)))) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:hunterof_lost_woods"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		} else if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == NetherAvoiderPulsatingPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:awakethe_eye"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
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
		}.getScore("sabre_experience")) == 250)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:mastermindof_swordsmanship"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
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
		}.getScore("sabre_experience")) == 1000)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:blessedby_godsof_war"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
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
		}.getScore("gun_experience")) == 500)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:sharpest_sharpshooter"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
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
		}.getScore("tribe_reputation")) >= 250)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:ormath_friend"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
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
		}.getScore("tribe_reputation")) >= 500)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:friendof_grand_ormath_clan"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
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
		}.getScore("tribe_reputation")) <= (-50))) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:ormath_betrayer"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		} else if (((((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
				? ((ServerPlayerEntity) entity).getAdvancements()
						.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
								.getAdvancement(new ResourceLocation("wobr:guide_book")))
						.isDone()
				: false)
				&& (!(((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
						? ((ServerPlayerEntity) entity).getAdvancements()
								.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
										.getAdvancement(new ResourceLocation("wobr:guide_book_completed")))
								.isDone()
						: false)))) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"/give @p written_book{pages:['[\"\",{\"text\":\"Welcome to Wastelands!\",\"bold\":true},{\"text\":\"\\\\nThis book will try to explain you the basics of what your journey will cast upon you.\\\\n\\\\nYou can find details in longer guide. There is also appendix bound to this guide, showing guns statistics.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"I. AIRSHIPS\",\"bold\":true},{\"text\":\"\\\\nThe only source for wasteland items is found on airships - there are several types of them, spread throughout whole world.\\\\n\\\\n1a. Bandit Airship\\\\nYou will find bandit spawner and a lot of treasures there. Also, some basic guns.\",\"color\":\"reset\"}]','{\"text\":\"1b. Mage Airship\\\\nMysterious, blue airship with some hidden story behind it. You can probably find some rare resources there.\\\\n\\\\n1c. Trader Airship\\\\nCurrently under development. Once revamped, it will have some exclusive items that can\\'t be found anywhere else.\"}','{\"text\":\"1d. Military Airship\\\\nCurrently under development. When fully introduced, it will contain some heavy militaristic elements that will be exclusive for that airship.\\\\n\\\\n1e. Jungle Airship\\\\nRare airship found in jungle, home of ormath tribes.\"}','[\"\",{\"text\":\"II. GUNS\",\"bold\":true},{\"text\":\"\\\\nGuns are second most important element of wastelands. You can craft the most basic one, or create others using firearm table (found in airships). Most guns require specific elements to be created. More informations can be found in appendix.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"III. Resources\",\"bold\":true},{\"text\":\"\\\\nIn Wastelands, you will find a lot of new resources and items. One of the most important ones are baedoor gold ingots - they can be created either from smelting baedoor coins or by processing pale gold ingot (obtained by merging gold and glistering ash)\",\"color\":\"reset\"}]','{\"text\":\"Baedoor Gold can be used for decoration and specific technical blocks, such as:\\\\n* fluid tank heater\\\\n* heat fueler\\\\n\\\\nThese technical blocks can be used with redstone power to specific actions, allowing you to control the environment in different way.\"}','{\"text\":\"Other resources you could like to use:\\\\n* lockable chests (allows you to lock them with key)\\\\n* wooden variants, such as chiseled versions, lamps and more\\\\n* blue nether bricks\\\\n* storage blocks for cobblestone, netherrack and end stone\"}','[\"\",{\"text\":\"IV. Herbs\",\"bold\":true},{\"text\":\"\\\\nThere are three herbs at the moment, giving you possibility to use them in different way. They can be found on different airships.\\\\n* sand and ash veil - give you possibility to farm sand and vulcanic sand\\\\n* tsua - gives you possibility to gather ink sacks\",\"color\":\"reset\"}]','[\"\",{\"text\":\"V. Mobs\",\"bold\":true},{\"text\":\"\\\\nWandering across the lands, you can encounter various mobs, including:\\\\n* bandits - dangerous if found in groups\\\\n* ormaths - peaceful beings, if not provoked\\\\n* wind spirits - summoned by ormath shamans and found on deserts\",\"color\":\"reset\"}]','[\"\",{\"text\":\"VI. The Journey Never Ends\",\"bold\":true},{\"text\":\"\\\\nWastelands offer also a lot of compatibilities between other mods. You can check them on official site.\\\\n\\\\nI hope this small guide helped you, stranger, so you can wander across the lands a bit safer.\",\"color\":\"reset\"}]'],title:\"Wasteland Guide\",author:Gunslinger}");
				}
			}
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"/give @p written_book{pages:['[\"\",{\"text\":\"Gun Appendix\",\"bold\":true},{\"text\":\"\\\\nIn this appendix I will try to explain each firearm.\\\\n\\\\nRemember that you need to find out the recipes for each element and scheme, and for most of them you need firearm table!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Mechanics\",\"bold\":true},{\"text\":\"\\\\n\\\\nShooting firearm require you to load bullets (\\\\\"R\\\\\") to the gun. After that you can shoot them (\\\\\"right-click\\\\\"), remembering that your experience counts - it affects your gun accuracy and reliability\",\"color\":\"reset\"}]','{\"text\":\"Reliability depends also on individual gun properties. Generally each gun has different characteristics, making it different to play (they differ with time of reload, shooting, reload mechanics, accuracy, and so on).\"}','{\"text\":\"I. Handmade Revolver\\\\n\\\\nIt\\'s the only gun you can craft on crafting table. You need revolver grip, iron ingot, short barrel and blaze rod for it.\\\\n\\\\nIt uses gunpowder as an ammo and shots single, weak bullets.\"}','{\"text\":\"II. Pepper Gun\\\\n\\\\nIt\\'s expansion of handmade revolver. Increases shooting radio drastically.\\\\nIt uses small bullets and can be expanded further to enhanced pepper box with craftable scheme.\"}','{\"text\":\"III. Short Revolver\\\\n\\\\nRare revolver found in airships. Considered as one of the fastest ones and one of the most balanced. Uses small bullets. Can be crafted with use of treasure scheme.\"}','{\"text\":\"IV. Long Revolver\\\\n\\\\nStronger, yet slower alternative for short revolver. It is similarly rare, or even more, since it is rarely seen in use by bandits.\"}','{\"text\":\"V. Elephant Gun\\\\n\\\\nSimple, yet powerful rifle. Uses long bullets, giving one, yet powerful shot in a long distance. Has pretty decent reload time.\"}','{\"text\":\"VI. Crocodile Shotgun\\\\n\\\\nPowerful shotgun with some advanced mechanisms inside. Uses slugs as ammo. Can be deadly on closer distances, but works well even on moderate ones.\"}','{\"text\":\"VII. Lefs9\\\\n\\\\nLefs9 is infantry revolver, using standard small bullets. It is kinda tough to use, but offers really huge clip size, which can be used as an advantage. Can be crafted from treasure scheme.\"}','{\"text\":\"VIII. Ayer Shotgun (and Sawed-Off)\\\\n\\\\nOne of the simplest guns you can craft easily at the start. It has quite huge power considering being craftable without scheme.\\\\nUses slugs.\"}','{\"text\":\"IX. Rusty Reaper\\\\n\\\\nAlternative for Ayer Shotgun. With kinda different reload mechanics and statistics, it is just different, yet similarly primitive gun. Uses slugs.\"}','{\"text\":\"X. Windsweeper\\\\n\\\\nLong reload and powerful damage. This is basically windsweeper, crafted as an enhancement of elephant gun.\\\\nUses large bullets.\"}','{\"text\":\"XI. Sand Wanderer\\\\n\\\\nGun used by desert nomands, being powerful, two-clipped rifle. It is probably one of the favourite rifles, considering it unmatched speed.\\\\nUses large bullets.\"}'],title:\"Gun Appendix\",author:Gunslinger}");
				}
			}
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:guide_book_completed"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		} else if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == NetherAvoiderShieldingPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("wobr:protectthe_unprotected"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
