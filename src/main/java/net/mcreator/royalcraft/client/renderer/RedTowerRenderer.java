package net.mcreator.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.royalcraft.entity.RedTowerEntity;
import net.mcreator.royalcraft.client.model.ModelPrincess_Tower;

public class RedTowerRenderer extends MobRenderer<RedTowerEntity, LivingEntityRenderState, ModelPrincess_Tower> {
	private RedTowerEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/princess_tower_red.png");

	public RedTowerRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelPrincess_Tower(context.bakeLayer(ModelPrincess_Tower.LAYER_LOCATION)), 1.66f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(RedTowerEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}