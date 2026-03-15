package net.luderspieler.royalcraft.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import java.util.*;

public class GameSavedData extends SavedData {

    // Encode the 6 tower states as one comma-separated string: "alive,alive,alive,alive,alive,alive"
    // Order: blueTower1,blueTower2,blueKingTower,redTower1,redTower2,redKingTower
    private static String encodeStates(GameState s) {
        return s.blueTower1State + "," + s.blueTower2State + "," + s.blueKingTowerState + ","
                + s.redTower1State + "," + s.redTower2State + "," + s.redKingTowerState;
    }
    private static GameState decodeStates(String gameId, String pBlue, String pRed,
                                          String bt1, String bt2, String bkt, String rt1, String rt2, String rkt,
                                          String states, boolean gameOver, double ax, double ay, double az) {
        String[] s = states.split(",", -1);
        GameState gs = new GameState();
        gs.gameId            = gameId;
        gs.playerBlueUUID    = pBlue;
        gs.playerRedUUID     = pRed;
        gs.blueTower1UUID    = bt1;
        gs.blueTower2UUID    = bt2;
        gs.blueKingTowerUUID = bkt;
        gs.redTower1UUID     = rt1;
        gs.redTower2UUID     = rt2;
        gs.redKingTowerUUID  = rkt;
        gs.blueTower1State    = s.length > 0 ? s[0] : "alive";
        gs.blueTower2State    = s.length > 1 ? s[1] : "alive";
        gs.blueKingTowerState = s.length > 2 ? s[2] : "alive";
        gs.redTower1State     = s.length > 3 ? s[3] : "alive";
        gs.redTower2State     = s.length > 4 ? s[4] : "alive";
        gs.redKingTowerState  = s.length > 5 ? s[5] : "alive";
        gs.gameOver = gameOver;
        gs.arenaX = ax;
        gs.arenaY = ay;
        gs.arenaZ = az;
        return gs;
    }

    private static final Codec<GameState> GAME_STATE_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("gameId").forGetter(s -> s.gameId),
            Codec.STRING.fieldOf("playerBlue").forGetter(s -> s.playerBlueUUID),
            Codec.STRING.fieldOf("playerRed").forGetter(s -> s.playerRedUUID),
            Codec.STRING.fieldOf("blueTower1").forGetter(s -> s.blueTower1UUID),
            Codec.STRING.fieldOf("blueTower2").forGetter(s -> s.blueTower2UUID),
            Codec.STRING.fieldOf("blueKingTower").forGetter(s -> s.blueKingTowerUUID),
            Codec.STRING.fieldOf("redTower1").forGetter(s -> s.redTower1UUID),
            Codec.STRING.fieldOf("redTower2").forGetter(s -> s.redTower2UUID),
            Codec.STRING.fieldOf("redKingTower").forGetter(s -> s.redKingTowerUUID),
            Codec.STRING.fieldOf("states").forGetter(GameSavedData::encodeStates),
            Codec.BOOL.fieldOf("gameOver").forGetter(s -> s.gameOver),
            Codec.DOUBLE.fieldOf("arenaX").forGetter(s -> s.arenaX),
            Codec.DOUBLE.fieldOf("arenaY").forGetter(s -> s.arenaY),
            Codec.DOUBLE.fieldOf("arenaZ").forGetter(s -> s.arenaZ)
    ).apply(instance, GameSavedData::decodeStates));

    private static final Codec<GameSavedData> CODEC = Codec.list(GAME_STATE_CODEC).xmap(
            list -> {
                GameSavedData data = new GameSavedData();
                for (GameState s : list) data.games.put(s.gameId, s);
                return data;
            },
            data -> new ArrayList<>(data.games.values())
    );

    public static final SavedDataType<GameSavedData> TYPE = new SavedDataType<>(
            "royalcraft_games",
            ctx -> new GameSavedData(),
            ctx -> CODEC
    );

    private final Map<String, GameState> games = new HashMap<>();

    public static class GameState {
        public String gameId            = UUID.randomUUID().toString();
        public String playerBlueUUID    = "";
        public String playerRedUUID     = "";
        public String blueTower1UUID    = "";
        public String blueTower2UUID    = "";
        public String blueKingTowerUUID = "";
        public String redTower1UUID     = "";
        public String redTower2UUID     = "";
        public String redKingTowerUUID  = "";
        public String blueTower1State    = "alive";
        public String blueTower2State    = "alive";
        public String blueKingTowerState = "alive";
        public String redTower1State     = "alive";
        public String redTower2State     = "alive";
        public String redKingTowerState  = "alive";
        public boolean gameOver = false;
        public double arenaX = 0, arenaY = 0, arenaZ = 0;
    }

    public static GameSavedData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(TYPE);
    }

    public void addGame(GameState state) { games.put(state.gameId, state); setDirty(); }
    public void removeGame(String gameId) { games.remove(gameId); setDirty(); }
    public Collection<GameState> getGames() { return games.values(); }

    public Optional<GameState> getGameForTower(String towerUUID) {
        return games.values().stream().filter(g ->
                g.blueTower1UUID.equals(towerUUID) || g.blueTower2UUID.equals(towerUUID) ||
                        g.blueKingTowerUUID.equals(towerUUID) || g.redTower1UUID.equals(towerUUID) ||
                        g.redTower2UUID.equals(towerUUID) || g.redKingTowerUUID.equals(towerUUID)
        ).findFirst();
    }

    public Optional<GameState> getGameForPlayer(String playerUUID) {
        return games.values().stream().filter(g ->
                g.playerBlueUUID.equals(playerUUID) || g.playerRedUUID.equals(playerUUID)
        ).findFirst();
    }
}