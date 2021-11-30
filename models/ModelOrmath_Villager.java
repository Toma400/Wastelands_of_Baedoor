// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelOrmath_Villager extends EntityModel {
	private final ModelRenderer whole;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer head_r1;
	private final ModelRenderer arm_right;
	private final ModelRenderer arm_left;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public ModelOrmath_Villager() {
		textureWidth = 64;
		textureHeight = 64;

		whole = new ModelRenderer(this);
		whole.setRotationPoint(0.0F, 5.4375F, 1.4838F);

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		whole.addChild(body_r1);
		setRotationAngle(body_r1, 0.3054F, 0.0F, 0.0F);
		body_r1.cubeList.add(new ModelBox(body_r1, 0, 0, -5.0F, -7.75F, -2.5F, 10, 16, 6, 0.01F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		whole.addChild(head);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, -8.7946F, -4.158F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.3054F, 0.0F, 0.0F);
		head_r1.cubeList.add(new ModelBox(head_r1, 32, 0, -3.0F, -2.75F, -3.0F, 6, 6, 6, 0.0F, false));

		arm_right = new ModelRenderer(this);
		arm_right.setRotationPoint(0.0F, 18.5625F, 5.5162F);
		whole.addChild(arm_right);
		arm_right.cubeList.add(new ModelBox(arm_right, 37, 37, -8.0F, -23.0F, -9.0F, 3, 11, 3, 0.0F, false));

		arm_left = new ModelRenderer(this);
		arm_left.setRotationPoint(0.0F, 18.5625F, 5.5162F);
		whole.addChild(arm_left);
		arm_left.cubeList.add(new ModelBox(arm_left, 35, 12, 5.0F, -23.0F, -9.0F, 3, 11, 3, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(0.0F, -8.7946F, -4.158F);
		whole.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 22, 0.0F, 14.3571F, 3.6742F, 5, 13, 5, 0.02F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(0.0F, -8.7946F, -4.158F);
		whole.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 20, 22, -5.0F, 14.3571F, 3.6742F, 5, 13, 5, 0.02F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		whole.render(f5);
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
		this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.arm_right.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.arm_left.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}