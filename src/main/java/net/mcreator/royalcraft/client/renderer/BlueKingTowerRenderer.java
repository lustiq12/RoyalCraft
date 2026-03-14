package net.mcreator.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.royalcraft.entity.BlueKingTowerEntity;
import net.mcreator.royalcraft.client.model.ModelKings_Tower;

public class BlueKingTowerRenderer extends MobRenderer<BlueKingTowerEntity, LivingEntityRenderState, ModelKings_Tower> {
	private BlueKingTowerEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/kings_tower_blue.png");

	public BlueKingTowerRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelKings_Tower(context.bakeLayer(ModelKings_Tower.LAYER_LOCATION)), 2.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BlueKingTowerEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}