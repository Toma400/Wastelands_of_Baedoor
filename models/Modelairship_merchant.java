// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelairship_merchant extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer brim;
	private final ModelRenderer nose;
	private final ModelRenderer arms;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public Modelairship_merchant() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 21.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-6.5F, -28.0F, -3.0F, 13.0F, 22.0F, 9.0F, 0.5F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -28.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 31).addBox(-4.5F, -17.0F, -4.0F, 9.0F, 17.0F, 9.0F, 0.0F, false);

		brim = new ModelRenderer(this);
		brim.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(brim);
		setRotationAngle(brim, -1.5708F, 0.0F, 0.0F);
		brim.setTextureOffset(36, 37).addBox(-7.5F, -8.0F, -10.0F, 15.0F, 15.0F, 2.0F, 0.1F, false);

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		head.addChild(nose);
		nose.setTextureOffset(62, 18).addBox(-1.5F, -2.0F, -6.0F, 3.0F, 5.0F, 2.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, -23.0F, -1.0F);
		body.addChild(arms);
		setRotationAngle(arms, -0.7854F, 0.0F, 0.0F);
		arms.setTextureOffset(87, 71).addBox(-4.0F, 1.5F, -2.0F, 9.0F, 7.0F, 6.0F, 0.0F, false);
		arms.setTextureOffset(70, 31).addBox(-10.0F, -5.5F, -2.0F, 6.0F, 14.0F, 6.0F, 0.0F, false);
		arms.setTextureOffset(0, 57).addBox(5.0F, -5.5F, -2.0F, 6.0F, 14.0F, 6.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.0F, -12.0F, 0.0F);
		body.addChild(RightLeg);
		RightLeg.setTextureOffset(54, 54).addBox(-4.0F, 0.0F, -1.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.0F, -12.0F, 0.0F);
		body.addChild(LeftLeg);
		LeftLeg.setTextureOffset(30, 54).addBox(-2.0F, 0.0F, -1.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
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