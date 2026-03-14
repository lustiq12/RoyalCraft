package net.mcreator.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.royalcraft.entity.RedGiantEntity;
import net.mcreator.royalcraft.client.model.ModelGiant;

import com.mojang.blaze3d.vertex.PoseStack;

public class RedGiantRenderer extends MobRenderer<RedGiantEntity, LivingEntityRenderState, ModelGiant> {
	private RedGiantEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/giant.png");

	public RedGiantRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGiant(context.bakeLayer(ModelGiant.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(RedGiantEntity entity, LivingEntityRenderState state, float partialTicks) {
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