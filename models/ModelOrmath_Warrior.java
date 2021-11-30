// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelOrmath_Warrior extends EntityModel {
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
		Head_r1.setRotationPoint(0.0F, -2.25F, 4.0F);
		Head.addChild(Head_r1);
		setRotationAngle(Head_r1, 0.3054F, 0.0F, 0.0F);
		Head_r1.cubeList.add(new ModelBox(Head_r1, 32, 0, -3.0F, -2.75F, -3.0F, 6, 6, 6, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-4.5F, 1.5F, -7.5F);

		RightArm_r1 = new ModelRenderer(this);
		RightArm_r1.setRotationPoint(-2.0F, 0.0F, 6.0F);
		RightArm.addChild(RightArm_r1);
		setRotationAngle(RightArm_r1, -1.1345F, 0.0F, 0.0F);
		RightArm_r1.cubeList.add(new ModelBox(RightArm_r1, 37, 37, -1.5F, -0.5F, -1.5F, 3, 11, 3, 0.0F, false));

		spear = new ModelRenderer(this);
		spear.setRotationPoint(4.5F, 22.5F, 7.5F);
		RightArm.addChild(spear);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-6.5F, -17.5F, -9.5F);
		spear.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 60, 40, -0.5F, -10.75F, 0.0F, 1, 23, 1, 0.01F, false));

		SpearHead = new ModelRenderer(this);
		SpearHead.setRotationPoint(-6.5F, -28.2225F, -19.1851F);
		spear.addChild(SpearHead);
		setRotationAngle(SpearHead, -0.4363F, 0.0F, 0.0F);
		SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 60, -0.5F, -4.2357F, 2.8378F, 1, 1, 3, 0.02F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 60, -0.5F, -3.2357F, 3.8378F, 1, 1, 3, 0.02F, false));
		SpearHead.cubeList.add(new ModelBox(SpearHead, 0, 0, -0.5F, -2.2357F, 4.8378F, 1, 1, 1, 0.02F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -0.2F, -0.1F);
		SpearHead.addChild(cube_r2);
		setRotationAngle(cube_r2, -1.5708F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 60, -0.5F, -4.9378F, -3.0357F, 1, 1, 3, 0.02F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -1.2F, -1.1F);
		SpearHead.addChild(cube_r3);
		setRotationAngle(cube_r3, -1.5708F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 60, -0.5F, -4.9378F, -2.0357F, 1, 1, 2, 0.02F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.25F, 1.5F, -7.5F);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 35, 12, -0.25F, -0.5F, 4.5F, 3, 11, 3, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.5F, 11.5F, -3.5F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 22, -2.5F, -0.5F, 3.5F, 5, 13, 5, 0.02F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.5F, 11.5F, -3.5F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 20, 22, -2.5F, -0.5F, 3.5F, 5, 13, 5, 0.02F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		Body_r1 = new ModelRenderer(this);
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

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}