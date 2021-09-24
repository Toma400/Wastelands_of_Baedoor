// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class ModelOrmath_Villager extends EntityModel<Entity> {
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightArm;
	private final ModelRenderer bb_main;
	private final ModelRenderer Head_r1;
	private final ModelRenderer cube_r1;

	public ModelOrmath_Villager() {
		textureWidth = 64;
		textureHeight = 64;

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(0.0F, 5.4375F, -2.5162F);
		LeftLeg.setTextureOffset(0, 22).addBox(0.0F, 5.5625F, -0.4838F, 5.0F, 13.0F, 5.0F, 0.02F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(0.0F, 5.4375F, -2.5162F);
		RightLeg.setTextureOffset(20, 22).addBox(-5.0F, 5.5625F, -0.4838F, 5.0F, 13.0F, 5.0F, 0.02F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(0.0F, 5.4375F, -2.5162F);
		LeftArm.setTextureOffset(35, 12).addBox(5.0F, -4.4375F, -3.4838F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(0.0F, 5.4375F, -2.5162F);
		RightArm.setTextureOffset(37, 37).addBox(-8.0F, -4.4375F, -3.4838F, 3.0F, 11.0F, 3.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		Head_r1 = new ModelRenderer(this);
		Head_r1.setRotationPoint(0.0F, -26.1187F, -2.599F);
		bb_main.addChild(Head_r1);
		setRotationAngle(Head_r1, 0.3054F, 0.0F, 0.0F);
		Head_r1.setTextureOffset(32, 0).addBox(-3.0F, -5.1565F, -6.5142F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -18.5625F, -2.5162F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3054F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 0).addBox(-5.0F, -7.75F, -2.5F, 10.0F, 16.0F, 6.0F, 0.01F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
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
		this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.Head_r1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head_r1.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}