
package net.mcreator.wobr.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wobr.procedures.TribeAttackValueProcedure;
import net.mcreator.wobr.procedures.ShamanRegenerationProcedure;
import net.mcreator.wobr.procedures.ShamanGUITradeProcedure;
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.item.WindShamanProjectileItem;
import net.mcreator.wobr.item.RodoftheWindsItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class OrmathShamanEntity extends WobrModElements.ModElement {
	public static EntityType entity = null;
	public OrmathShamanEntity(WobrModElements instance) {
		super(instance, 436);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("ormath_shaman")
						.setRegistryName("ormath_shaman");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10066330, -2565928, new Item.Properties().group(WoBCreativeTabItemGroup.tab))
				.setRegistryName("ormath_shaman_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelOrmath_Shaman(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wobr:textures/ormath_shaman.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 20;
			setNoAI(false);
			setCustomName(new StringTextComponent("Ormath Shaman"));
			setCustomNameVisible(true);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false) {
				@Override
				public boolean shouldExecute() {
					double x = CustomEntity.this.posX;
					double y = CustomEntity.this.posY;
					double z = CustomEntity.this.posZ;
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && TribeAttackValueProcedure.executeProcedure(ImmutableMap.of("entity", entity));
				}
			});
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false) {
				@Override
				public boolean shouldExecute() {
					double x = CustomEntity.this.posX;
					double y = CustomEntity.this.posY;
					double z = CustomEntity.this.posZ;
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && TribeAttackValueProcedure.executeProcedure(ImmutableMap.of("entity", entity));
				}
			});
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolemEntity.class, false, false));
			this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
			this.goalSelector.addGoal(8, new AvoidEntityGoal(this, CreeperEntity.class, (float) 15, 1, 1.2));
			this.goalSelector.addGoal(9, new OpenDoorGoal(this, true));
			this.goalSelector.addGoal(10, new OpenDoorGoal(this, false));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(RodoftheWindsItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_shaman_idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_shaman_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_shaman_death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			super.processInteract(sourceentity, hand);
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ShamanGUITradeProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ShamanRegenerationProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10D);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			WindShamanProjectileItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelOrmath_Shaman extends EntityModel {
		private final RendererModel Left_Arm;
		private final RendererModel Right_Arm;
		private final RendererModel cube_r1;
		private final RendererModel Wand;
		private final RendererModel cube_r2;
		private final RendererModel Head;
		private final RendererModel cube_r3;
		private final RendererModel LeftLeg;
		private final RendererModel RightLeg;
		private final RendererModel bb_main;
		private final RendererModel Body_r1;
		public ModelOrmath_Shaman() {
			textureWidth = 64;
			textureHeight = 64;
			Left_Arm = new RendererModel(this);
			Left_Arm.setRotationPoint(4.0F, 2.0F, -0.775F);
			Left_Arm.cubeList.add(new ModelBox(Left_Arm, 8, 42, -0.18F, -0.213F, -1.437F, 3, 11, 3, 0.1F, false));
			Right_Arm = new RendererModel(this);
			Right_Arm.setRotationPoint(-4.568F, 3.0621F, -0.4373F);
			cube_r1 = new RendererModel(this);
			cube_r1.setRotationPoint(0.04F, -0.0071F, -0.2427F);
			Right_Arm.addChild(cube_r1);
			setRotationAngle(cube_r1, -1.3526F, 0.0F, 0.0F);
			cube_r1.cubeList.add(new ModelBox(cube_r1, 20, 42, -3.458F, -0.3809F, -1.4446F, 3, 11, 3, 0.1F, false));
			Wand = new RendererModel(this);
			Wand.setRotationPoint(5.568F, 20.9379F, 0.4373F);
			Right_Arm.addChild(Wand);
			cube_r2 = new RendererModel(this);
			cube_r2.setRotationPoint(-7.4F, -24.7628F, -13.5357F);
			Wand.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
			cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 24, -1.0F, -1.5F, 1.0F, 2, 27, 2, -0.5F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(-0.45F, -0.875F, -4.52F);
			setRotationAngle(Head, -0.1309F, 0.0F, 0.0F);
			cube_r3 = new RendererModel(this);
			cube_r3.setRotationPoint(0.05F, -0.95F, -1.08F);
			Head.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.3054F, 0.0F, 0.0F);
			cube_r3.cubeList.add(new ModelBox(cube_r3, 36, 0, -3.05F, -2.8F, -2.92F, 6, 6, 6, 0.0F, false));
			LeftLeg = new RendererModel(this);
			LeftLeg.setRotationPoint(1.846F, 11.503F, 2.672F);
			LeftLeg.cubeList.add(new ModelBox(LeftLeg, 8, 24, -2.5F, -0.5F, -2.5F, 5, 13, 5, -0.1F, false));
			RightLeg = new RendererModel(this);
			RightLeg.setRotationPoint(-2.95F, 12.503F, 2.672F);
			RightLeg.cubeList.add(new ModelBox(RightLeg, 28, 24, -2.5F, -1.5F, -2.5F, 5, 13, 5, -0.1F, false));
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			Body_r1 = new RendererModel(this);
			Body_r1.setRotationPoint(0.0F, -15.7904F, -1.2527F);
			bb_main.addChild(Body_r1);
			setRotationAngle(Body_r1, 0.4538F, 0.0F, 0.0F);
			Body_r1.cubeList.add(new ModelBox(Body_r1, 0, 0, -5.524F, -9.664F, -1.576F, 10, 16, 8, -0.4F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Left_Arm.render(f5);
			Right_Arm.render(f5);
			Head.render(f5);
			LeftLeg.render(f5);
			RightLeg.render(f5);
			bb_main.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Left_Arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
