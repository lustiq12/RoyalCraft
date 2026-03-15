package net.luderspieler.royalcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.luderspieler.royalcraft.client.model.ModelMinion;
import net.luderspieler.royalcraft.entity.BlueMinionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class BlueMinionRenderer extends MobRenderer<BlueMinionEntity, LivingEntityRenderState, ModelMinion> {
	private BlueMinionEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/minion.png");

	public BlueMinionRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelMinion(context.bakeLayer(ModelMinion.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BlueMinionEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(2f, 2f, 2f);
	}
}