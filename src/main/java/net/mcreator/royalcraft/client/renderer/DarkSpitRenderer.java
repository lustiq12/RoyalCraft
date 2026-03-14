package net.mcreator.royalcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mcreator.royalcraft.entity.DarkSpitEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class DarkSpitRenderer extends EntityRenderer<DarkSpitEntity, EntityRenderState> {
	private final ResourceLocation texture = ResourceLocation.parse("royalcraft:textures/projectiles/spit.png");

	public DarkSpitRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}

	@Override
	public void extractRenderState(DarkSpitEntity entity, EntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	public ResourceLocation getTextureLocation(EntityRenderState state) {
		return texture;
	}

	@Override
	public void render(EntityRenderState state, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.scale(0.5f, 0.5f, 0.5f);

		com.mojang.blaze3d.vertex.VertexConsumer vertexConsumer = buffer.getBuffer(
				net.minecraft.client.renderer.RenderType.entityCutoutNoCull(texture));

		net.minecraft.client.renderer.block.model.BakedQuad quad;
		// Billboard rendering - immer zur Kamera zeigen
		poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());

		float r = 0.5f;
		var matrix = poseStack.last().pose();
		vertexConsumer.addVertex(matrix, -r, -r, 0).setColor(255,255,255,255).setUv(0,1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0,1,0);
		vertexConsumer.addVertex(matrix,  r, -r, 0).setColor(255,255,255,255).setUv(1,1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0,1,0);
		vertexConsumer.addVertex(matrix,  r,  r, 0).setColor(255,255,255,255).setUv(1,0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0,1,0);
		vertexConsumer.addVertex(matrix, -r,  r, 0).setColor(255,255,255,255).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(0,1,0);

		poseStack.popPose();
		super.render(state, poseStack, buffer, packedLight);
	}
}