
package net.mcreator.wobr.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wobr.procedures.TribeAttackValueProcedure;
import net.mcreator.wobr.procedures.OrmathHuntersSpawnProcedure;
import net.mcreator.wobr.procedures.EntityOrmathRaiderConditionProcedure;
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class OrmathRiderEntity extends WobrModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(120).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new)
			.size(1.5f, 2f)).build("ormath_rider").setRegistryName("ormath_rider");
	public OrmathRiderEntity(WobrModElements instance) {
		super(instance, 462);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -11851759, -8487859, new Item.Properties().group(WoBCreativeTabItemGroup.tab))
				.setRegistryName("ormath_rider_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("bamboo_jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("bamboo_jungle_hills")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("jungle_edge")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("modified_jungle")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("modified_jungle_edge")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 10, 1, 1));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return EntityOrmathRaiderConditionProcedure.executeProcedure(ImmutableMap.of("world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelOrmath_Beast_with_Hunter(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wobr:textures/ormath_rider.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 7;
			setNoAI(false);
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
					double x = CustomEntity.this.getPosX();
					double y = CustomEntity.this.getPosY();
					double z = CustomEntity.this.getPosZ();
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && TribeAttackValueProcedure.executeProcedure(ImmutableMap.of("entity", entity));
				}
			});
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AnimalEntity.class, true, false));
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.5, false));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_hunter_idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_rider_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_rider_death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				OrmathHuntersSpawnProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK);
			this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1D);
		}
	}

	// Made with Blockbench 3.9.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class ModelOrmath_Beast_with_Hunter extends EntityModel<Entity> {
		private final ModelRenderer Head;
		private final ModelRenderer BottomJaw;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer UpperJaw;
		private final ModelRenderer LeftLeg;
		private final ModelRenderer RightLeg;
		private final ModelRenderer LeftlLeg2;
		private final ModelRenderer RightLeg2;
		private final ModelRenderer Soldier;
		private final ModelRenderer Body_r1;
		private final ModelRenderer Head2;
		private final ModelRenderer Head_r1;
		private final ModelRenderer RightArm;
		private final ModelRenderer RightArm_r1;
		private final ModelRenderer spear;
		private final ModelRenderer cube_r3;
		private final ModelRenderer SpearHead;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		private final ModelRenderer LeftArm;
		private final ModelRenderer LeftLeg2;
		private final ModelRenderer LeftLeg_r1;
		private final ModelRenderer RightLeg3;
		private final ModelRenderer RightLeg_r1;
		private final ModelRenderer bb_main;
		private final ModelRenderer Spike2_r1;
		private final ModelRenderer cube_r6;
		private final ModelRenderer cube_r7;
		private final ModelRenderer cube_r8;
		public ModelOrmath_Beast_with_Hunter() {
			textureWidth = 128;
			textureHeight = 128;
			Head = new ModelRenderer(this);
			Head.setRotationPoint(-0.6667F, -0.8333F, -22.25F);
			Head.setTextureOffset(46, 44).addBox(-5.3333F, -6.6667F, -11.75F, 10.0F, 7.0F, 14.0F, 0.01F, false);
			Head.setTextureOffset(24, 74).addBox(-0.3333F, -12.6667F, -11.0F, 0.0F, 6.0F, 13.0F, 0.0F, false);
			BottomJaw = new ModelRenderer(this);
			BottomJaw.setRotationPoint(-0.3333F, 1.6896F, -0.1102F);
			Head.addChild(BottomJaw);
			setRotationAngle(BottomJaw, 0.5672F, 0.0F, 0.0F);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 11.1608F, 12.7055F);
			BottomJaw.addChild(cube_r1);
			setRotationAngle(cube_r1, -1.0036F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(0, 4).addBox(-5.0F, 14.4345F, -21.4072F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			cube_r1.setTextureOffset(9, 40).addBox(0.5F, 14.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(3, 40).addBox(4.0F, 10.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(6, 39).addBox(-5.0F, 10.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(6, 7).addBox(-5.0F, 12.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(0, 30).addBox(4.0F, 12.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(5, 4).addBox(2.25F, 14.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(0, 37).addBox(-1.5F, 14.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(5, 4).addBox(-3.25F, 14.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(0, 37).addBox(-1.5F, 14.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(0, 8).addBox(4.0F, 14.4345F, -21.4072F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 9.474F, 11.6309F);
			BottomJaw.addChild(cube_r2);
			setRotationAngle(cube_r2, -1.0036F, 0.0F, 0.0F);
			cube_r2.setTextureOffset(80, 43).addBox(-5.0F, 3.6845F, -16.4072F, 10.0F, 12.0F, 3.0F, 0.02F, false);
			UpperJaw = new ModelRenderer(this);
			UpperJaw.setRotationPoint(-0.3333F, 1.6896F, -0.1102F);
			Head.addChild(UpperJaw);
			UpperJaw.setTextureOffset(0, 33).addBox(3.75F, -1.3563F, -11.1398F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(6, 30).addBox(-4.75F, -1.3563F, -11.1398F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(7, 36).addBox(-3.25F, -1.3563F, -11.1398F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(4, 34).addBox(-4.75F, -1.3563F, -9.1398F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(9, 33).addBox(3.75F, -1.3563F, -9.1398F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(3, 38).addBox(3.75F, -1.3563F, -7.1398F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(0, 39).addBox(-4.75F, -1.3563F, -7.1398F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(6, 41).addBox(-1.5F, -1.3563F, -11.1398F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(0, 41).addBox(0.5F, -1.3563F, -11.1398F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			UpperJaw.setTextureOffset(7, 36).addBox(2.25F, -1.3563F, -11.1398F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			LeftLeg = new ModelRenderer(this);
			LeftLeg.setRotationPoint(4.05F, 8.8F, -9.8F);
			LeftLeg.setTextureOffset(74, 65).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, -0.2F, false);
			RightLeg = new ModelRenderer(this);
			RightLeg.setRotationPoint(-6.2F, 8.8F, -20.8F);
			RightLeg.setTextureOffset(0, 78).addBox(-3.0F, 0.0F, 8.0F, 6.0F, 16.0F, 6.0F, -0.2F, false);
			LeftlLeg2 = new ModelRenderer(this);
			LeftlLeg2.setRotationPoint(4.8F, 8.8F, 10.2F);
			LeftlLeg2.setTextureOffset(44, 87).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 12.0F, 6.0F, -0.2F, false);
			RightLeg2 = new ModelRenderer(this);
			RightLeg2.setRotationPoint(-6.95F, 8.8F, 10.2F);
			RightLeg2.setTextureOffset(68, 87).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 12.0F, 6.0F, -0.2F, false);
			Soldier = new ModelRenderer(this);
			Soldier.setRotationPoint(-0.125F, -7.4354F, -4.836F);
			setRotationAngle(Soldier, -0.2618F, 0.0F, 0.0F);
			Body_r1 = new ModelRenderer(this);
			Body_r1.setRotationPoint(-0.625F, -6.4271F, -3.6802F);
			Soldier.addChild(Body_r1);
			setRotationAngle(Body_r1, 0.3054F, 0.0F, 0.0F);
			Body_r1.setTextureOffset(42, 65).addBox(-5.0F, -7.75F, -2.5F, 10.0F, 16.0F, 6.0F, 0.01F, false);
			Head2 = new ModelRenderer(this);
			Head2.setRotationPoint(-0.625F, 20.0283F, -5.8381F);
			Soldier.addChild(Head2);
			Head_r1 = new ModelRenderer(this);
			Head_r1.setRotationPoint(0.0F, -35.25F, -2.0F);
			Head2.addChild(Head_r1);
			setRotationAngle(Head_r1, 0.3054F, 0.0F, 0.0F);
			Head_r1.setTextureOffset(92, 12).addBox(-3.0F, -2.75F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
			RightArm = new ModelRenderer(this);
			RightArm.setRotationPoint(-5.125F, 22.6354F, -5.664F);
			Soldier.addChild(RightArm);
			RightArm_r1 = new ModelRenderer(this);
			RightArm_r1.setRotationPoint(-2.0F, -33.0F, 0.0F);
			RightArm.addChild(RightArm_r1);
			setRotationAngle(RightArm_r1, -1.1345F, 0.0F, 0.0F);
			RightArm_r1.setTextureOffset(12, 100).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);
			spear = new ModelRenderer(this);
			spear.setRotationPoint(4.5F, 22.5F, 7.5F);
			RightArm.addChild(spear);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(-6.5F, -50.5F, -15.5F);
			spear.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.4363F, 0.0F, 0.0F);
			cube_r3.setTextureOffset(98, 58).addBox(-0.5F, -10.75F, 0.0F, 1.0F, 23.0F, 1.0F, 0.01F, false);
			SpearHead = new ModelRenderer(this);
			SpearHead.setRotationPoint(-6.5F, -28.2225F, -19.1851F);
			spear.addChild(SpearHead);
			setRotationAngle(SpearHead, -0.4363F, 0.0F, 0.0F);
			SpearHead.setTextureOffset(0, 0).addBox(-0.5F, -31.6082F, -16.5464F, 1.0F, 1.0F, 3.0F, 0.02F, false);
			SpearHead.setTextureOffset(0, 0).addBox(-0.5F, -30.6082F, -15.5464F, 1.0F, 1.0F, 3.0F, 0.02F, false);
			SpearHead.setTextureOffset(8, 10).addBox(-0.5F, -29.6082F, -14.5464F, 1.0F, 1.0F, 1.0F, 0.02F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, -0.2F, -0.1F);
			SpearHead.addChild(cube_r4);
			setRotationAngle(cube_r4, -1.5708F, 0.0F, 0.0F);
			cube_r4.setTextureOffset(0, 0).addBox(-0.5F, 14.4464F, -30.4082F, 1.0F, 1.0F, 3.0F, 0.02F, false);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(0.0F, -1.2F, -1.1F);
			SpearHead.addChild(cube_r5);
			setRotationAngle(cube_r5, -1.5708F, 0.0F, 0.0F);
			cube_r5.setTextureOffset(5, 0).addBox(-0.5F, 14.4464F, -29.4082F, 1.0F, 1.0F, 2.0F, 0.02F, false);
			LeftArm = new ModelRenderer(this);
			LeftArm.setRotationPoint(4.625F, 22.6354F, -5.664F);
			Soldier.addChild(LeftArm);
			LeftArm.setTextureOffset(0, 100).addBox(-0.25F, -33.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);
			LeftLeg2 = new ModelRenderer(this);
			LeftLeg2.setRotationPoint(1.875F, 32.6354F, -1.664F);
			Soldier.addChild(LeftLeg2);
			LeftLeg_r1 = new ModelRenderer(this);
			LeftLeg_r1.setRotationPoint(0.0F, -33.0F, 0.0F);
			LeftLeg2.addChild(LeftLeg_r1);
			setRotationAngle(LeftLeg_r1, -1.2217F, -0.6109F, 0.0F);
			LeftLeg_r1.setTextureOffset(92, 87).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, 0.02F, false);
			RightLeg3 = new ModelRenderer(this);
			RightLeg3.setRotationPoint(-3.125F, 32.6354F, -1.664F);
			Soldier.addChild(RightLeg3);
			RightLeg_r1 = new ModelRenderer(this);
			RightLeg_r1.setRotationPoint(0.0F, -33.0F, 0.0F);
			RightLeg3.addChild(RightLeg_r1);
			setRotationAngle(RightLeg_r1, -1.2217F, 0.6109F, 0.0F);
			RightLeg_r1.setTextureOffset(24, 93).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, 0.02F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 30).addBox(-9.6F, -25.7108F, 2.4785F, 17.0F, 15.0F, 13.0F, 0.41F, false);
			Spike2_r1 = new ModelRenderer(this);
			Spike2_r1.setRotationPoint(-1.0F, -34.5F, -7.75F);
			bb_main.addChild(Spike2_r1);
			setRotationAngle(Spike2_r1, -0.1745F, 0.0F, 0.0F);
			Spike2_r1.setTextureOffset(18, 69).addBox(0.0F, 0.25F, -3.25F, 0.0F, 4.0F, 9.0F, 0.0F, false);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(-1.0F, -23.5836F, -22.2343F);
			bb_main.addChild(cube_r6);
			setRotationAngle(cube_r6, -0.5672F, 0.0F, 0.0F);
			cube_r6.setTextureOffset(60, 0).addBox(-5.0F, -6.5F, -1.5F, 10.0F, 10.0F, 8.0F, 0.0F, false);
			cube_r7 = new ModelRenderer(this);
			cube_r7.setRotationPoint(-1.0F, -23.8453F, -15.0802F);
			bb_main.addChild(cube_r7);
			setRotationAngle(cube_r7, -0.1745F, 0.0F, 0.0F);
			cube_r7.setTextureOffset(0, 58).addBox(-7.0F, -5.0F, -3.5F, 14.0F, 13.0F, 7.0F, 0.0F, false);
			cube_r8 = new ModelRenderer(this);
			cube_r8.setRotationPoint(-1.0F, -19.5F, 1.0F);
			bb_main.addChild(cube_r8);
			setRotationAngle(cube_r8, -0.1745F, 0.0F, 0.0F);
			cube_r8.setTextureOffset(48, 18).addBox(-8.0F, -6.0F, -4.0F, 16.0F, 13.0F, 12.0F, 0.0F, false);
			cube_r8.setTextureOffset(0, 0).addBox(-9.0F, -9.0F, -16.0F, 18.0F, 18.0F, 12.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Head.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftlLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
			Soldier.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.LeftlLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head2.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head2.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.RightLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
