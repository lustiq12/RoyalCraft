package net.mcreator.royalcraft.procedures;

import net.mcreator.royalcraft.data.GameManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class BlueKingTowerEntityDiesProcedure {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) return;
        if (world instanceof ServerLevel level)
            GameManager.onTowerDied(level, entity.getStringUUID());
    }
}
