package net.mcreator.royalcraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import net.mcreator.royalcraft.entity.HealthBarEntity;
import net.mcreator.royalcraft.client.model.ModelHealthBar;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class HealthBarRenderer extends MobRenderer<HealthBarEntity, LivingEntityRenderState, ModelHealthBar> {
	private HealthBarEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("royalcraft:textures/entities/empty.png");

	public HealthBarRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelHealthBar(context.bakeLayer(ModelHealthBar.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<>(this) {
			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, LivingEntityRenderState state, float headYaw, float headPitch) {
				if (entity == null) return;
				int percent = getHealthPercent(entity);
				if (percent < 0) return; // invalid
				String textureName = "royalcraft:textures/entities/healthbar" + (percent * 10) + ".png";
				ResourceLocation texture = ResourceLocation.parse(textureName);
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
				EntityModel model = new ModelHealthBar(Minecraft.getInstance().getEntityModels().bakeLayer(ModelHealthBar.LAYER_LOCATION));
				model.setupAnim(state);
				model.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(state, 0));
			}
		});
	}

	// Returns 0,1,2,...,10 (maps to healthbar00-100), or -1 if invalid
	private static int getHealthPercent(HealthBarEntity entity) {
		int hp = entity.getEntityData().get(HealthBarEntity.DATA_Health);
		int max = entity.getEntityData().get(HealthBarEntity.DATA_MaxHealth);
		if (hp < 0 || max <= 0) return -1;
		return Math.round((float) hp / max * 10);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(HealthBarEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}