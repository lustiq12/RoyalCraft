package net.mcreator.royalcraft.procedures;

import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.royalcraft.init.RoyalcraftModEntities;
import net.mcreator.royalcraft.entity.HealthBarEntity;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EntitySpawnsProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinLevelEvent event) {
		execute(event, event.getLevel(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("royalcraft:healthbar")))) {
			if ((world instanceof ServerLevel _level1 ? RoyalcraftModEntities.HEALTH_BAR.get().spawn(_level1, BlockPos.containing(x, y, z), EntitySpawnReason.MOB_SUMMONED) : null) instanceof HealthBarEntity _datEntSetS)
				_datEntSetS.getEntityData().set(HealthBarEntity.DATA_ConnectedEntity, (entity.getStringUUID()));
		}
	}
}