package net.luderspieler.royalcraft.procedures;

import net.luderspieler.royalcraft.init.RoyalcraftModEntities;
import net.luderspieler.royalcraft.network.RoyalcraftModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelAccessor;

public class SummonTroopProcedure {

    public enum Troop {
        BARBARIAN, GIANT, GOLEM, MINI_PEKKA, MINION
    }

    public static void execute(LevelAccessor world, Entity entity, Troop troop, int count, int elixirCost) {
        if (entity == null) return;

        RoyalcraftModVariables.PlayerVariables vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
        if (vars.Elixir < elixirCost) return;

        String team = vars.Team;
        boolean isRed = team.equals("Red");
        boolean isBlue = team.equals("Blue");
        if (!isRed && !isBlue) return;

        String blockTag = isRed ? "royalcraft:red" : "royalcraft:blue";
        BlockPos target = getTargetBlock(entity);
        if (!world.getBlockState(target).is(BlockTags.create(ResourceLocation.parse(blockTag)))) return;

        vars.Elixir -= elixirCost;
        vars.markSyncDirty();
        UseCardProcedure.execute(entity);

        if (!(world instanceof ServerLevel level)) return;
        BlockPos spawnPos = target.above();

        for (int i = 0; i < count; i++) {
            Entity spawned = switch (troop) {
                case BARBARIAN -> isRed
                        ? RoyalcraftModEntities.RED_BARBARIAN.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED)
                        : RoyalcraftModEntities.BLUE_BARBARIAN.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED);
                case GIANT -> isRed
                        ? RoyalcraftModEntities.RED_GIANT.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED)
                        : RoyalcraftModEntities.BLUE_GIANT.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED);
                case GOLEM -> isRed
                        ? RoyalcraftModEntities.RED_GOLEM.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED)
                        : RoyalcraftModEntities.BLUE_GOLEM.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED);
                case MINI_PEKKA -> isRed
                        ? RoyalcraftModEntities.RED_MINI_PEKKA.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED)
                        : RoyalcraftModEntities.BLUE_MINI_PEKKA.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED);
                case MINION -> isRed
                        ? RoyalcraftModEntities.RED_MINION.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED)
                        : RoyalcraftModEntities.BLUE_MINION.get().spawn(level, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            };
        }
    }

    private static BlockPos getTargetBlock(Entity entity) {
        return entity.level().clip(new ClipContext(
                entity.getEyePosition(1f),
                entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)),
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                entity
        )).getBlockPos();
    }
}