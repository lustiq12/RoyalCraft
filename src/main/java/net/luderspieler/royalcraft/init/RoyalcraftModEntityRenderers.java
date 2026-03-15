/*
 *    luderspieler note: This file will be REGENERATED on each build.
 */
package net.luderspieler.royalcraft.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.luderspieler.royalcraft.client.renderer.*;

@EventBusSubscriber(Dist.CLIENT)
public class RoyalcraftModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RoyalcraftModEntities.RED_TOWER.get(), RedTowerRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.RED_GOLEM.get(), RedGolemRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_GOLEM.get(), BlueGolemRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_MINION.get(), BlueMinionRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.RED_MINION.get(), RedMinionRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_TOWER.get(), BlueTowerRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.RED_BARBARIAN.get(), RedBarbarianRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_BARBARIAN.get(), BlueBarbarianRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_KING_TOWER.get(), BlueKingTowerRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.RED_KING_TOWER.get(), RedKingTowerRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_GIANT.get(), BlueGiantRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.RED_GIANT.get(), RedGiantRenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.BLUE_MINI_PEKKA.get(), BlueMiniPEKKARenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.RED_MINI_PEKKA.get(), RedMiniPEKKARenderer::new);
		event.registerEntityRenderer(RoyalcraftModEntities.DARK_SPIT.get(), DarkSpitRenderer::new);
	}
}
