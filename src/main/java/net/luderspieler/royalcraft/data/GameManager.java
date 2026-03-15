package net.luderspieler.royalcraft.data;

import net.luderspieler.royalcraft.data.GameSavedData.GameState;
import net.luderspieler.royalcraft.init.RoyalcraftModEntities;
import net.luderspieler.royalcraft.network.RoyalcraftModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

import java.util.*;

@EventBusSubscriber(modid = "royalcraft")
public class GameManager {

    public static String startGame(ServerLevel level, Player redPlayer, Player bluePlayer, double x, double y, double z) {
        // Place arena structure
        StructureTemplate template = level.getStructureManager()
                .getOrCreate(ResourceLocation.fromNamespaceAndPath("royalcraft", "arena"));
        if (template != null) {
            template.placeInWorld(level, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z),
                    new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
                    level.random, 3);
        }

        // Despawn existing game entities in arena area
        {
            final Vec3 _center = new Vec3((x + 22), (y + 0), (z + 17));
            for (Entity entityiterator : level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(40 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
                if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.tryParse("royalcraft:despawn")))) {
                    if (!entityiterator.level().isClientSide())
                        entityiterator.discard();
                }
            }
        }

        // Set teams
        setTeam(redPlayer, "Red");
        setTeam(bluePlayer, "Blue");

        // Teleport players
        teleportPlayer((ServerPlayer) redPlayer,  x + 36, y + 7, z + 17, 90,  20);
        teleportPlayer((ServerPlayer) bluePlayer, x + 10, y + 7, z + 17, -90, 20);

        // Spawn towers
        Entity blueTower1    = RoyalcraftModEntities.BLUE_TOWER.get().spawn(level, BlockPos.containing(x + 13, y + 1, z + 11), EntitySpawnReason.MOB_SUMMONED);
        Entity blueTower2    = RoyalcraftModEntities.BLUE_TOWER.get().spawn(level, BlockPos.containing(x + 13, y + 1, z + 22), EntitySpawnReason.MOB_SUMMONED);
        Entity blueKingTower = RoyalcraftModEntities.BLUE_KING_TOWER.get().spawn(level, BlockPos.containing(x + 10, y + 1, z + 17), EntitySpawnReason.MOB_SUMMONED);
        Entity redTower1     = RoyalcraftModEntities.RED_TOWER.get().spawn(level, BlockPos.containing(x + 32, y + 1, z + 11), EntitySpawnReason.MOB_SUMMONED);
        Entity redTower2     = RoyalcraftModEntities.RED_TOWER.get().spawn(level, BlockPos.containing(x + 32, y + 1, z + 22), EntitySpawnReason.MOB_SUMMONED);
        Entity redKingTower  = RoyalcraftModEntities.RED_KING_TOWER.get().spawn(level, BlockPos.containing(x + 36, y + 1, z + 17), EntitySpawnReason.MOB_SUMMONED);

        // Give cards & elixir
        giveRandomCards(bluePlayer, 9);
        giveRandomCards(redPlayer, 9);
        setElixir(bluePlayer, 10);
        setElixir(redPlayer, 10);

        // Save game state — store arena origin so we can find entities later
        GameState state = new GameState();
        state.gameId            = UUID.randomUUID().toString();
        state.playerBlueUUID    = bluePlayer.getStringUUID();
        state.playerRedUUID     = redPlayer.getStringUUID();
        state.blueTower1UUID    = blueTower1    != null ? blueTower1.getStringUUID()    : "";
        state.blueTower2UUID    = blueTower2    != null ? blueTower2.getStringUUID()    : "";
        state.blueKingTowerUUID = blueKingTower != null ? blueKingTower.getStringUUID() : "";
        state.redTower1UUID     = redTower1     != null ? redTower1.getStringUUID()     : "";
        state.redTower2UUID     = redTower2     != null ? redTower2.getStringUUID()     : "";
        state.redKingTowerUUID  = redKingTower  != null ? redKingTower.getStringUUID()  : "";
        state.arenaX = x;
        state.arenaY = y;
        state.arenaZ = z;

        GameSavedData.get(level).addGame(state);

        level.getServer().getPlayerList().broadcastSystemMessage(
                Component.literal("Game started! Red: " + redPlayer.getDisplayName().getString()
                        + " | Blue: " + bluePlayer.getDisplayName().getString()), false);

        return state.gameId;
    }

    // ── Server tick ──────────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        ServerLevel level = event.getServer().overworld();
        GameSavedData data = GameSavedData.get(level);
        List<String> toRemove = new ArrayList<>();

        for (GameState game : data.getGames()) {
            if (game.gameOver) {
                toRemove.add(game.gameId);
                continue;
            }
            tickGame(level, data, game);
        }

        for (String id : toRemove) data.removeGame(id);
    }

    @SubscribeEvent
    public static void onItemToss(net.neoforged.neoforge.event.entity.item.ItemTossEvent event) {
        if (event.getPlayer() instanceof Player player && player.level() instanceof ServerLevel level) {
            GameSavedData.get(level).getGameForPlayer(player.getStringUUID()).ifPresent(game -> {
                if (!game.gameOver) {
                    event.setCanceled(true);
                    player.getInventory().setItem(player.getInventory().getSelectedSlot(), event.getEntity().getItem());
                }
            });
        }
    }

    private static void tickGame(ServerLevel level, GameSavedData data, GameState game) {
        Player blue = getPlayer(level, game.playerBlueUUID);
        Player red  = getPlayer(level, game.playerRedUUID);
        boolean dirty = false;

        if ("dead".equals(game.redTower1State)) {
            game.redTower1State = "gone";
            broadcast(level, game, "The first Red Tower was destroyed by " + name(blue) + "!", 0xff4444);
            dirty = true;
        }
        if ("dead".equals(game.redTower2State)) {
            game.redTower2State = "gone";
            broadcast(level, game, "The second Red Tower was destroyed by " + name(blue) + "!", 0xff4444);
            dirty = true;
        }
        if ("dead".equals(game.redKingTowerState)) {
            broadcast(level, game, "The Red King Tower was destroyed! " + name(blue) + " won!", 0xff4444);
            game.gameOver = true;
            game.redKingTowerState = "gone"; // erst hier
            EndGame(level, game);
            dirty = true;
        }
        if ("dead".equals(game.blueTower1State)) {
            game.blueTower1State = "gone";
            broadcast(level, game, "The first Blue Tower was destroyed by " + name(red) + "!", 0x4444ff);
            dirty = true;
        }
        if ("dead".equals(game.blueTower2State)) {
            game.blueTower2State = "gone";
            broadcast(level, game, "The second Blue Tower was destroyed by " + name(red) + "!", 0x4444ff);
            dirty = true;
        }
        if ("dead".equals(game.blueKingTowerState)) {
            broadcast(level, game, "The Blue King Tower was destroyed! " + name(red) + " won!", 0x4444ff);
            game.gameOver = true;
            game.blueKingTowerState = "gone"; // erst hier
            EndGame(level, game);
            dirty = true;
        }

        if (dirty) data.setDirty();
    }

    // Uses stored arena coordinates — works even after towers are dead
    private static void EndGame(ServerLevel level, GameState game) {
        Vec3 center = new Vec3(game.arenaX + 22, game.arenaY, game.arenaZ + 17);
        AABB area = new AABB(center, center).inflate(40 / 2d);
        level.getEntitiesOfClass(Mob.class, area, e ->
                        e.getType().is(TagKey.create(Registries.ENTITY_TYPE,
                                ResourceLocation.parse("royalcraft:despawn"))))
                .forEach(mob -> {
                    mob.setNoAi(true);
                    mob.setDeltaMovement(0, 0, 0);
                });

        // Clear inventories
        Player blue = getPlayer(level, game.playerBlueUUID);
        Player red  = getPlayer(level, game.playerRedUUID);
        if (blue != null) blue.getInventory().clearContent();
        if (red  != null) red.getInventory().clearContent();

        if (game.blueKingTowerState.equals("gone")) {
            if (red instanceof ServerPlayer sp) {
                sp.connection.send(new ClientboundSetTitleTextPacket(Component.literal("You Win!").withStyle(s -> s.withColor(0xFF4444).withBold(true))));
                sp.connection.send(new ClientboundSetSubtitleTextPacket(Component.literal("GG").withStyle(s -> s.withColor(0xFF4444))));
            }
            if (blue instanceof ServerPlayer sp) {
                sp.connection.send(new ClientboundSetTitleTextPacket(Component.literal("You Lose!").withStyle(s -> s.withColor(0x4444FF).withBold(true))));
                sp.connection.send(new ClientboundSetSubtitleTextPacket(Component.literal("GG").withStyle(s -> s.withColor(0x4444FF))));
            }
        } else {
            if (blue instanceof ServerPlayer sp) {
                sp.connection.send(new ClientboundSetTitleTextPacket(Component.literal("You Win!").withStyle(s -> s.withColor(0x4444FF).withBold(true))));
                sp.connection.send(new ClientboundSetSubtitleTextPacket(Component.literal("GG").withStyle(s -> s.withColor(0x4444FF))));
            }
            if (red instanceof ServerPlayer sp) {
                sp.connection.send(new ClientboundSetTitleTextPacket(Component.literal("You Lose!").withStyle(s -> s.withColor(0xFF4444).withBold(true))));
                sp.connection.send(new ClientboundSetSubtitleTextPacket(Component.literal("GG").withStyle(s -> s.withColor(0xFF4444))));
            }
        }

    }

    // ── Tower death callback ─────────────────────────────────────────────────

    public static void onTowerDied(ServerLevel level, String towerUUID) {
        GameSavedData data = GameSavedData.get(level);
        data.getGameForTower(towerUUID).ifPresent(game -> {
            if      (towerUUID.equals(game.blueTower1UUID))    game.blueTower1State    = "dead";
            else if (towerUUID.equals(game.blueTower2UUID))    game.blueTower2State    = "dead";
            else if (towerUUID.equals(game.blueKingTowerUUID)) game.blueKingTowerState = "dead";
            else if (towerUUID.equals(game.redTower1UUID))     game.redTower1State     = "dead";
            else if (towerUUID.equals(game.redTower2UUID))     game.redTower2State     = "dead";
            else if (towerUUID.equals(game.redKingTowerUUID))  game.redKingTowerState  = "dead";
            data.setDirty();
        });
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private static void setTeam(Player player, String team) {
        RoyalcraftModVariables.PlayerVariables vars = player.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
        vars.Team = team;
        vars.markSyncDirty();
    }

    private static void setElixir(Player player, int amount) {
        RoyalcraftModVariables.PlayerVariables vars = player.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
        vars.Elixir = amount;
        vars.markSyncDirty();
    }

    private static void teleportPlayer(ServerPlayer player, double x, double y, double z, float yRot, float xRot) {
        player.connection.teleport(x, y, z, yRot, xRot);
    }

    private static void giveRandomCards(Player player, int count) {
        if (!(player.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable handler)) return;
        for (int i = 0; i < count; i++) {
            ItemStack stack = new ItemStack(
                    BuiltInRegistries.ITEM.getRandomElementOf(
                                    ItemTags.create(ResourceLocation.parse("royalcraft:cards")),
                                    RandomSource.create())
                            .orElseGet(() -> BuiltInRegistries.ITEM.wrapAsHolder(Items.AIR)).value());
            stack.setCount(1);
            handler.setStackInSlot(i, stack);
        }
    }

    private static Player getPlayer(ServerLevel level, String uuid) {
        try { return (Player) level.getEntity(UUID.fromString(uuid)); }
        catch (Exception e) { return null; }
    }

    private static String name(Player player) {
        return player != null ? player.getDisplayName().getString() : "Unknown";
    }

    private static void broadcast(ServerLevel level, GameState game, String msg, int color) {
        Player blue = getPlayer(level, game.playerBlueUUID);
        Player red  = getPlayer(level, game.playerRedUUID);
        Component component = Component.literal(msg).withColor(color).withStyle(ChatFormatting.BOLD);
        if (blue instanceof ServerPlayer sp) sp.connection.send(new ClientboundSystemChatPacket(component, false));
        if (red instanceof ServerPlayer sp) sp.connection.send(new ClientboundSystemChatPacket(component, false));
    }
}