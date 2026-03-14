/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.royalcraft.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.royalcraft.client.gui.AsdScreen;

@EventBusSubscriber(Dist.CLIENT)
public class RoyalcraftModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(RoyalcraftModMenus.ASD.get(), AsdScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}