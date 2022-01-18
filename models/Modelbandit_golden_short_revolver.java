// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelbandit_golden_short_revolver extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer brim;
	private final ModelRenderer nose;
	private final ModelRenderer arms;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer gun;

	public Modelbandit_golden_short_revolver() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(34, 18).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(28, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		brim = new ModelRenderer(this);
		brim.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(brim);
		setRotationAngle(brim, -1.5708F, 0.0F, 0.0F);
		brim.setTextureOffset(0, 24).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F, 0.1F, false);

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		head.addChild(nose);
		nose.setTextureOffset(22, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, -22.0F, 0.0F);
		body.addChild(arms);
		setRotationAngle(arms, -0.7854F, 0.0F, 0.0F);
		arms.setTextureOffset(46, 48).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		arms.setTextureOffset(50, 36).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		arms.setTextureOffset(16, 41).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.0F, -12.0F, 0.0F);
		body.addChild(RightLeg);
		RightLeg.setTextureOffset(0, 41).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.0F, -12.0F, 0.0F);
		body.addChild(LeftLeg);
		LeftLeg.setTextureOffset(34, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		gun = new ModelRenderer(this);
		gun.setRotationPoint(3.0F, 2.0F, 2.0F);
		gun.setTextureOffset(96, 75).addBox(-3.5F, -0.5F, -13.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		gun.setTextureOffset(0, 0).addBox(-3.5F, 0.5F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		gun.setTextureOffset(28, 65).addBox(-3.5F, 1.5F, -8.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		gun.setTextureOffset(32, 65).addBox(-3.5F, 2.5F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		gun.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}