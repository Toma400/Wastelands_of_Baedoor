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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}