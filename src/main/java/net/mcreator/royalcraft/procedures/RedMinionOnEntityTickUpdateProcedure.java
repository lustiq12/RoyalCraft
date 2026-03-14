package net.mcreator.royalcraft.procedures;

import net.mcreator.royalcraft.entity.RedMinionEntity;
import net.mcreator.royalcraft.network.RoyalcraftModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;

import java.util.Comparator;

public class RedMinionOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null) return;
		Level lvl = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		InvulnerableProcedure.execute(world, entity);

		if (RoyalcraftModVariables.WorldVariables.get(world).WorldTimer == 1) {
			if (entity instanceof RedMinionEntity _Minion) {
				_Minion.cachedTarget = lvl.getEntitiesOfClass(LivingEntity.class,
								new AABB(x - 40, y - 40, z - 40, x + 40, y + 40, z + 40),
								e -> e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.tryParse("royalcraft:blue"))))
						.stream()
						.min(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z)))
						.orElse(null);

				if (entity instanceof Mob _entity)
					_entity.setTarget(_Minion.cachedTarget);
			}
		}

		if (entity instanceof RedMinionEntity _Minion && _Minion.cachedTarget != null) {
			if (entity instanceof Mob _entity) {
				double dist = Math.sqrt(entity.distanceToSqr(_Minion.cachedTarget));
				if (dist < 4.0) {
					double angle = Math.atan2(z - _Minion.cachedTarget.getZ(), x - _Minion.cachedTarget.getX());
					double fleeX = x + Math.cos(angle) * 6;
					double fleeZ = z + Math.sin(angle) * 6;
					_entity.getNavigation().moveTo(fleeX, entity.getY(), fleeZ, 1.2);
				}
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