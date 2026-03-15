package net.mcreator.royalcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.TriState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;

@EventBusSubscriber(Dist.CLIENT)
public class HealthBarOverlay {

    private static final java.util.Map<Integer, LivingEntity> healthbarEntities = new java.util.HashMap<>();

    @SubscribeEvent
    public static void onCanRender(RenderNameTagEvent.CanRender event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity living && entity.getType().is(
                TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("royalcraft:healthbar")))) {
            event.setCanRender(TriState.TRUE);
            event.setContent(Component.empty()); // leerer Name = kein Flickern
            healthbarEntities.put(entity.getId(), living);
        }
    }

    @SubscribeEvent
    public static void onDoRender(RenderNameTagEvent.DoRender event) {
        EntityRenderState state = event.getEntityRenderState();

        LivingEntity barEntity = healthbarEntities.values().stream()
                .filter(e -> Math.abs(e.getX() - state.x) < 0.2
                        && Math.abs(e.getY() - state.y) < 0.2
                        && Math.abs(e.getZ() - state.z) < 0.2)
                .findFirst().orElse(null);

        if (barEntity == null) return;
        event.setCanceled(true);

        if (barEntity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("royalcraft:red_tower")))) {
            System.out.println("RedTower HP: " + barEntity.getHealth() + "/" + barEntity.getMaxHealth());
        }

        float health = barEntity.getHealth();
        float maxHealth = barEntity.getMaxHealth();
        if (maxHealth <= 0) return;

        int percent = (int)(Math.floor((health / maxHealth) * 10) * 10);
        percent = Math.clamp(percent, 0, 100);

        ResourceLocation texture = ResourceLocation.parse(
                "royalcraft:textures/entities/healthbar" + percent + ".png");

        PoseStack poseStack = event.getPoseStack();
        var buffer = event.getMultiBufferSource();
        int light = event.getPackedLight();

        poseStack.pushPose();
        poseStack.translate(0, barEntity.getBbHeight() + 1.3, 0);
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        poseStack.scale(-0.025f * (40f / 9f), -0.075f, 1f);

        MultiBufferSource.BufferSource immediateBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vc = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        var matrix = poseStack.last().pose();
        vc.addVertex(matrix, -15, -4.5f, 0).setColor(255,255,255,255).setUv(0,0).setUv1(0,10).setUv2(light & 0xFFFF, (light >> 16) & 0xFFFF).setNormal(0,1,0);
        vc.addVertex(matrix, -15,  4.5f, 0).setColor(255,255,255,255).setUv(0,1).setUv1(0,10).setUv2(light & 0xFFFF, (light >> 16) & 0xFFFF).setNormal(0,1,0);
        vc.addVertex(matrix,  15,  4.5f, 0).setColor(255,255,255,255).setUv(1,1).setUv1(0,10).setUv2(light & 0xFFFF, (light >> 16) & 0xFFFF).setNormal(0,1,0);
        vc.addVertex(matrix,  15, -4.5f, 0).setColor(255,255,255,255).setUv(1,0).setUv1(0,10).setUv2(light & 0xFFFF, (light >> 16) & 0xFFFF).setNormal(0,1,0);

        poseStack.popPose();
    }
}