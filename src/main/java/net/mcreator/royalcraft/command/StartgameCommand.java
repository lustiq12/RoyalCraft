package net.mcreator.royalcraft.command;

import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

import net.mcreator.royalcraft.data.GameManager;

import com.mojang.brigadier.arguments.DoubleArgumentType;

@EventBusSubscriber
public class StartgameCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("startgame")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("Red", EntityArgument.player())
                        .then(Commands.argument("Blue", EntityArgument.player())
                                .then(Commands.argument("x", DoubleArgumentType.doubleArg())
                                        .then(Commands.argument("y", DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("z", DoubleArgumentType.doubleArg())
                                                        .executes(ctx -> {
                                                            ServerPlayer red  = EntityArgument.getPlayer(ctx, "Red");
                                                            ServerPlayer blue = EntityArgument.getPlayer(ctx, "Blue");
                                                            double x = DoubleArgumentType.getDouble(ctx, "x");
                                                            double y = DoubleArgumentType.getDouble(ctx, "y");
                                                            double z = DoubleArgumentType.getDouble(ctx, "z");
                                                            if (ctx.getSource().getLevel() instanceof ServerLevel level) {
                                                                GameManager.startGame(level, red, blue, x, y, z);
                                                            }
                                                            return 1;
                                                        })))))));
    }
}