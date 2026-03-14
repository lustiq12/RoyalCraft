package net.mcreator.royalcraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.royalcraft.entity.HealthBarEntity;

import java.util.UUID;

public class HealthBarOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null) return;
		if (!(entity instanceof HealthBarEntity bar)) return;

		Entity source = world instanceof ServerLevel level
				? getEntityFromUUID(level, bar.getEntityData().get(HealthBarEntity.DATA_ConnectedEntity))
				: null;

		if (source == null) {
			if (!entity.level().isClientSide()) entity.discard();
			return;
		}

		// Sync health
		bar.getEntityData().set(HealthBarEntity.DATA_Health,    (int)(source instanceof LivingEntity l ? l.getHealth()    : -1));
		bar.getEntityData().set(HealthBarEntity.DATA_MaxHealth, (int)(source instanceof LivingEntity l ? l.getMaxHealth() : -1));

		// Face fixed direction
		entity.setYRot(90);
		entity.setXRot(0);
		entity.setYBodyRot(90);
		entity.setYHeadRot(90);
		entity.yRotO = 90;
		entity.xRotO = 0;
		if (entity instanceof LivingEntity le) {
			le.yBodyRotO = 90;
			le.yHeadRotO = 90;
		}

		// Teleport above source entity, height depends on entity size tag
		double offset = parseDouble(bar.getEntityData().get(HealthBarEntity.DATA_Offset));
		double tx = source.getX() + offset;
		double tz = source.getZ();
		double ty;

		if (source.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("royalcraft:fiveblocks")))) {
			ty = source.getY() + 5;
		} else if (source.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("royalcraft:fourblocks")))) {
			ty = source.getY() + 4;
		} else {
			ty = source.getY() + 2.3; // threeblocks default
		}

		entity.teleportTo(tx, ty, tz);
		if (entity instanceof ServerPlayer sp)
			sp.connection.teleport(tx, ty, tz, entity.getYRot(), entity.getXRot());
	}

	private static Entity getEntityFromUUID(ServerLevel level, String uuid) {
		try { return level.getEntity(UUID.fromString(uuid)); }
		catch (IllegalArgumentException e) { return null; }
	}

	private static double parseDouble(String s) {
		try { return Double.parseDouble(s.trim()); }
		catch (Exception e) { return 0; }
	}
}