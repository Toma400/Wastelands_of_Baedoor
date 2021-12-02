
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
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
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.item.StoneJavelinItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class OrmathHunterEntity extends WobrModElements.ModElement {
	public static EntityType entity = null;
	public OrmathHunterEntity(WobrModElements instance) {
		super(instance, 468);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(110).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("ormath_hunter")
						.setRegistryName("ormath_hunter");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -12188411, -3490131, new Item.Properties().group(WoBCreativeTabItemGroup.tab))
				.setRegistryName("ormath_hunter_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelOrmath_Hunter(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wobr:textures/ormath_hunter.png");
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
			experienceValue = 3;
			setNoAI(false);
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(StoneJavelinItem.block, (int) (1)));
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
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AnimalEntity.class, true, false));
			this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.5, false));
			this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 10, 5));
			this.goalSelector.addGoal(6, new LookAtGoal(this, OrmathRiderEntity.CustomEntity.class, (float) 5));
			this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(9, new SwimGoal(this));
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

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.LEATHER, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_hunter_idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_hunter_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_hunter_death"));
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
				TribeAttackValueProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK);
			this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1D);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			StoneJavelinItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelOrmath_Hunter extends EntityModel {
		private final RendererModel Soldier;
		private final RendererModel Body_r1;
		private final RendererModel Head;
		private final RendererModel Head_r1;
		private final RendererModel RightArm;
		private final RendererModel RightArm_r1;
		private final RendererModel spear;
		private final RendererModel cube_r1;
		private final RendererModel SpearHead;
		private final RendererModel cube_r2;
		private final RendererModel cube_r3;
		private final RendererModel LeftArm;
		private final RendererModel LeftLeg;
		private final RendererModel RightLeg;
		public ModelOrmath_Hunter() {
			textureWidth = 128;
			textureHeight = 128;
			Soldier = new RendererModel(this);
			Soldier.setRotationPoint(-0.125F, 11.5646F, 5.164F);
			Body_r1 = new RendererModel(this);
			Body_r1.setRotationPoint(-0.625F, -6.4271F, -3.6802F);
			Soldier.addChild(Body_r1);
			setRotationAngle(Body_r1, 0.3054F, 0.0F, 0.0F);
			Body_r1.cubeList.add(new ModelBox(Body_r1, 42, 65, -5.0F, -7.75F, -2.5F, 10, 16, 6, 0.01F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(-0.625F, -12.9717F, -6.3381F);
			Soldier.addChild(Head);
			Head_r1 = new RendererModel(this);
			Head_r1.setRotationPoint(0.0F, -2.25F, -1.5F);
			Head.addChild(Head_r1);
			setRotationAngle(Head_r1, 0.3054F, 0.0F, 0.0F);
			Head_r1.cubeList.add(new ModelBox(Head_r1, 92, 12, -3.0F, -2.75F, -3.0F, 6, 6, 6, 0.0F, false));
			RightArm = new RendererModel(this);
			RightArm.setRotationPoint(-5.75F, -10.3646F, -5.714F);
			Soldier.addChild(RightArm);
			RightArm_r1 = new RendererModel(this);
			RightArm_r1.setRotationPoint(-1.375F, 0.0F, 0.05F);
			RightArm.addChild(RightArm_r1);
			setRotationAngle(RightArm_r1, -1.1345F, 0.0F, 0.0F);
			RightArm_r1.cubeList.add(new ModelBox(RightArm_r1, 12, 100, -1.5F, -0.5F, -1.5F, 3, 11, 3, 0.0F, false));
			spear = new RendererModel(this);
			spear.setRotationPoint(5.125F, 55.5F, 7.55F);
			RightArm.addChild(spear);
			cube_r1 = new RendererModel(this);
			cube_r1.setRotationPoint(-6.5F, -50.5F, -15.5F);
			spear.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
			cube_r1.cubeList.add(new ModelBox(cube_r1, 98, 58, -0.5F, -10.75F, 0.0F, 1, 23, 1, 0.01F, false));
			SpearHead = new RendererModel(this);
			SpearHead.setRotationPoint(-6.5F, -28.2225F, -19.1851F);
			spear.addChild(SpearHead);
			setRotationAngle(SpearHead, -0.4363F, 0.0F, 0.0F);
			SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 0, -0.5F, -31.6082F, -16.5464F, 1, 1, 3, 0.02F, false));
			SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 0, -0.5F, -30.6082F, -15.5464F, 1, 1, 3, 0.02F, false));
			SpearHead.cubeList.add(new ModelBox(SpearHead, 8, 10, -0.5F, -29.6082F, -14.5464F, 1, 1, 1, 0.02F, false));
			cube_r2 = new RendererModel(this);
			cube_r2.setRotationPoint(0.0F, -0.2F, -0.1F);
			SpearHead.addChild(cube_r2);
			setRotationAngle(cube_r2, -1.5708F, 0.0F, 0.0F);
			cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -0.5F, 14.4464F, -30.4082F, 1, 1, 3, 0.02F, false));
			cube_r3 = new RendererModel(this);
			cube_r3.setRotationPoint(0.0F, -1.2F, -1.1F);
			SpearHead.addChild(cube_r3);
			setRotationAngle(cube_r3, -1.5708F, 0.0F, 0.0F);
			cube_r3.cubeList.add(new ModelBox(cube_r3, 5, 0, -0.5F, 14.4464F, -29.4082F, 1, 1, 2, 0.02F, false));
			LeftArm = new RendererModel(this);
			LeftArm.setRotationPoint(4.375F, -10.3646F, -5.664F);
			Soldier.addChild(LeftArm);
			LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 100, 0.0F, -0.5F, -1.5F, 3, 11, 3, 0.0F, false));
			LeftLeg = new RendererModel(this);
			LeftLeg.setRotationPoint(1.875F, -0.3646F, -1.664F);
			Soldier.addChild(LeftLeg);
			LeftLeg.cubeList.add(new ModelBox(LeftLeg, 92, 87, -2.5F, -0.5F, -2.5F, 5, 13, 5, 0.02F, false));
			RightLeg = new RendererModel(this);
			RightLeg.setRotationPoint(-3.125F, -0.3646F, -1.664F);
			Soldier.addChild(RightLeg);
			RightLeg.cubeList.add(new ModelBox(RightLeg, 24, 93, -2.5F, -0.5F, -2.5F, 5, 13, 5, 0.02F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Soldier.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
