package net.luderspieler.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.luderspieler.royalcraft.entity.BlueTowerEntity;
import net.luderspieler.royalcraft.client.model.ModelPrincess_Tower;

public class BlueTowerRenderer extends MobRenderer<BlueTowerEntity, LivingEntityRenderState, ModelPrincess_Tower> {
	private BlueTowerEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/princess_tower_blue.png");

	public BlueTowerRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelPrincess_Tower(context.bakeLayer(ModelPrincess_Tower.LAYER_LOCATION)), 1.66f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BlueTowerEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}