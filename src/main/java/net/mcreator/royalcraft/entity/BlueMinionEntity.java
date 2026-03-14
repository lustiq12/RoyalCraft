package net.mcreator.royalcraft.entity;

import net.mcreator.royalcraft.init.RoyalcraftModEntities;
import net.mcreator.royalcraft.procedures.BlueMinionOnEntityTickUpdateProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class BlueMinionEntity extends Monster implements RangedAttackMob {
	public static final EntityDataAccessor<String> DATA_Team = SynchedEntityData.defineId(BlueMinionEntity.class, EntityDataSerializers.STRING);
	public LivingEntity cachedTarget = null;

	public BlueMinionEntity(EntityType<BlueMinionEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
		this.setNoGravity(true);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_Team, "");
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FleeFromTargetGoal(this, 4.0f));
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0, 20, 5.0f));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float power) {
		if (this.level().isClientSide) return;
		if (this.distanceToSqr(target) > 36.0) return;

		DarkSpitEntity projectile = new DarkSpitEntity(RoyalcraftModEntities.DARK_SPIT.get(), this, this.level());
		projectile.setPos(this.getX(), this.getEyeY(), this.getZ());

		double dx = target.getX() - this.getX();
		double dy = target.getY(0.5) - this.getEyeY() + 0.2;
		double dz = target.getZ() - this.getZ();

		projectile.shoot(dx, dy, dz, 1.5f, 1.0f);
		this.level().addFreshEntity(projectile);
	}

	@Override
	public void baseTick() {
		super.baseTick();
		BlueMinionOnEntityTickUpdateProcedure.execute(this.level(), this);
		this.setNoGravity(true);

		// Höhe korrigieren
		double targetY = getCeilingY() - 9.0;
		if (Math.abs(getY() - targetY) > 2.0) {
			this.setPos(getX(), targetY, getZ());
		} else {
			this.setDeltaMovement(getDeltaMovement().x, (targetY - getY()) * 0.3, getDeltaMovement().z);
		}

		// Rotation zum Target
		if (cachedTarget != null) {
			double dx = cachedTarget.getX() - getX();
			double dz = cachedTarget.getZ() - getZ();
			float yaw = (float)(Math.atan2(dz, dx) * (180 / Math.PI)) - 90;
			this.setYRot(yaw);
			this.yRotO = yaw;
		}
	}

	// Verhindert dass Navigation den Kopf dreht
	@Override
	public void setYHeadRot(float yaw) {
		if (cachedTarget != null) {
			double dx = cachedTarget.getX() - getX();
			double dz = cachedTarget.getZ() - getZ();
			float targetYaw = (float)(Math.atan2(dz, dx) * (180 / Math.PI)) - 90;
			super.setYHeadRot(targetYaw);
		} else {
			super.setYHeadRot(yaw);
		}
	}

	private double getCeilingY() {
		BlockPos pos = new BlockPos((int)getX(), (int)getY() + 1, (int)getZ());
		while (level().isEmptyBlock(pos) && pos.getY() < level().getMaxY()) {
			pos = pos.above();
		}
		return pos.getY();
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new FlyingPathNavigation(this, level);
	}

	@Override
	public boolean onGround() { return false; }

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) { return false; }

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.death"));
	}

	@Override
	public void addAdditionalSaveData(ValueOutput valueOutput) {
		super.addAdditionalSaveData(valueOutput);
		valueOutput.putString("DataTeam", this.entityData.get(DATA_Team));
	}

	@Override
	public void readAdditionalSaveData(ValueInput valueInput) {
		super.readAdditionalSaveData(valueInput);
		this.entityData.set(DATA_Team, valueInput.getStringOr("DataTeam", ""));
	}

	public static void init(RegisterSpawnPlacementsEvent event) {}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.15);
		builder = builder.add(Attributes.MAX_HEALTH, 9);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 4);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
		builder = builder.add(Attributes.FLYING_SPEED, 0.4);
		return builder;
	}

	private static class FleeFromTargetGoal extends Goal {
		private final Mob mob;
		private final float minDist;

		public FleeFromTargetGoal(Mob mob, float minDist) {
			this.mob = mob;
			this.minDist = minDist;
		}

		@Override
		public boolean canUse() {
			LivingEntity target = mob.getTarget();
			return target != null && mob.distanceToSqr(target) < minDist * minDist;
		}

		@Override
		public void tick() {
			LivingEntity target = mob.getTarget();
			if (target == null) return;
			double angle = Math.atan2(mob.getZ() - target.getZ(), mob.getX() - target.getX());
			double fleeX = mob.getX() + Math.cos(angle) * 6;
			double fleeZ = mob.getZ() + Math.sin(angle) * 6;
			mob.getNavigation().moveTo(fleeX, mob.getY(), fleeZ, 1.5);
		}
	}
}