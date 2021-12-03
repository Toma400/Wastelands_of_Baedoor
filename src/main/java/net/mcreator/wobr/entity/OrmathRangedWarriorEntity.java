
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wobr.procedures.TribeAttackValueProcedure;
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.item.BoneJavelinItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class OrmathRangedWarriorEntity extends WobrModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new)
			.size(0.6f, 1.8f)).build("ormath_ranged_warrior").setRegistryName("ormath_ranged_warrior");
	public OrmathRangedWarriorEntity(WobrModElements instance) {
		super(instance, 2150);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10066330, -13557214, new Item.Properties().group(WoBCreativeTabItemGroup.tab))
				.setRegistryName("ormath_ranged_warrior_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelOrmath_Warrior(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wobr:textures/ormath_warrior.png");
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
					double x = CustomEntity.this.getPosX();
					double y = CustomEntity.this.getPosY();
					double z = CustomEntity.this.getPosZ();
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && TribeAttackValueProcedure.executeProcedure(ImmutableMap.of("entity", entity));
				}
			});
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.7, false));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
			this.goalSelector.addGoal(6, new AvoidEntityGoal(this, CreeperEntity.class, (float) 15, 1, 1.2));
			this.goalSelector.addGoal(7, new OpenDoorGoal(this, true));
			this.goalSelector.addGoal(8, new OpenDoorGoal(this, false));
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
			this.entityDropItem(new ItemStack(BoneJavelinItem.block));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_warrior_idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_warrior_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_warrior_death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10D);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			BoneJavelinItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.9.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class ModelOrmath_Warrior extends EntityModel<Entity> {
		private final ModelRenderer Head;
		private final ModelRenderer Head_r1;
		private final ModelRenderer RightArm;
		private final ModelRenderer RightArm_r1;
		private final ModelRenderer spear;
		private final ModelRenderer cube_r1;
		private final ModelRenderer SpearHead;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer LeftArm;
		private final ModelRenderer LeftLeg;
		private final ModelRenderer RightLeg;
		private final ModelRenderer bb_main;
		private final ModelRenderer Body_r1;
		public ModelOrmath_Warrior() {
			textureWidth = 64;
			textureHeight = 64;
			Head = new ModelRenderer(this);
			Head.setRotationPoint(0.0F, -1.1071F, -7.6742F);
			Head_r1 = new ModelRenderer(this);
			Head_r1.setRotationPoint(0.0F, -2.25F, 1.5F);
			Head.addChild(Head_r1);
			setRotationAngle(Head_r1, 0.3054F, 0.0F, 0.0F);
			Head_r1.setTextureOffset(32, 0).addBox(-3.0F, -2.75F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
			RightArm = new ModelRenderer(this);
			RightArm.setRotationPoint(-4.5F, 1.5F, -7.5F);
			RightArm_r1 = new ModelRenderer(this);
			RightArm_r1.setRotationPoint(-2.0F, 0.0F, 3.5F);
			RightArm.addChild(RightArm_r1);
			setRotationAngle(RightArm_r1, -1.1345F, 0.0F, 0.0F);
			RightArm_r1.setTextureOffset(37, 37).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 11.0F, 3.0F, 0.0F, false);
			spear = new ModelRenderer(this);
			spear.setRotationPoint(4.5F, 22.5F, 7.5F);
			RightArm.addChild(spear);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(-6.5F, -17.5F, -12.0F);
			spear.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(60, 40).addBox(-0.5F, -10.75F, 0.0F, 1.0F, 23.0F, 1.0F, 0.01F, false);
			SpearHead = new ModelRenderer(this);
			SpearHead.setRotationPoint(-6.5F, -28.2225F, -19.1851F);
			spear.addChild(SpearHead);
			setRotationAngle(SpearHead, -0.4363F, 0.0F, 0.0F);
			SpearHead.setTextureOffset(0, 60).addBox(-0.5F, -3.1792F, 0.5721F, 1.0F, 1.0F, 3.0F, 0.02F, false);
			SpearHead.setTextureOffset(0, 60).addBox(-0.5F, -2.1792F, 1.5721F, 1.0F, 1.0F, 3.0F, 0.02F, false);
			SpearHead.setTextureOffset(0, 0).addBox(-0.5F, -1.1792F, 2.5721F, 1.0F, 1.0F, 1.0F, 0.02F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, -0.2F, -0.1F);
			SpearHead.addChild(cube_r2);
			setRotationAngle(cube_r2, -1.5708F, 0.0F, 0.0F);
			cube_r2.setTextureOffset(0, 60).addBox(-0.5F, -2.6721F, -1.9792F, 1.0F, 1.0F, 3.0F, 0.02F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(0.0F, -1.2F, -1.1F);
			SpearHead.addChild(cube_r3);
			setRotationAngle(cube_r3, -1.5708F, 0.0F, 0.0F);
			cube_r3.setTextureOffset(0, 60).addBox(-0.5F, -2.6721F, -0.9792F, 1.0F, 1.0F, 2.0F, 0.02F, false);
			LeftArm = new ModelRenderer(this);
			LeftArm.setRotationPoint(5.25F, 1.5F, -7.5F);
			LeftArm.setTextureOffset(35, 12).addBox(-0.25F, -0.5F, 2.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
			LeftLeg = new ModelRenderer(this);
			LeftLeg.setRotationPoint(2.5F, 11.5F, -3.5F);
			LeftLeg.setTextureOffset(0, 22).addBox(-2.5F, -0.5F, 1.0F, 5.0F, 13.0F, 5.0F, 0.02F, false);
			RightLeg = new ModelRenderer(this);
			RightLeg.setRotationPoint(-2.5F, 11.5F, -3.5F);
			RightLeg.setTextureOffset(20, 22).addBox(-2.5F, -0.5F, 1.0F, 5.0F, 13.0F, 5.0F, 0.02F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			Body_r1 = new ModelRenderer(this);
			Body_r1.setRotationPoint(0.0F, -18.5625F, -2.0162F);
			bb_main.addChild(Body_r1);
			setRotationAngle(Body_r1, 0.3054F, 0.0F, 0.0F);
			Body_r1.setTextureOffset(0, 0).addBox(-5.0F, -7.75F, -2.5F, 10.0F, 16.0F, 6.0F, 0.01F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Head.render(matrixStack, buffer, packedLight, packedOverlay);
			RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
