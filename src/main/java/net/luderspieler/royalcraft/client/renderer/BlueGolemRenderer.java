package net.luderspieler.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.luderspieler.royalcraft.entity.BlueGolemEntity;
import net.luderspieler.royalcraft.client.model.Modelgolem;

import com.mojang.blaze3d.vertex.PoseStack;

public class BlueGolemRenderer extends MobRenderer<BlueGolemEntity, LivingEntityRenderState, Modelgolem> {
	private BlueGolemEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/golemtxt.png");

	public BlueGolemRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelgolem(context.bakeLayer(Modelgolem.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BlueGolemEntity entity, LivingEntityRenderState state, float partialTicks) {
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