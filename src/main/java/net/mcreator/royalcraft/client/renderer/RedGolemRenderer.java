package net.mcreator.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.royalcraft.entity.RedGolemEntity;
import net.mcreator.royalcraft.client.model.Modelgolem;

import com.mojang.blaze3d.vertex.PoseStack;

public class RedGolemRenderer extends MobRenderer<RedGolemEntity, LivingEntityRenderState, Modelgolem> {
	private RedGolemEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/golemtxt.png");

	public RedGolemRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelgolem(context.bakeLayer(Modelgolem.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(RedGolemEntity entity, LivingEntityRenderState state, float partialTicks) {
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