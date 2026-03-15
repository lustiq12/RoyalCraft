package net.luderspieler.royalcraft.client.model;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelGiant extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("royalcraft", "modelgiant"), "main");
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart LeftArm;
	public final ModelPart RightArm;

	public ModelGiant(ModelPart root) {
		super(root);
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(44, 0).addBox(-2.25F, -1.0F, -2.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 17.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(46, 13).addBox(-2.0F, -1.0F, -4.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.25F, 17.0F, 2.0F));
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 50).addBox(-3.5F, -5.0F, -6.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(40, 27)
				.addBox(-4.0F, -2.0F, -5.5F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(40, 37).addBox(-3.0F, -6.0F, -5.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 27).addBox(-6.25F, -5.0F, -3.75F, 13.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
				.addBox(-7.0F, -16.0F, -4.0F, 14.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 17).addBox(-7.25F, -7.0F, -4.25F, 15.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(7.0F, 2.0F, 0.0F));
		PartDefinition bone6_r1 = LeftArm.addOrReplaceChild("bone6_r1", CubeListBuilder.create().texOffs(0, 40).addBox(0.0F, -2.0F, -3.0F, 4.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, -0.1309F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-7.0F, 2.0F, 0.0F));
		PartDefinition bone7_r1 = RightArm.addOrReplaceChild("bone7_r1", CubeListBuilder.create().texOffs(20, 40).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.1309F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.LeftLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}