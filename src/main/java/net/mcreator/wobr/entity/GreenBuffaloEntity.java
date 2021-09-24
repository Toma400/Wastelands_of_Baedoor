
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wobr.procedures.BuffaloRideProcedure;
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@WobrModElements.ModElement.Tag
public class GreenBuffaloEntity extends WobrModElements.ModElement {
	public static EntityType entity = null;
	public GreenBuffaloEntity(WobrModElements instance) {
		super(instance, 459);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.AMBIENT).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.5f, 2f)).build("green_buffalo")
						.setRegistryName("green_buffalo");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13031657, -15125748, new Item.Properties().group(WoBCreativeTabItemGroup.tab))
				.setRegistryName("green_buffalo_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelOrmath_Beast(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wobr:textures/bufallo_green_maned.png");
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
			experienceValue = 5;
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
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.65, false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
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
			this.entityDropItem(new ItemStack(Items.BEEF, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_beast_idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_beast_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("wobr:ormath_beast_death"));
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			super.processInteract(sourceentity, hand);
			sourceentity.startRiding(this);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
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
				BuffaloRideProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.18);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(44);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK);
			this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1D);
		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;
				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					float forward = ((LivingEntity) entity).moveForward;
					float strafe = 0;
					super.travel(new Vec3d(strafe, 0, forward));
				}
				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.getPosX() - this.prevPosX;
				double d0 = this.getPosZ() - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.travel(dir);
		}
	}

	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.15 - 1.16
	// Paste this class into your mod and generate all required imports
	public static class ModelOrmath_Beast extends EntityModel<Entity> {
		private final ModelRenderer Head;
		private final ModelRenderer TopJaw;
		private final ModelRenderer BottomJaw;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer LeftLeg;
		private final ModelRenderer RightLeg;
		private final ModelRenderer LeftlLeg2;
		private final ModelRenderer RightLeg2;
		private final ModelRenderer bb_main;
		private final ModelRenderer Spike2_r1;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		public ModelOrmath_Beast() {
			textureWidth = 128;
			textureHeight = 128;
			Head = new ModelRenderer(this);
			Head.setRotationPoint(-0.6667F, -0.8333F, -22.25F);
			Head.setTextureOffset(47, 24).addBox(-0.3333F, -12.6667F, -11.0F, 0.0F, 6.0F, 13.0F, 0.0F, false);
			TopJaw = new ModelRenderer(this);
			TopJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
			Head.addChild(TopJaw);
			TopJaw.setTextureOffset(47, 16).addBox(-5.3333F, -6.6667F, -11.75F, 10.0F, 7.0F, 14.0F, 0.01F, false);
			TopJaw.setTextureOffset(0, 8).addBox(3.4167F, 0.3333F, -11.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(7, 7).addBox(-5.0833F, 0.3333F, -11.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(122, 66).addBox(-3.5833F, 0.3333F, -11.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(4, 30).addBox(-5.0833F, 0.3333F, -9.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(0, 30).addBox(3.4167F, 0.3333F, -9.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(4, 10).addBox(3.4167F, 0.3333F, -7.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(8, 30).addBox(-5.0833F, 0.3333F, -7.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(0, 37).addBox(-1.8333F, 0.3333F, -11.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(7, 36).addBox(0.1667F, 0.3333F, -11.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			TopJaw.setTextureOffset(122, 66).addBox(1.9167F, 0.3333F, -11.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			BottomJaw = new ModelRenderer(this);
			BottomJaw.setRotationPoint(-0.3333F, 1.6896F, -0.1102F);
			Head.addChild(BottomJaw);
			setRotationAngle(BottomJaw, 0.5672F, 0.0F, 0.0F);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 11.1608F, 12.7055F);
			BottomJaw.addChild(cube_r1);
			setRotationAngle(cube_r1, -1.0036F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(0, 0).addBox(-5.0F, 14.4345F, -21.4072F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			cube_r1.setTextureOffset(4, 33).addBox(0.5F, 14.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(0, 33).addBox(4.0F, 10.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(7, 32).addBox(-5.0F, 10.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(5, 0).addBox(-5.0F, 12.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(5, 4).addBox(4.0F, 12.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(122, 66).addBox(2.25F, 14.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(7, 34).addBox(-1.5F, 14.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(122, 66).addBox(-3.25F, 14.4345F, -20.4072F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			cube_r1.setTextureOffset(7, 34).addBox(-1.5F, 14.4345F, -19.4072F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(0, 4).addBox(4.0F, 14.4345F, -21.4072F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 9.474F, 11.6309F);
			BottomJaw.addChild(cube_r2);
			setRotationAngle(cube_r2, -1.0036F, 0.0F, 0.0F);
			cube_r2.setTextureOffset(24, 90).addBox(-5.0F, 3.6845F, -16.4072F, 10.0F, 12.0F, 3.0F, 0.02F, false);
			LeftLeg = new ModelRenderer(this);
			LeftLeg.setRotationPoint(4.05F, 8.8F, -9.8F);
			LeftLeg.setTextureOffset(70, 72).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F, -0.2F, false);
			RightLeg = new ModelRenderer(this);
			RightLeg.setRotationPoint(-6.2F, 8.8F, -20.8F);
			RightLeg.setTextureOffset(0, 78).addBox(-3.0F, 0.0F, 8.0F, 6.0F, 16.0F, 6.0F, -0.2F, false);
			LeftlLeg2 = new ModelRenderer(this);
			LeftlLeg2.setRotationPoint(4.8F, 8.8F, 10.2F);
			LeftlLeg2.setTextureOffset(81, 0).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 12.0F, 6.0F, -0.2F, false);
			RightLeg2 = new ModelRenderer(this);
			RightLeg2.setRotationPoint(-6.95F, 8.8F, 10.2F);
			RightLeg2.setTextureOffset(88, 88).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 12.0F, 6.0F, -0.2F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 30).addBox(-9.6F, -25.7108F, 2.4785F, 17.0F, 15.0F, 13.0F, 0.41F, false);
			Spike2_r1 = new ModelRenderer(this);
			Spike2_r1.setRotationPoint(-1.0F, -34.5F, -7.75F);
			bb_main.addChild(Spike2_r1);
			setRotationAngle(Spike2_r1, -0.1745F, 0.0F, 0.0F);
			Spike2_r1.setTextureOffset(100, 109).addBox(0.0F, 0.25F, -3.25F, 0.0F, 4.0F, 9.0F, 0.0F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(-1.0F, -23.5836F, -22.2343F);
			bb_main.addChild(cube_r3);
			setRotationAngle(cube_r3, -0.5672F, 0.0F, 0.0F);
			cube_r3.setTextureOffset(34, 72).addBox(-5.0F, -6.5F, -1.5F, 10.0F, 10.0F, 8.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(-1.0F, -23.8453F, -15.0802F);
			bb_main.addChild(cube_r4);
			setRotationAngle(cube_r4, -0.1745F, 0.0F, 0.0F);
			cube_r4.setTextureOffset(0, 58).addBox(-7.0F, -5.0F, -3.5F, 14.0F, 13.0F, 7.0F, 0.0F, false);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(-1.0F, -19.5F, 1.0F);
			bb_main.addChild(cube_r5);
			setRotationAngle(cube_r5, -0.1745F, 0.0F, 0.0F);
			cube_r5.setTextureOffset(48, 46).addBox(-8.0F, -6.0F, -4.0F, 16.0F, 13.0F, 12.0F, 0.0F, false);
			cube_r5.setTextureOffset(0, 0).addBox(-9.0F, -9.0F, -16.0F, 18.0F, 18.0F, 12.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			float f = 1;
			if (this.isChild) {
				f = (float) ((double) f * 0.5D);
			}
			matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
			matrixStack.scale(f, f, f);
			Head.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftlLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
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
			this.LeftlLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.RightLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
