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
public class ModelKings_Tower extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("royalcraft", "model_kings_tower"), "main");
	public final ModelPart LeftBanner;
	public final ModelPart RightBanner;
	public final ModelPart Body;

	public ModelKings_Tower(ModelPart root) {
		super(root);
		this.LeftBanner = root.getChild("LeftBanner");
		this.RightBanner = root.getChild("RightBanner");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition LeftBanner = partdefinition.addOrReplaceChild("LeftBanner", CubeListBuilder.create().texOffs(96, 88).addBox(8.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(80, 173)
				.addBox(0.0F, -3.01F, -6.0F, 8.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 110).addBox(-0.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(18.0F, -19.0F, 0.0F));
		PartDefinition RightBanner = partdefinition.addOrReplaceChild("RightBanner", CubeListBuilder.create().texOffs(24, 110).addBox(-8.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(120, 173)
				.addBox(-8.0F, -3.01F, -6.0F, 8.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(48, 110).addBox(0.01F, -3.0F, -6.0F, 0.0F, 28.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.0F, -19.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, 0.0F, -47.0F, 48.0F, 40.0F, 48.0F, new CubeDeformation(0.0F)).texOffs(120, 116).addBox(-15.0F, -9.0F, -51.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(72, 128)
						.addBox(-5.0F, -9.0F, -51.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(112, 135).addBox(-15.0F, -9.0F, -41.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(152, 135)
						.addBox(31.0F, -9.0F, -41.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 150).addBox(31.0F, -9.0F, -51.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(72, 147)
						.addBox(21.0F, -9.0F, -51.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(120, 88).addBox(5.0F, -6.0F, -49.0F, 16.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(120, 102)
						.addBox(5.0F, -6.0F, -5.0F, 16.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 88).addBox(31.0F, -6.0F, -31.0F, 8.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(48, 88)
						.addBox(-13.0F, -6.0F, -31.0F, 8.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(160, 116).addBox(31.0F, -9.0F, -15.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(152, 154)
						.addBox(31.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(112, 154).addBox(21.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 169)
						.addBox(-15.0F, -9.0F, -15.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(168, 88).addBox(-15.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(40, 166)
						.addBox(-5.0F, -9.0F, -5.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)),
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