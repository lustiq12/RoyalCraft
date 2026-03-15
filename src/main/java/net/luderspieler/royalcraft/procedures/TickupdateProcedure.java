package net.luderspieler.royalcraft.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.Minecraft;

import net.luderspieler.royalcraft.network.RoyalcraftModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class TickupdateProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (10 > entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir) {
				{
					RoyalcraftModVariables.PlayerVariables _vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
					_vars.Elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir + 0.025;
					_vars.markSyncDirty();
				}
			}
		}
		if (world.isClientSide()) {
			if (Minecraft.getInstance().screen != null && Minecraft.getInstance().screen.getClass() == net.minecraft.client.gui.screens.inventory.InventoryScreen.class) {
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
		}
	}
}