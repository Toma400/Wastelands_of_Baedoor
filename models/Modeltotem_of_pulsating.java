// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class Modeltotem_of_pulsating extends EntityModel<Entity> {
	private final ModelRenderer bb_main;

	public Modeltotem_of_pulsating() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 28).addBox(-1.0F, -16.0F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(20, 0).addBox(-3.0F, -17.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-2.0F, -12.0F, -7.0F, 3.0F, 1.0F, 14.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 15).addBox(-2.0F, -11.0F, -6.0F, 3.0F, 1.0F, 12.0F, 0.0F, false);
		bb_main.setTextureOffset(18, 15).addBox(-2.0F, -8.0F, -5.0F, 3.0F, 1.0F, 10.0F, 0.0F, false);
		bb_main.setTextureOffset(22, 26).addBox(-2.0F, -5.0F, -4.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}