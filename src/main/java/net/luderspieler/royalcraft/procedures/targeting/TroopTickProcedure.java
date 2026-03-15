package net.luderspieler.royalcraft.procedures.targeting;

import net.luderspieler.royalcraft.network.RoyalcraftModVariables;
import net.luderspieler.royalcraft.procedures.InvulnerableProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;

import java.util.Comparator;

public class TroopTickProcedure {

    public static void execute(LevelAccessor world, Entity entity,
                               String targetTeam, boolean targetGround, boolean targetAir, boolean flying) {
        execute(world, entity, targetTeam, targetGround, targetAir, flying, -1);
    }

    public static void execute(LevelAccessor world, Entity entity,
                               String targetTeam, boolean targetGround, boolean targetAir, boolean flying, float attackRange) {

        if (entity == null) return;
        Level lvl = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        InvulnerableProcedure.execute(world, entity);

        // Höhenkorrektur
        if (flying) {
            double targetY = getCeilingY(entity, lvl) - 9.0;
            if (Math.abs(y - targetY) > 2.0) {
                entity.setPos(x, targetY, z);
            } else {
                entity.setDeltaMovement(
                        entity.getDeltaMovement().x,
                        (targetY - y) * 0.3,
                        entity.getDeltaMovement().z);
            }
        }

        // Target suchen alle 15 Ticks
        if (RoyalcraftModVariables.WorldVariables.get(world).WorldTimer == 1) {
            String teamTag = targetTeam.toLowerCase();
            TagKey<EntityType<?>> towerTag = TagKey.create(Registries.ENTITY_TYPE,
                    ResourceLocation.parse("royalcraft:" + teamTag + "towers"));
            TagKey<EntityType<?>> teamEntityTag = TagKey.create(Registries.ENTITY_TYPE,
                    ResourceLocation.parse("royalcraft:" + teamTag));
            TagKey<EntityType<?>> flyingTag = TagKey.create(Registries.ENTITY_TYPE,
                    ResourceLocation.parse("royalcraft:flying"));

            LivingEntity target = lvl.getEntitiesOfClass(LivingEntity.class,
                            new AABB(x - 40, y - 40, z - 40, x + 40, y + 40, z + 40),
                            e -> {
                                boolean isTower = e.getType().is(towerTag);
                                boolean isGround = targetGround && e.getType().is(teamEntityTag)
                                        && !e.getType().is(flyingTag);
                                boolean isAir = targetAir && e.getType().is(teamEntityTag)
                                        && e.getType().is(flyingTag);
                                return isTower || isGround || isAir;
                            })
                    .stream()
                    .min(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z)))
                    .orElse(null);

            if (target != null && entity instanceof Mob _entity) {
                _entity.setTarget(target);
            }

        }

        // Jeden Tick: Rotation + Abstand halten
        if (entity instanceof Mob _entity && _entity.getTarget() != null) {
            LivingEntity target = _entity.getTarget();

            // Rotation
            double lookDx = target.getX() - x;
            double lookDz = target.getZ() - z;
            float yaw = (float)(Math.atan2(lookDz, lookDx) * (180 / Math.PI)) - 90;
            entity.setYRot(yaw);
            entity.yRotO = yaw;
            _entity.setYHeadRot(yaw);

            // Abstand halten
            double dist = Math.sqrt(entity.distanceToSqr(target));
            double offsetX = (entity.getRandom().nextDouble() - 0.5) * 0.4;
            double offsetZ = (entity.getRandom().nextDouble() - 0.5) * 0.4;

            if (attackRange < 0) {
                _entity.getNavigation().moveTo(target.getX() + offsetX, target.getY(), target.getZ() + offsetZ, 1.0);
            } else if (dist > attackRange + 0.5) {
                _entity.getNavigation().moveTo(target.getX() + offsetX, target.getY(), target.getZ() + offsetZ, 1.0);
            } else if (dist < attackRange - 0.5) {
                double angle = Math.atan2(z - target.getZ(), x - target.getX());
                _entity.getNavigation().moveTo(
                        x + Math.cos(angle) * (attackRange + 1),
                        y,
                        z + Math.sin(angle) * (attackRange + 1),
                        1.0);
            } else {
                _entity.getNavigation().stop();
            }
        }
    }

    private static double getCeilingY(Entity entity, Level level) {
        BlockPos pos = new BlockPos((int)entity.getX(), (int)entity.getY() + 1, (int)entity.getZ());
        while (level.isEmptyBlock(pos) && pos.getY() < level.getMaxY()) {
            pos = pos.above();
        }
        return pos.getY();
    }
}