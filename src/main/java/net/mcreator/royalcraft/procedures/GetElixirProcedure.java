package net.mcreator.royalcraft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;

public class GetElixirProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return new java.text.DecimalFormat("########").format(Math.floor(entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir));
	}
}