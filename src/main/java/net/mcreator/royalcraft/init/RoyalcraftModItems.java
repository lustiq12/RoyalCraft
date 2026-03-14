/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.royalcraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item;

import net.mcreator.royalcraft.item.MiniPEKKAitemItem;
import net.mcreator.royalcraft.item.GolemItemItem;
import net.mcreator.royalcraft.item.MinionItemItem;
import net.mcreator.royalcraft.item.GiantItemItem;
import net.mcreator.royalcraft.item.BarbarianItemItem;
import net.mcreator.royalcraft.RoyalcraftMod;

import java.util.function.Function;

public class RoyalcraftModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(RoyalcraftMod.MODID);
	public static final DeferredItem<Item> RED_TOWER_SPAWN_EGG;
	public static final DeferredItem<Item> RED_GOLEM_SPAWN_EGG;
	public static final DeferredItem<Item> BLUE_GOLEM_SPAWN_EGG;
	public static final DeferredItem<Item> GOLEM_ITEM;
	public static final DeferredItem<Item> BLUE_MINION_SPAWN_EGG;
	public static final DeferredItem<Item> RED_MINION_SPAWN_EGG;
	public static final DeferredItem<Item> MINION_ITEM;
	public static final DeferredItem<Item> BLUE_TOWER_SPAWN_EGG;
	public static final DeferredItem<Item> BARBARIAN_ITEM;
	public static final DeferredItem<Item> RED_BARBARIAN_SPAWN_EGG;
	public static final DeferredItem<Item> BLUE_BARBARIAN_SPAWN_EGG;
	public static final DeferredItem<Item> BLUE_KING_TOWER_SPAWN_EGG;
	public static final DeferredItem<Item> RED_KING_TOWER_SPAWN_EGG;
	public static final DeferredItem<Item> HEALTH_BAR_SPAWN_EGG;
	public static final DeferredItem<Item> BLUE_GIANT_SPAWN_EGG;
	public static final DeferredItem<Item> RED_GIANT_SPAWN_EGG;
	public static final DeferredItem<Item> GIANT_ITEM;
	public static final DeferredItem<Item> BLUE_MINI_PEKKA_SPAWN_EGG;
	public static final DeferredItem<Item> RED_MINI_PEKKA_SPAWN_EGG;
	public static final DeferredItem<Item> MINI_PEKKA_ITEM;
	static {
		RED_TOWER_SPAWN_EGG = register("red_tower_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_TOWER.get(), properties));
		RED_GOLEM_SPAWN_EGG = register("red_golem_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_GOLEM.get(), properties));
		BLUE_GOLEM_SPAWN_EGG = register("blue_golem_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_GOLEM.get(), properties));
		GOLEM_ITEM = register("golem_item", GolemItemItem::new);
		RED_MINION_SPAWN_EGG = register("red_minion_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_MINION.get(), properties));
		BLUE_MINION_SPAWN_EGG = register("blue_minion_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_MINION.get(), properties));
		MINION_ITEM = register("minion_item", MinionItemItem::new);
		BLUE_TOWER_SPAWN_EGG = register("blue_tower_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_TOWER.get(), properties));
		BARBARIAN_ITEM = register("barbarian_item", BarbarianItemItem::new);
		RED_BARBARIAN_SPAWN_EGG = register("red_barbarian_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_BARBARIAN.get(), properties));
		BLUE_BARBARIAN_SPAWN_EGG = register("blue_barbarian_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_BARBARIAN.get(), properties));
		BLUE_KING_TOWER_SPAWN_EGG = register("blue_king_tower_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_KING_TOWER.get(), properties));
		RED_KING_TOWER_SPAWN_EGG = register("red_king_tower_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_KING_TOWER.get(), properties));
		HEALTH_BAR_SPAWN_EGG = register("health_bar_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.HEALTH_BAR.get(), properties));
		BLUE_GIANT_SPAWN_EGG = register("blue_giant_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_GIANT.get(), properties));
		RED_GIANT_SPAWN_EGG = register("red_giant_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_GIANT.get(), properties));
		GIANT_ITEM = register("giant_item", GiantItemItem::new);
		BLUE_MINI_PEKKA_SPAWN_EGG = register("blue_mini_pekka_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.BLUE_MINI_PEKKA.get(), properties));
		RED_MINI_PEKKA_SPAWN_EGG = register("red_mini_pekka_spawn_egg", properties -> new SpawnEggItem(RoyalcraftModEntities.RED_MINI_PEKKA.get(), properties));
		MINI_PEKKA_ITEM = register("mini_pekka_item", MiniPEKKAitemItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}
}