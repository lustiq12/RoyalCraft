package net.mcreator.royalcraft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;

public class UsefirevallprocedurProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir >= 3) {
			{
				RoyalcraftModVariables.PlayerVariables _vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
				_vars.Elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir - 3;
				_vars.markSyncDirty();
			}
			{
				Entity _shootFrom = entity;
				Level projectileLevel = _shootFrom.level();
				if (!projectileLevel.isClientSide()) {
					Projectile _entityToSpawn = initProjectileProperties(new LargeFireball(EntityType.FIREBALL, projectileLevel), entity, new Vec3((entity.getLookAngle().x), (entity.getLookAngle().y), (entity.getLookAngle().z)));
					_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
					_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			}
		}
	}

	private static Projectile initProjectileProperties(Projectile entityToSpawn, Entity shooter, Vec3 acceleration) {
		entityToSpawn.setOwner(shooter);
		if (!Vec3.ZERO.equals(acceleration)) {
			entityToSpawn.setDeltaMovement(acceleration);
			entityToSpawn.hasImpulse = true;
		}
		return entityToSpawn;
	}
}