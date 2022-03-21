
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
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.mcreator.wobr.item.StoneSpearItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class OrmathWarriorEntity extends WobrModElements.ModElement {
	public static EntityType entity = null;
	public OrmathWarriorEntity(WobrModElements instance) {
		super(instance, 441);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("ormath_warrior")
						.setRegistryName("ormath_warrior");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10066330, -12577772, new Item.Properties().group(WoBCreativeTabItemGroup.tab))
				.setRegistryName("ormath_warrior_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new ModelOrmath_Warrior(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wobr:textures/ormath_warrior.png");
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
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.7, false));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(7, new AvoidEntityGoal(this, CreeperEntity.class, (float) 15, 1, 1.2));
			this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
			this.goalSelector.addGoal(9, new OpenDoorGoal(this, false));
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
			this.entityDropItem(new ItemStack(StoneSpearItem.block, (int) (1)));
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
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
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
	}

	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelOrmath_Warrior extends EntityModel {
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
		private final RendererModel bb_main;
		private final RendererModel Body_r1;
		public ModelOrmath_Warrior() {
			textureWidth = 64;
			textureHeight = 64;
			Head = new RendererModel(this);
			Head.setRotationPoint(0.0F, -1.1071F, -7.6742F);
			Head_r1 = new RendererModel(this);
			Head_r1.setRotationPoint(0.0F, -2.25F, 4.0F);
			Head.addChild(Head_r1);
			setRotationAngle(Head_r1, 0.3054F, 0.0F, 0.0F);
			Head_r1.cubeList.add(new ModelBox(Head_r1, 32, 0, -3.0F, -2.75F, -3.0F, 6, 6, 6, 0.0F, false));
			RightArm = new RendererModel(this);
			RightArm.setRotationPoint(-4.5F, 1.5F, -7.5F);
			RightArm_r1 = new RendererModel(this);
			RightArm_r1.setRotationPoint(-2.0F, 0.0F, 6.0F);
			RightArm.addChild(RightArm_r1);
			setRotationAngle(RightArm_r1, -1.1345F, 0.0F, 0.0F);
			RightArm_r1.cubeList.add(new ModelBox(RightArm_r1, 37, 37, -1.5F, -0.5F, -1.5F, 3, 11, 3, 0.0F, false));
			spear = new RendererModel(this);
			spear.setRotationPoint(4.5F, 22.5F, 7.5F);
			RightArm.addChild(spear);
			cube_r1 = new RendererModel(this);
			cube_r1.setRotationPoint(-6.5F, -17.5F, -9.5F);
			spear.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
			cube_r1.cubeList.add(new ModelBox(cube_r1, 60, 40, -0.5F, -10.75F, 0.0F, 1, 23, 1, 0.01F, false));
			SpearHead = new RendererModel(this);
			SpearHead.setRotationPoint(-6.5F, -28.2225F, -19.1851F);
			spear.addChild(SpearHead);
			setRotationAngle(SpearHead, -0.4363F, 0.0F, 0.0F);
			SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 60, -0.5F, -4.2357F, 2.8378F, 1, 1, 3, 0.02F, false));
			SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 60, -0.5F, -3.2357F, 3.8378F, 1, 1, 3, 0.02F, false));
			SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 0, -0.5F, -2.2357F, 4.8378F, 1, 1, 1, 0.02F, false));
			cube_r2 = new RendererModel(this);
			cube_r2.setRotationPoint(0.0F, -0.2F, -0.1F);
			SpearHead.addChild(cube_r2);
			setRotationAngle(cube_r2, -1.5708F, 0.0F, 0.0F);
			cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 60, -0.5F, -4.9378F, -3.0357F, 1, 1, 3, 0.02F, false));
			cube_r3 = new RendererModel(this);
			cube_r3.setRotationPoint(0.0F, -1.2F, -1.1F);
			SpearHead.addChild(cube_r3);
			setRotationAngle(cube_r3, -1.5708F, 0.0F, 0.0F);
			cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 60, -0.5F, -4.9378F, -2.0357F, 1, 1, 2, 0.02F, false));
			LeftArm = new RendererModel(this);
			LeftArm.setRotationPoint(5.25F, 1.5F, -7.5F);
			LeftArm.cubeList.add(new ModelBox(LeftArm, 35, 12, -0.25F, -0.5F, 4.5F, 3, 11, 3, 0.0F, false));
			LeftLeg = new RendererModel(this);
			LeftLeg.setRotationPoint(2.5F, 11.5F, -3.5F);
			LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 22, -2.5F, -0.5F, 3.5F, 5, 13, 5, 0.02F, false));
			RightLeg = new RendererModel(this);
			RightLeg.setRotationPoint(-2.5F, 11.5F, -3.5F);
			RightLeg.cubeList.add(new ModelBox(RightLeg, 20, 22, -2.5F, -0.5F, 3.5F, 5, 13, 5, 0.02F, false));
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			Body_r1 = new RendererModel(this);
			Body_r1.setRotationPoint(0.0F, -18.5625F, 0.4838F);
			bb_main.addChild(Body_r1);
			setRotationAngle(Body_r1, 0.3054F, 0.0F, 0.0F);
			Body_r1.cubeList.add(new ModelBox(Body_r1, 0, 0, -5.0F, -7.75F, -2.5F, 10, 16, 6, 0.01F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Head.render(f5);
			RightArm.render(f5);
			LeftArm.render(f5);
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
			this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
