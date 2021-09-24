// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class ModelOrmath_Shaman extends EntityModel<Entity> {
	private final ModelRenderer Left_Arm;
	private final ModelRenderer Right_Arm;
	private final ModelRenderer cube_r1;
	private final ModelRenderer Wand;
	private final ModelRenderer Head;
	private final ModelRenderer cube_r2;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer bb_main;
	private final ModelRenderer Body_r1;

	public ModelOrmath_Shaman() {
		textureWidth = 64;
		textureHeight = 64;

		Left_Arm = new ModelRenderer(this);
		Left_Arm.setRotationPoint(4.0F, 2.0F, -0.775F);
		Left_Arm.setTextureOffset(8, 42).addBox(-0.18F, -0.213F, -1.437F, 3.0F, 11.0F, 3.0F, 0.1F, false);

		Right_Arm = new ModelRenderer(this);
		Right_Arm.setRotationPoint(-4.568F, 3.0621F, -0.4373F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.04F, -0.0071F, -0.2427F);
		Right_Arm.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.3526F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(20, 42).addBox(-3.458F, -0.3809F, -1.4446F, 3.0F, 11.0F, 3.0F, 0.1F, false);

		Wand = new ModelRenderer(this);
		Wand.setRotationPoint(5.568F, 36.9379F, 6.4373F);
		Right_Arm.addChild(Wand);
		Wand.setTextureOffset(0, 24).addBox(-8.4F, -40.2628F, -17.5357F, 2.0F, 24.0F, 2.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.45F, -0.875F, -4.52F);
		setRotationAngle(Head, -0.1309F, 0.0F, 0.0F);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.05F, -0.95F, -1.08F);
		Head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.3054F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(36, 0).addBox(-3.05F, -2.8F, -2.92F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.846F, 11.503F, 2.672F);
		LeftLeg.setTextureOffset(8, 24).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, -0.1F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.95F, 12.503F, 2.672F);
		RightLeg.setTextureOffset(28, 24).addBox(-2.5F, -1.5F, -2.5F, 5.0F, 13.0F, 5.0F, -0.1F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		Body_r1 = new ModelRenderer(this);
		Body_r1.setRotationPoint(0.0F, -15.7904F, -1.2527F);
		bb_main.addChild(Body_r1);
		setRotationAngle(Body_r1, 0.4538F, 0.0F, 0.0F);
		Body_r1.setTextureOffset(0, 0).addBox(-5.524F, -9.664F, -1.576F, 10.0F, 16.0F, 8.0F, -0.4F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Left_Arm.render(matrixStack, buffer, packedLight, packedOverlay);
		Right_Arm.render(matrixStack, buffer, packedLight, packedOverlay);
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
		LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
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