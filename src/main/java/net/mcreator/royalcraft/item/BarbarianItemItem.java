package net.mcreator.royalcraft.item;

import net.mcreator.royalcraft.procedures.SummonTroopProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BarbarianItemItem extends Item {
	public BarbarianItemItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SummonTroopProcedure.execute(world, entity, SummonTroopProcedure.Troop.BARBARIAN, 5, 5);
		return ar;
	}
}