package net.mcreator.royalcraft.procedures;

import net.mcreator.royalcraft.entity.BlueMinionEntity;
import net.mcreator.royalcraft.entity.RedMinionEntity;
import net.mcreator.royalcraft.init.RoyalcraftModEntities;
import net.mcreator.royalcraft.network.RoyalcraftModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelAccessor;

public class SummonMinionProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity SpawnedEntity = null;
		if (entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir >= 3) {
			if ((entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team).equals("Red") && (world.getBlockState(
					new BlockPos(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
					.is(BlockTags.create(ResourceLocation.parse("royalcraft:red")))) {
				{
					RoyalcraftModVariables.PlayerVariables _vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
					_vars.Elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir - 3;
					_vars.markSyncDirty();
				}
				UseCardProcedure.execute(entity);
				SpawnedEntity = world instanceof ServerLevel _level8
						? RoyalcraftModEntities.RED_MINION.get().spawn(_level8, new BlockPos(
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY() + 1,
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()),
								EntitySpawnReason.MOB_SUMMONED)
						: null;
				if (SpawnedEntity instanceof RedMinionEntity _datEntSetS)
					_datEntSetS.getEntityData().set(RedMinionEntity.DATA_Team, entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team);
			} else if ((entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team).equals("Blue") && (world.getBlockState(
					new BlockPos(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
					.is(BlockTags.create(ResourceLocation.parse("royalcraft:blue")))) {
				{
					RoyalcraftModVariables.PlayerVariables _vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
					_vars.Elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir - 3;
					_vars.markSyncDirty();
				}
				UseCardProcedure.execute(entity);

				for (int index1 = 0; index1 < 3; index1++) {
					SpawnedEntity = world instanceof ServerLevel _level18
							? RoyalcraftModEntities.BLUE_MINION.get().spawn(_level18, new BlockPos(
									entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
									entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY() + 1,
									entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()),
							EntitySpawnReason.MOB_SUMMONED)
							: null;
					if (SpawnedEntity instanceof BlueMinionEntity _datEntSetS)
						_datEntSetS.getEntityData().set(BlueMinionEntity.DATA_Team, entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team);
				}
			}
		}
	}
}