package net.mcreator.royalcraft.procedures;

import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class WorldTickerProcedure {
	@SubscribeEvent
	public static void onWorldTick(LevelTickEvent.Post event) {
		execute(event, event.getLevel());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		RoyalcraftModVariables.WorldVariables.get(world).WorldTimer = RoyalcraftModVariables.WorldVariables.get(world).WorldTimer + 1;
		RoyalcraftModVariables.WorldVariables.get(world).markSyncDirty();
		if (RoyalcraftModVariables.WorldVariables.get(world).WorldTimer == 15) {
			RoyalcraftModVariables.WorldVariables.get(world).WorldTimer = 0;
			RoyalcraftModVariables.WorldVariables.get(world).markSyncDirty();
		}
	}
}