package net.mcreator.royalcraft.item;

import net.mcreator.royalcraft.procedures.SummonTroopProcedure;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

public class MiniPEKKAitemItem extends Item {
	public MiniPEKKAitemItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SummonTroopProcedure.execute(world, entity, SummonTroopProcedure.Troop.MINI_PEKKA, 1, 4);
		return ar;
	}
}