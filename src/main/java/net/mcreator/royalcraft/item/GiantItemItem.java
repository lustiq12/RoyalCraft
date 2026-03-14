package net.mcreator.royalcraft.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.royalcraft.procedures.SummonGiantProcedure;

public class GiantItemItem extends Item {
	public GiantItemItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SummonGiantProcedure.execute(world, entity);
		return ar;
	}
}