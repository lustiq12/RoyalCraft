package net.mcreator.royalcraft.client.screens;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.Minecraft;


@EventBusSubscriber(Dist.CLIENT)
public class BarOverlay {
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("royalcraft:textures/screens/bar_beginning.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_10 = ResourceLocation.parse("royalcraft:textures/screens/elixirdrop.png");

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();
		Player entity = Minecraft.getInstance().player;
		if (entity == null) return;

		double elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir;

		// Elixir-Drop Icon
		event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, 15, h - 25, 0, 0, 16, 16, 16, 16);

		// Bar
		for (int i = 1; i <= 10; i++) {
			if (elixir >= i) {
				ResourceLocation img = (i == 1) ? IMAGE_0 : IMAGE_1;
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, img, 24 + (i-1)*9, h - 16, 0, 0, 16, 4, 16, 4);
			}
		}

		// Elixir Zahl
		event.getGuiGraphics().drawString(Minecraft.getInstance().font,
				String.valueOf((int) Math.floor(elixir)), 18, h - 11, -1, false);
	}
}