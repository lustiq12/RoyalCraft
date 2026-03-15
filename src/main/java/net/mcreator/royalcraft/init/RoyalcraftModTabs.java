/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.royalcraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mcreator.royalcraft.RoyalcraftMod;

@EventBusSubscriber
public class RoyalcraftModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RoyalcraftMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(RoyalcraftModItems.RED_TOWER_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.RED_GOLEM_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_GOLEM_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.RED_MINION_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_MINION_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_TOWER_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.RED_BARBARIAN_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_BARBARIAN_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_KING_TOWER_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.RED_KING_TOWER_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_GIANT_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.RED_GIANT_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.BLUE_MINI_PEKKA_SPAWN_EGG.get());
			tabData.accept(RoyalcraftModItems.RED_MINI_PEKKA_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(RoyalcraftModItems.GOLEM_ITEM.get());
			tabData.accept(RoyalcraftModItems.MINION_ITEM.get());
			tabData.accept(RoyalcraftModItems.BARBARIAN_ITEM.get());
			tabData.accept(RoyalcraftModItems.GIANT_ITEM.get());
			tabData.accept(RoyalcraftModItems.MINI_PEKKA_ITEM.get());
		}
	}
}