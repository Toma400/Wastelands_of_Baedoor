// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelOrmath_Shaman extends EntityModel {
	private final ModelRenderer Left_Arm;
	private final ModelRenderer Right_Arm;
	private final ModelRenderer cube_r1;
	private final ModelRenderer Wand;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Head;
	private final ModelRenderer cube_r3;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer bb_main;
	private final ModelRenderer Body_r1;

	public ModelOrmath_Shaman() {
		textureWidth = 64;
		textureHeight = 64;

		Left_Arm = new ModelRenderer(this);
		Left_Arm.setRotationPoint(4.0F, 2.0F, -0.775F);
		Left_Arm.cubeList.add(new ModelBox(Left_Arm, 8, 42, -0.18F, -0.213F, -1.437F, 3, 11, 3, 0.1F, false));

		Right_Arm = new ModelRenderer(this);
		Right_Arm.setRotationPoint(-4.568F, 3.0621F, -0.4373F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.04F, -0.0071F, -0.2427F);
		Right_Arm.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.3526F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 20, 42, -3.458F, -0.3809F, -1.4446F, 3, 11, 3, 0.1F, false));

		Wand = new ModelRenderer(this);
		Wand.setRotationPoint(5.568F, 20.9379F, 0.4373F);
		Right_Arm.addChild(Wand);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-7.4F, -24.7628F, -13.5357F);
		Wand.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 24, -1.0F, -1.5F, 1.0F, 2, 27, 2, -0.5F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.45F, -0.875F, -4.52F);
		setRotationAngle(Head, -0.1309F, 0.0F, 0.0F);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.05F, -0.95F, -1.08F);
		Head.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3054F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 36, 0, -3.05F, -2.8F, -2.92F, 6, 6, 6, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.846F, 11.503F, 2.672F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 8, 24, -2.5F, -0.5F, -2.5F, 5, 13, 5, -0.1F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.95F, 12.503F, 2.672F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 28, 24, -2.5F, -1.5F, -2.5F, 5, 13, 5, -0.1F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		Body_r1 = new ModelRenderer(this);
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

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Left_Arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}