package net.mcreator.royalcraft.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.royalcraft.entity.HealthBarEntity;

public class HealthBarOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof HealthBarEntity _datEntSetS)
			_datEntSetS.getEntityData().set(HealthBarEntity.DATA_Offset, (new java.text.DecimalFormat("##.#######").format(Mth.nextDouble(RandomSource.create(), 0, 0.05))));
	}
}