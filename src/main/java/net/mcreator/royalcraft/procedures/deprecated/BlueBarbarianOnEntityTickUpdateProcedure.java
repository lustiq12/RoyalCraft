package net.mcreator.royalcraft.procedures.deprecated;

import net.mcreator.royalcraft.procedures.InvulnerableProcedure;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;

import java.util.Comparator;

public class BlueBarbarianOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity Targetentity = null;
		Level lvl = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		InvulnerableProcedure.execute(world, entity);
		if (RoyalcraftModVariables.WorldVariables.get(world).WorldTimer == 1) {
			Targetentity = lvl.getEntitiesOfClass(LivingEntity.class, new AABB(x - 40, y - 40, z - 40, x + 40, y + 40, z + 40), e -> e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.tryParse("royalcraft:red")))).stream()
					.min(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).orElse(null);
			if (!(Targetentity == null)) {
				if (entity instanceof Mob _entity && Targetentity instanceof LivingEntity _ent)
					_entity.setTarget(_ent);
				if (entity instanceof Mob _entity) {
					double offsetX = (entity.getRandom().nextDouble() - 0.5) * 2;
					double offsetZ = (entity.getRandom().nextDouble() - 0.5) * 2;
					_entity.getNavigation().moveTo(Targetentity.getX() + offsetX, Targetentity.getY(), Targetentity.getZ() + offsetZ, 1);
				}
			}
		}
	}
}