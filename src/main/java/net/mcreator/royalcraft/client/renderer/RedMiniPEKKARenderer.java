package net.mcreator.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.royalcraft.entity.RedMiniPEKKAEntity;
import net.mcreator.royalcraft.client.model.Modelmini_pekka;

public class RedMiniPEKKARenderer extends MobRenderer<RedMiniPEKKAEntity, LivingEntityRenderState, Modelmini_pekka> {
	private RedMiniPEKKAEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/mini_pekka.png");

	public RedMiniPEKKARenderer(EntityRendererProvider.Context context) {
		super(context, new Modelmini_pekka(context.bakeLayer(Modelmini_pekka.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(RedMiniPEKKAEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}