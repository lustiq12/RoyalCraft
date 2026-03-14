package net.mcreator.royalcraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.royalcraft.network.RoyalcraftModVariables;
import net.mcreator.royalcraft.init.RoyalcraftModEntities;
import net.mcreator.royalcraft.entity.RedMiniPEKKAEntity;
import net.mcreator.royalcraft.entity.BlueMiniPEKKAEntity;

public class SummonMiniPEKKAProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity SpawnedEntity = null;
		if (entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir >= 4) {
			if ((entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team).equals("Red") && (world.getBlockState(
					new BlockPos(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
					.is(BlockTags.create(ResourceLocation.parse("royalcraft:red")))) {
				{
					RoyalcraftModVariables.PlayerVariables _vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
					_vars.Elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir - 4;
					_vars.markSyncDirty();
				}
				UseCardProcedure.execute(entity);
				SpawnedEntity = world instanceof ServerLevel _level8
						? RoyalcraftModEntities.RED_MINI_PEKKA.get().spawn(_level8, new BlockPos(
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY() + 1,
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()),
								EntitySpawnReason.MOB_SUMMONED)
						: null;
				if (SpawnedEntity instanceof RedMiniPEKKAEntity _datEntSetS)
					_datEntSetS.getEntityData().set(RedMiniPEKKAEntity.DATA_Team, entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team);
			} else if ((entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team).equals("Blue") && (world.getBlockState(
					new BlockPos(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(),
							entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())))
					.is(BlockTags.create(ResourceLocation.parse("royalcraft:blue")))) {
				{
					RoyalcraftModVariables.PlayerVariables _vars = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES);
					_vars.Elixir = entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Elixir - 4;
					_vars.markSyncDirty();
				}
				UseCardProcedure.execute(entity);
				SpawnedEntity = world instanceof ServerLevel _level18
						? RoyalcraftModEntities.BLUE_MINI_PEKKA.get().spawn(_level18, new BlockPos(
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(),
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY() + 1,
								entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(50)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()),
								EntitySpawnReason.MOB_SUMMONED)
						: null;
				if (SpawnedEntity instanceof BlueMiniPEKKAEntity _datEntSetS)
					_datEntSetS.getEntityData().set(BlueMiniPEKKAEntity.DATA_Team, entity.getData(RoyalcraftModVariables.PLAYER_VARIABLES).Team);
			}
		}
	}
}