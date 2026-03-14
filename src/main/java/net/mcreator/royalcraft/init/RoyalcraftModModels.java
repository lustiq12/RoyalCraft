/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.royalcraft.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.royalcraft.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class RoyalcraftModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelKings_Tower.LAYER_LOCATION, ModelKings_Tower::createBodyLayer);
		event.registerLayerDefinition(Modelmini_pekka.LAYER_LOCATION, Modelmini_pekka::createBodyLayer);
		event.registerLayerDefinition(ModelPrincess_Tower.LAYER_LOCATION, ModelPrincess_Tower::createBodyLayer);
		event.registerLayerDefinition(ModelCRTower.LAYER_LOCATION, ModelCRTower::createBodyLayer);
		event.registerLayerDefinition(Modelgolem.LAYER_LOCATION, Modelgolem::createBodyLayer);
		event.registerLayerDefinition(ModelMinion.LAYER_LOCATION, ModelMinion::createBodyLayer);
		event.registerLayerDefinition(ModelGiant.LAYER_LOCATION, ModelGiant::createBodyLayer);
		event.registerLayerDefinition(ModelHealthBar.LAYER_LOCATION, ModelHealthBar::createBodyLayer);
	}
}