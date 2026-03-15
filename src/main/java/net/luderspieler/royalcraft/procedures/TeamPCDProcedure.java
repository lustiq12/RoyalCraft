package net.luderspieler.royalcraft.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import net.luderspieler.royalcraft.network.RoyalcraftModVariables;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class TeamPCDProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		{
			RoyalcraftModVariables.PlayerVariables _vars = (commandParameterEntity(arguments, "name")).getData(RoyalcraftModVariables.PLAYER_VARIABLES);
			_vars.Team = StringArgumentType.getString(arguments, "team");
			_vars.markSyncDirty();
		}
	}

	private static Entity commandParameterEntity(CommandContext<CommandSourceStack> arguments, String parameter) {
		try {
			return EntityArgument.getEntity(arguments, parameter);
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}