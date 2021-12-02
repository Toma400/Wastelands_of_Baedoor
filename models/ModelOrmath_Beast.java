// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelOrmath_Beast extends EntityModel {
	private final ModelRenderer Head;
	private final ModelRenderer TopJaw;
	private final ModelRenderer BottomJaw;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftlLeg2;
	private final ModelRenderer RightLeg2;
	private final ModelRenderer bb_main;
	private final ModelRenderer Spike2_r1;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;

	public ModelOrmath_Beast() {
		textureWidth = 128;
		textureHeight = 128;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.6667F, -0.8333F, -22.25F);
		Head.cubeList.add(new ModelBox(Head, 47, 24, -0.3333F, -12.6667F, -11.0F, 0, 6, 13, 0.0F, false));

		TopJaw = new ModelRenderer(this);
		TopJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(TopJaw);
		TopJaw.cubeList.add(new ModelBox(TopJaw, 47, 16, -5.3333F, -6.6667F, -11.75F, 10, 7, 14, 0.01F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 0, 8, 3.4167F, 0.3333F, -11.25F, 1, 3, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 7, 7, -5.0833F, 0.3333F, -11.25F, 1, 3, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 122, 66, -3.5833F, 0.3333F, -11.25F, 1, 2, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 4, 30, -5.0833F, 0.3333F, -9.25F, 1, 2, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 0, 30, 3.4167F, 0.3333F, -9.25F, 1, 2, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 4, 10, 3.4167F, 0.3333F, -7.25F, 1, 1, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 8, 30, -5.0833F, 0.3333F, -7.25F, 1, 1, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 0, 37, -1.8333F, 0.3333F, -11.25F, 1, 1, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 7, 36, 0.1667F, 0.3333F, -11.25F, 1, 1, 1, 0.0F, false));
		TopJaw.cubeList.add(new ModelBox(TopJaw, 122, 66, 1.9167F, 0.3333F, -11.25F, 1, 2, 1, 0.0F, false));

		BottomJaw = new ModelRenderer(this);
		BottomJaw.setRotationPoint(-0.3333F, 1.6896F, -0.1102F);
		Head.addChild(BottomJaw);
		setRotationAngle(BottomJaw, 0.5672F, 0.0F, 0.0F);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 11.1608F, 12.7055F);
		BottomJaw.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.0036F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -5.0F, 14.4345F, -21.4072F, 1, 1, 3, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 4, 33, 0.5F, 14.4345F, -19.4072F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 33, 4.0F, 10.4345F, -19.4072F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 7, 32, -5.0F, 10.4345F, -19.4072F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 5, 0, -5.0F, 12.4345F, -20.4072F, 1, 1, 2, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 5, 4, 4.0F, 12.4345F, -20.4072F, 1, 1, 2, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 122, 66, 2.25F, 14.4345F, -20.4072F, 1, 1, 2, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 7, 34, -1.5F, 14.4345F, -19.4072F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 122, 66, -3.25F, 14.4345F, -20.4072F, 1, 1, 2, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 7, 34, -1.5F, 14.4345F, -19.4072F, 1, 1, 1, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 4, 4.0F, 14.4345F, -21.4072F, 1, 1, 3, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 9.474F, 11.6309F);
		BottomJaw.addChild(cube_r2);
		setRotationAngle(cube_r2, -1.0036F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 90, -5.0F, 3.6845F, -16.4072F, 10, 12, 3, 0.02F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(4.05F, 8.8F, -9.8F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 70, 72, -3.0F, 0.0F, -3.0F, 6, 16, 6, -0.2F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-6.2F, 8.8F, -20.8F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 78, -3.0F, 0.0F, 8.0F, 6, 16, 6, -0.2F, false));

		LeftlLeg2 = new ModelRenderer(this);
		LeftlLeg2.setRotationPoint(4.8F, 8.8F, 10.2F);
		LeftlLeg2.cubeList.add(new ModelBox(LeftlLeg2, 81, 0, -3.0F, 3.0F, -3.0F, 6, 12, 6, -0.2F, false));

		RightLeg2 = new ModelRenderer(this);
		RightLeg2.setRotationPoint(-6.95F, 8.8F, 10.2F);
		RightLeg2.cubeList.add(new ModelBox(RightLeg2, 88, 88, -3.0F, 3.0F, -3.0F, 6, 12, 6, -0.2F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 30, -9.6F, -25.7108F, 2.4785F, 17, 15, 13, 0.41F, false));

		Spike2_r1 = new ModelRenderer(this);
		Spike2_r1.setRotationPoint(-1.0F, -34.5F, -7.75F);
		bb_main.addChild(Spike2_r1);
		setRotationAngle(Spike2_r1, -0.1745F, 0.0F, 0.0F);
		Spike2_r1.cubeList.add(new ModelBox(Spike2_r1, 100, 109, 0.0F, 0.25F, -3.25F, 0, 4, 9, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.0F, -23.5836F, -22.2343F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.5672F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 34, 72, -5.0F, -6.5F, -1.5F, 10, 10, 8, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.0F, -23.8453F, -15.0802F);
		bb_main.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.1745F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 58, -7.0F, -5.0F, -3.5F, 14, 13, 7, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-1.0F, -19.5F, 1.0F);
		bb_main.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.1745F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 48, 46, -8.0F, -6.0F, -4.0F, 16, 13, 12, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -9.0F, -9.0F, -16.0F, 18, 18, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Head.render(f5);
		LeftLeg.render(f5);
		RightLeg.render(f5);
		LeftlLeg2.render(f5);
		RightLeg2.render(f5);
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
		this.LeftlLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.RightLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}