package net.luderspieler.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.luderspieler.royalcraft.entity.BlueGiantEntity;
import net.luderspieler.royalcraft.client.model.ModelGiant;

import com.mojang.blaze3d.vertex.PoseStack;

public class BlueGiantRenderer extends MobRenderer<BlueGiantEntity, LivingEntityRenderState, ModelGiant> {
	private BlueGiantEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/giant.png");

	public BlueGiantRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGiant(context.bakeLayer(ModelGiant.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BlueGiantEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
		poseStack.scale(1.2f, 1.2f, 1.2f);
	}
}