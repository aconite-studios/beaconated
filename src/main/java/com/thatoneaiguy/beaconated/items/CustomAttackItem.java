package com.thatoneaiguy.beaconated.items;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Iterator;

public interface CustomAttackItem{

    default void attack(Entity target, PlayerEntity player) {

        ItemStack attackerMainStack = player.getMainHandStack();

        if (target.isAttackable()) {
            if (!target.handleAttack(player)) {

                float genericAttackDamage = getDamage(player, attackerMainStack);

                float itemAttackDamage;
                if (target instanceof LivingEntity living) {
                    itemAttackDamage = EnchantmentHelper.getAttackDamage(attackerMainStack, living.getGroup());
                } else {
                    itemAttackDamage = EnchantmentHelper.getAttackDamage(attackerMainStack, EntityGroup.DEFAULT);
                }

                float coolDown = player.getAttackCooldownProgress(0.5F);
                genericAttackDamage *= 0.2F + coolDown * coolDown * 0.8F;
                itemAttackDamage *= coolDown;
                player.resetLastAttackedTicks();
                if (genericAttackDamage > 0.0F || itemAttackDamage > 0.0F) {
                    boolean isItemCharged = coolDown > 0.9F;
                    boolean canKnockBack = false;
                    int knockBack = 0;
                    knockBack += EnchantmentHelper.getKnockback(player);
                    if (player.isSprinting() && isItemCharged) {
                        playSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK);
                        ++knockBack;
                        canKnockBack = true;
                    }

                    boolean canCritical =
                            isItemCharged &&
                                    player.fallDistance > 0.0F &&
                                    !player.isOnGround() &&
                                    !player.isClimbing() &&
                                    !player.isTouchingWater() &&
                                    !player.hasStatusEffect(StatusEffects.BLINDNESS) &&
                                    !player.hasVehicle() &&
                                    target instanceof LivingEntity
                                    && !player.isSprinting();

                    if (canCritical) {
                        genericAttackDamage *= 1.5F;
                    }

                    genericAttackDamage += itemAttackDamage;

                    boolean canSweep = false;

                    boolean originalSweep = originalSweep(canCritical,canKnockBack,isItemCharged,player);

                    if (originalSweep) {
                        if (attackerMainStack.getItem() instanceof SwordItem || shouldSweep(attackerMainStack)) {
                            canSweep = true;
                        }
                    }

                    float targetHealth = 0.0F;
                    boolean shouldTargetBurn = false;
                    int fireAspect = EnchantmentHelper.getFireAspect(player);
                    if (target instanceof LivingEntity living) {
                        targetHealth = living.getHealth();
                        if (fireAspect > 0 && !target.isOnFire()) {
                            shouldTargetBurn = true;
                            target.setOnFireFor(1);
                        }
                    }

                    Vec3d targetVelocity = target.getVelocity();
                    boolean canBeDamaged = damageTarget(target, getDamageSources(player), genericAttackDamage);
                    if (canBeDamaged) {
                        if (knockBack > 0) {
                            if (target instanceof LivingEntity living) {
                                living.takeKnockback((float)knockBack/2, MathHelper.sin(player.getYaw() * 0.017453292F), -MathHelper.cos(player.getYaw() * 0.017453292F));
                                postKnockBack(attackerMainStack,living,player);
                            } else {
                                target.addVelocity(-MathHelper.sin(player.getYaw() * 0.017453292F) * (float)knockBack/2, 0.1, MathHelper.cos(player.getYaw() * 0.017453292F) * (float)knockBack/2);
                            }

                            player.setVelocity(player.getVelocity().multiply(0.6, 1.0, 0.6));
                            player.setSprinting(false);
                        }

                        if (canSweep) {
                            sweep(player,target,genericAttackDamage);
                        }

                        if (target instanceof ServerPlayerEntity serverPlayer && target.velocityModified) {
                            serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                            target.velocityModified = false;
                            target.setVelocity(targetVelocity);
                        }

                        if (canCritical) {
                            playSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT);
                            player.addCritParticles(target);
                        }

                        if (!canCritical && !canSweep) {
                            if (isItemCharged) {
                                playSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG);
                            } else {
                                playSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK);
                            }
                        }

                        if (itemAttackDamage > 0.0F) {
                            player.addEnchantedHitParticles(target);
                        }

                        player.onAttacking(target);
                        if (target instanceof LivingEntity living) {
                            EnchantmentHelper.onUserDamaged(living, player);
                        }

                        EnchantmentHelper.onTargetDamaged(player, target);
                        Entity entity = target;
                        if (target instanceof EnderDragonPart part) {
                            entity = part.owner;
                        }

                        if (!player.getWorld().isClient && !attackerMainStack.isEmpty() && entity instanceof LivingEntity living) {
                            attackerMainStack.postHit(living, player);
                            if (attackerMainStack.isEmpty()) {
                                player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        if (target instanceof LivingEntity living) {
                            float m = targetHealth - (living.getHealth());
                            player.increaseStat(Stats.DAMAGE_DEALT, Math.round(m * 10.0F));
                            if (fireAspect > 0) {
                                target.setOnFireFor(fireAspect * 4);
                            }

                            if (player.getWorld() instanceof ServerWorld server && m > 2.0F) {
                                int n = (int)((double)m * 0.5);
                                server.spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY(0.5), target.getZ(), n, 0.1, 0.0, 0.1, 0.2);
                            }
                        }

                        player.addExhaustion(0.1F);
                    } else {
                        playSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE);
                        if (shouldTargetBurn) {
                            target.extinguish();
                        }
                    }
                }

            }
        }
    }

    default boolean postKnockBack(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    default void playSound(PlayerEntity player, SoundEvent sound) {
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), sound, player.getSoundCategory(), 1.0F, 1.0F);
    }

    default void sweep(PlayerEntity player, Entity target, float attackDamage) {
        float attackSweepDamage = getAttackSweepDamage(player, attackDamage);
        Iterator<LivingEntity> sweptEntities = getSweptEntities(player, target);

        sweep:
        while(true) {
            LivingEntity livingEntity;
            do {
                do {
                    do {
                        do {
                            if (!sweptEntities.hasNext()) {
                                playSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP);
                                spawnSweepAttackParticles(player);
                                break sweep;
                            }

                            livingEntity = sweptEntities.next();
                        } while(livingEntity == player);
                    } while(livingEntity == target);
                } while(player.isTeammate(livingEntity));
            } while(livingEntity instanceof ArmorStandEntity && ((ArmorStandEntity)livingEntity).isMarker());

            if (player.squaredDistanceTo(livingEntity) < 9.0) {
                applyKnockBack(livingEntity,player,target);
                livingEntity.damage( getDamageSources(player), attackSweepDamage);
            }
        }
    }

    default float getDamage(PlayerEntity player,ItemStack stack) {
        return (float)player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    default void applyKnockBack(LivingEntity livingEntity, PlayerEntity player, Entity target) {
        livingEntity.takeKnockback(0.4000000059604645, MathHelper.sin(player.getYaw() * 0.017453292F), -MathHelper.cos(player.getYaw() * 0.017453292F));
    }

    default void spawnSweepAttackParticles(PlayerEntity player) {
        player.spawnSweepAttackParticles();
    }

    default DamageSource getDamageSources(PlayerEntity player) {
        return player.getDamageSources().playerAttack(player);
    }

    default boolean shouldSweep(ItemStack stack) {
        return false;
    }

    default boolean originalSweep(boolean canCritical,boolean canKnockBack, boolean isItemCharged, PlayerEntity player) {
        return isItemCharged && !canKnockBack && !canCritical && player.isOnGround() && player.horizontalSpeed-player.prevHorizontalSpeed < (double)player.getMovementSpeed();
    }

    default float getAttackSweepDamage(PlayerEntity player, float attackDamage) {
        return 1f * EnchantmentHelper.getSweepingMultiplier(player) * attackDamage;
    }

    default Iterator<LivingEntity> getSweptEntities(PlayerEntity player, Entity target) {
        return player.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0)).iterator();
    }

    default boolean damageTarget(Entity target, DamageSource source, float amount) {
        return target.damage(source, amount);
    }
}
