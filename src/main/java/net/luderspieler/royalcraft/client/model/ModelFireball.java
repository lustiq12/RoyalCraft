package net.luderspieler.royalcraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class ModelFireball extends EntityModel<EntityRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath("royalcraft", "model_fireball"), "main");

	private final ModelPart ground;
	private final ModelPart ball;
	private final ModelPart tail;

	public ModelFireball(ModelPart root) {
		super(root);
		this.ground = root.getChild("ground");
		this.ball = root.getChild("ball");
		this.tail = this.ball.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ground = partdefinition.addOrReplaceChild("ground", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, -0.9F, -22.0F, 44.0F, 0.0F, 44.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.9F, 0.0F));
		PartDefinition ball = partdefinition.addOrReplaceChild("ball", CubeListBuilder.create().texOffs(0, 44).addBox(-16.0F, -29.0F, -16.0F, 32.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -43.0F, -33.0F, 0.5672F, 0.0F, 0.0F));
		ball.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 108).addBox(-13.0F, -29.0F, -13.0F, 26.0F, 32.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(EntityRenderState state) {
		// Animation wird extern gesteuert
	}
}