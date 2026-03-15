package net.luderspieler.royalcraft.client.model;

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
public class ModelPrincess_Tower extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("royalcraft", "model_princess_tower"), "main");
	public final ModelPart LeftBanner;
	public final ModelPart RightBanner;
	public final ModelPart Body;

	public ModelPrincess_Tower(ModelPart root) {
		super(root);
		this.LeftBanner = root.getChild("LeftBanner");
		this.RightBanner = root.getChild("RightBanner");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition LeftBanner = partdefinition.addOrReplaceChild("LeftBanner", CubeListBuilder.create().texOffs(88, 80).addBox(8.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(72, 120)
				.addBox(0.0F, -3.01F, -6.0F, 8.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 100).addBox(-0.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, -19.0F, 0.0F));
		PartDefinition RightBanner = partdefinition.addOrReplaceChild("RightBanner", CubeListBuilder.create().texOffs(24, 100).addBox(-8.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(112, 126)
				.addBox(-8.0F, -3.01F, -6.0F, 8.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(48, 100).addBox(0.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -19.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 0.0F, -43.0F, 40.0F, 40.0F, 40.0F, new CubeDeformation(0.0F)).texOffs(112, 80).addBox(6.0F, -6.0F, -43.0F, 14.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(112, 94)
						.addBox(6.0F, -6.0F, -11.0F, 14.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 80).addBox(25.0F, -6.0F, -30.0F, 8.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(44, 80)
						.addBox(-7.0F, -6.0F, -30.0F, 8.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(152, 125).addBox(25.0F, -9.0F, -16.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(112, 108)
						.addBox(25.0F, -9.0F, -12.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 140).addBox(20.0F, -9.0F, -11.0F, 5.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(72, 132)
						.addBox(-7.0F, -9.0F, -12.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(140, 155).addBox(-7.0F, -9.0F, -16.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(140, 138)
						.addBox(1.0F, -9.0F, -11.0F, 5.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(146, 108).addBox(1.0F, -9.0F, -43.0F, 5.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(156, 80)
						.addBox(-7.0F, -9.0F, -34.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(106, 138).addBox(-7.0F, -9.0F, -43.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(60, 150)
						.addBox(20.0F, -9.0F, -43.0F, 5.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(86, 156).addBox(25.0F, -9.0F, -34.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 140)
						.addBox(25.0F, -9.0F, -43.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-13.0F, -16.0F, 23.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

	}
}