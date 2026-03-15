/*
 *	luderspieler note: This file will be REGENERATED on each build.
 */
package net.luderspieler.royalcraft.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.luderspieler.royalcraft.client.gui.AsdScreen;

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