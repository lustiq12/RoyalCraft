package net.mcreator.royalcraft.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.Minecraft;

import net.mcreator.royalcraft.procedures.*;

@EventBusSubscriber(Dist.CLIENT)
public class BarOverlay {
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("royalcraft:textures/screens/bar_beginning.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_9 = ResourceLocation.parse("royalcraft:textures/screens/bar_middle_end.png");
	private static final ResourceLocation IMAGE_10 = ResourceLocation.parse("royalcraft:textures/screens/elixirdrop.png");

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (true) {
			if (Elixir1Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, 24, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir2Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, 33, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir3Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, 42, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir4Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, 51, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir5Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, 60, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir6Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, 69, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir7Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, 78, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir8Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, 87, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir9Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, 96, h - 16, 0, 0, 16, 4, 16, 4);
			}
			if (Elixir10Procedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, 105, h - 16, 0, 0, 16, 4, 16, 4);
			}
			event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, 15, h - 25, 0, 0, 16, 16, 16, 16);

			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetElixirProcedure.execute(entity), 18, h - 11, -1, false);
		}
	}
}