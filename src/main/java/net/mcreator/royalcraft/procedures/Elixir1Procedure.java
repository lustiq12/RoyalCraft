package net.mcreator.royalcraft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;

public class Elixir1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir >= 1;
	}
}