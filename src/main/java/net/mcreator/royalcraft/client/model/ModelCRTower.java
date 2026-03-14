package net.mcreator.royalcraft.client.model;

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
public class ModelCRTower extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("royalcraft", "model_cr_tower"), "main");
	public final ModelPart bb_main;

	public ModelCRTower(ModelPart root) {
		super(root);
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
				CubeListBuilder.create().texOffs(0, 45).addBox(-16.0F, -36.0F, -16.0F, 32.0F, 36.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-20.0F, -41.0F, -20.0F, 40.0F, 5.0F, 40.0F, new CubeDeformation(0.0F)).texOffs(128, 67)
						.addBox(-25.0F, -51.0F, -25.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(128, 86).addBox(-25.0F, -51.0F, 16.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 113)
						.addBox(-22.0F, -47.0F, -16.0F, 5.0F, 6.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(148, 105).addBox(16.0F, -51.0F, -25.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(74, 113)
						.addBox(18.0F, -47.0F, -16.0F, 5.0F, 6.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(148, 124).addBox(16.0F, -51.0F, 16.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(128, 45)
						.addBox(-16.0F, -47.0F, 18.0F, 32.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(128, 56).addBox(-16.0F, -47.0F, -22.0F, 32.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
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