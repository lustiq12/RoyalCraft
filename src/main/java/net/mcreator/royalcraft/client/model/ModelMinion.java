package net.mcreator.royalcraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ModelMinion extends EntityModel<LivingEntityRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath("royalcraft", "model_minion"), "main");

	private final ModelPart body;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart leftArm;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart head;
	private final ModelPart rightArm;

	public ModelMinion(ModelPart root) {
		super(root);
		this.body      = root.getChild("body");
		this.leftLeg   = root.getChild("leftLeg");
		this.rightLeg  = root.getChild("rightLeg");
		this.leftArm   = root.getChild("leftArm");
		this.leftWing  = root.getChild("leftWing");
		this.rightWing = root.getChild("rightWing");
		this.head      = root.getChild("head");
		this.rightArm  = root.getChild("rightArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(1.2F, 24.4F, 1.0F));
		leftLeg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -0.5231F, -0.8497F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(-1.3F, 24.4F, 1.0F));
		rightLeg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 9).addBox(0.0F, -0.5231F, -0.8497F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(1.9F, 20.9F, -2.6F));
		leftArm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.7F, -0.4F, 0.0F, -0.3054F, 0.4363F));

		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 21.3F, -0.4F));
		leftWing.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 0).addBox(0.0736F, -3.0094F, -0.0041F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.1F, 0.3408F, 0.6219F, 0.5468F));

		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(0.0F, 21.3F, -0.4F));
		rightWing.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(10, 14).addBox(-0.0736F, -3.0094F, -0.0041F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.1F, 0.3408F, -0.6219F, -0.5468F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -1.4F, -2.4F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.4F, -3.3F));
		head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -0.2F, -0.3927F, 0.0F, -0.3054F));
		head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 16).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -0.2F, -0.3927F, 0.0F, 0.3054F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(-1.9F, 20.9F, -2.6F));
		rightArm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(14, 6).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.7F, -0.4F, 0.0F, 0.3054F, -0.4363F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;

		this.leftLeg.xRot   = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.rightLeg.xRot  = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leftArm.xRot   = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.rightArm.xRot  = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.leftWing.zRot  =  Mth.cos(state.ageInTicks * 0.3F) * 0.2F;
		this.rightWing.zRot = -Mth.cos(state.ageInTicks * 0.3F) * 0.2F;
		this.head.yRot = state.yRot / (180F / (float) Math.PI);
		this.head.xRot = state.xRot / (180F / (float) Math.PI);
	}
}