package net.mcreator.royalcraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import java.util.UUID;

public class InvulnerableProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity placeholder = null;
		if (entity instanceof LivingEntity living) {
			// Alle Invulnerability-Werte zurücksetzen
			living.hurtTime = 0;
			living.invulnerableTime = 0;
		}
		placeholder = world instanceof ServerLevel _level1 ? getEntityFromUUID(_level1, (entity.getStringUUID())) : null;
	}

	private static Entity getEntityFromUUID(ServerLevel level, String uuid) {
		try {
			return level.getEntity(UUID.fromString(uuid));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}