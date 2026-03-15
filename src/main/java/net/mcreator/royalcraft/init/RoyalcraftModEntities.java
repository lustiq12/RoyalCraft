/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.royalcraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.royalcraft.entity.*;
import net.mcreator.royalcraft.RoyalcraftMod;

@EventBusSubscriber
public class RoyalcraftModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, RoyalcraftMod.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<DarkSpitEntity>> DARK_SPIT = register("dark_spit",
			EntityType.Builder.<DarkSpitEntity>of(DarkSpitEntity::new, MobCategory.MISC)
					.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.25f, 0.25f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedTowerEntity>> RED_TOWER = register("red_tower",
			EntityType.Builder.<RedTowerEntity>of(RedTowerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(2.5f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedGolemEntity>> RED_GOLEM = register("red_golem",
			EntityType.Builder.<RedGolemEntity>of(RedGolemEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.2f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueGolemEntity>> BLUE_GOLEM = register("blue_golem",
			EntityType.Builder.<BlueGolemEntity>of(BlueGolemEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.6f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedMinionEntity>> RED_MINION = register("red_minion",
			EntityType.Builder.<RedMinionEntity>of(RedMinionEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueMinionEntity>> BLUE_MINION = register("blue_minion",
			EntityType.Builder.<BlueMinionEntity>of(BlueMinionEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueTowerEntity>> BLUE_TOWER = register("blue_tower",
			EntityType.Builder.<BlueTowerEntity>of(BlueTowerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(2.5f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedBarbarianEntity>> RED_BARBARIAN = register("red_barbarian",
			EntityType.Builder.<RedBarbarianEntity>of(RedBarbarianEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).ridingOffset(-0.6f).sized(0.2f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueBarbarianEntity>> BLUE_BARBARIAN = register("blue_barbarian",
			EntityType.Builder.<BlueBarbarianEntity>of(BlueBarbarianEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).ridingOffset(-0.6f).sized(0.2f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueKingTowerEntity>> BLUE_KING_TOWER = register("blue_king_tower",
			EntityType.Builder.<BlueKingTowerEntity>of(BlueKingTowerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(2.8f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedKingTowerEntity>> RED_KING_TOWER = register("red_king_tower",
			EntityType.Builder.<RedKingTowerEntity>of(RedKingTowerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(2.8f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueGiantEntity>> BLUE_GIANT = register("blue_giant",
			EntityType.Builder.<BlueGiantEntity>of(BlueGiantEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.4f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedGiantEntity>> RED_GIANT = register("red_giant",
			EntityType.Builder.<RedGiantEntity>of(RedGiantEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.4f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueMiniPEKKAEntity>> BLUE_MINI_PEKKA = register("blue_mini_pekka",
			EntityType.Builder.<BlueMiniPEKKAEntity>of(BlueMiniPEKKAEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.2f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<RedMiniPEKKAEntity>> RED_MINI_PEKKA = register("red_mini_pekka",
			EntityType.Builder.<RedMiniPEKKAEntity>of(RedMiniPEKKAEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.2f, 1.8f));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(RoyalcraftMod.MODID, registryname))));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		RedTowerEntity.init(event);
		RedGolemEntity.init(event);
		RedMinionEntity.init(event);
		BlueMinionEntity.init(event);
		BlueGolemEntity.init(event);
		BlueTowerEntity.init(event);
		RedBarbarianEntity.init(event);
		BlueBarbarianEntity.init(event);
		BlueKingTowerEntity.init(event);
		RedKingTowerEntity.init(event);
		BlueGiantEntity.init(event);
		RedGiantEntity.init(event);
		BlueMiniPEKKAEntity.init(event);
		RedMiniPEKKAEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(RED_TOWER.get(), RedTowerEntity.createAttributes().build());
		event.put(RED_GOLEM.get(), RedGolemEntity.createAttributes().build());
		event.put(BLUE_GOLEM.get(), BlueGolemEntity.createAttributes().build());
		event.put(RED_MINION.get(), RedMinionEntity.createAttributes().build());
		event.put(BLUE_MINION.get(), BlueMinionEntity.createAttributes().build());
		event.put(BLUE_TOWER.get(), BlueTowerEntity.createAttributes().build());
		event.put(RED_BARBARIAN.get(), RedBarbarianEntity.createAttributes().build());
		event.put(BLUE_BARBARIAN.get(), BlueBarbarianEntity.createAttributes().build());
		event.put(BLUE_KING_TOWER.get(), BlueKingTowerEntity.createAttributes().build());
		event.put(RED_KING_TOWER.get(), RedKingTowerEntity.createAttributes().build());
		event.put(BLUE_GIANT.get(), BlueGiantEntity.createAttributes().build());
		event.put(RED_GIANT.get(), RedGiantEntity.createAttributes().build());
		event.put(BLUE_MINI_PEKKA.get(), BlueMiniPEKKAEntity.createAttributes().build());
		event.put(RED_MINI_PEKKA.get(), RedMiniPEKKAEntity.createAttributes().build());
	}
}
