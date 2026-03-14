package net.mcreator.royalcraft.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

public class TowerSpawnedProcedure {

    // Für Princess Towers (nur Rotation)
    public static void execute(Entity entity, float yRot) {
        if (entity == null) return;
        entity.setYRot(yRot);
        entity.setXRot(0);
        entity.setYBodyRot(yRot);
        entity.setYHeadRot(yRot);
        entity.yRotO = yRot;
        entity.xRotO = 0;
        if (entity instanceof LivingEntity le) {
            le.yBodyRotO = yRot;
            le.yHeadRotO = yRot;
        }
    }

    // Für King Towers (Rotation + Teleport)
    public static void execute(Entity entity, float yRot, double x, double y, double z) {
        execute(entity, yRot);
        entity.teleportTo(x - 0.5, y, z - 0.5);
        if (entity instanceof ServerPlayer sp)
            sp.connection.teleport(x - 0.5, y, z - 0.5, entity.getYRot(), entity.getXRot());
    }
}