package net.mcreator.royalcraft.item;

import net.mcreator.royalcraft.procedures.SummonMinionProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class MinionItemItem extends Item {
	public MinionItemItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SummonMinionProcedure.execute(world, entity);
		return ar;
	}
}