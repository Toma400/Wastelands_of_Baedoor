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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
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