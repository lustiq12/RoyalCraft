package net.mcreator.royalcraft.procedures;

import net.minecraft.world.entity.Entity;
import net.mcreator.royalcraft.entity.HealthBarEntity;

/**
 * Returns the health percentage of the connected entity, rounded down to the nearest 10.
 * Example: 85% HP → returns 80
 * Returns -1 if entity is null or MaxHealth is 0.
 */
public class HealthBarPercentProcedure {
    public static int execute(Entity entity) {
        if (entity == null) return -1;
        if (!(entity instanceof HealthBarEntity bar)) return -1;
        int maxHealth = bar.getEntityData().get(HealthBarEntity.DATA_MaxHealth);
        if (maxHealth == 0) return -1;
        int health = bar.getEntityData().get(HealthBarEntity.DATA_Health);
        float percent = ((float) health / (float) maxHealth) * 100f;
        return (int) (Math.floor(percent / 10.0) * 10);
    }
}