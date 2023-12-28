package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import ladysnake.pickyourpoison.common.PickYourPoison;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VibraniumPhiltreBlockEntity extends BlockEntity {
    static int ticks = 400;

    static int effect = (int) (Math.random() * 10);

    public VibraniumPhiltreBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.PHILTRE, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumPhiltreBlockEntity entity) {
       // System.out.println(effect);

        int b = 1;
        if (world.getBlockState(blockPos.down(b)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
            world.getPlayers().forEach(player -> {
                if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                    if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos);

                        ticks = ticks - 1;

                        if(ticks < 0) {
                            ticks = 400;

                            if (effect < 1) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance slownessEffect = new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1, false, false);
                                player.addStatusEffect(slownessEffect);
                            } else if (effect < 2) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance slowfallEffect = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0, false, false);
                                player.addStatusEffect(slowfallEffect);
                            } else if (effect < 3) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(StatusEffects.POISON, 200, 0, false, false);
                                player.addStatusEffect(poisonEffect);
                            } else if (effect < 4) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance numbnessEffect = new StatusEffectInstance(PickYourPoison.NUMBNESS, 200, 0, false, false);
                                player.addStatusEffect(numbnessEffect);
                            } else if (effect < 5) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance vulnerabilityEffect = new StatusEffectInstance(PickYourPoison.VULNERABILITY, 200, 0, false, false);
                                player.addStatusEffect(vulnerabilityEffect);
                            } else if (effect < 6) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance slownessEffect = new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2, false, false);
                                player.addStatusEffect(slownessEffect);
                            } else if (effect < 7) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance slowfallEffect = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0, false, false);
                                player.addStatusEffect(slowfallEffect);
                            } else if (effect < 8) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(StatusEffects.POISON, 200, 0, false, false);
                                player.addStatusEffect(poisonEffect);
                            } else if (effect < 9) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance numbnessEffect = new StatusEffectInstance(PickYourPoison.NUMBNESS, 200, 0, false, false);
                                player.addStatusEffect(numbnessEffect);
                            } else if (effect < 0) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance vulnerabilityEffect = new StatusEffectInstance(PickYourPoison.VULNERABILITY, 200, 0, false, false);
                                player.addStatusEffect(vulnerabilityEffect);
                            }
                        }
                    }
                    if(player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos);

                        ticks = ticks - 1;

                        if(ticks < 0) {
                            ticks = 400;

                            if (effect < 1) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance regenEffect = new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false);
                                player.addStatusEffect(regenEffect);
                            } else if (effect < 2) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance resistanceEffect = new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false);
                                player.addStatusEffect(resistanceEffect);
                            } else if (effect < 3) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance speedEffect = new StatusEffectInstance(StatusEffects.SPEED, 200, 1, false, false);
                                player.addStatusEffect(speedEffect);
                            } else if (effect < 4) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance hasteEffect = new StatusEffectInstance(StatusEffects.HASTE, 200, 1, false, false);
                                player.addStatusEffect(hasteEffect);
                            } else if (effect < 5) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance fireResistanceEffect = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false);
                                player.addStatusEffect(fireResistanceEffect);
                            } else if (effect < 6) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance regenEffect = new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false);
                                player.addStatusEffect(regenEffect);
                            } else if (effect < 7) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance resistanceEffect = new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false);
                                player.addStatusEffect(resistanceEffect);
                            } else if (effect < 8) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance speedEffect = new StatusEffectInstance(StatusEffects.SPEED, 200, 1, false, false);
                                player.addStatusEffect(speedEffect);
                            } else if (effect < 9) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance hasteEffect = new StatusEffectInstance(StatusEffects.HASTE, 200, 1, false, false);
                                player.addStatusEffect(hasteEffect);
                            } else if (effect < 0) {
                                effect = (int) (Math.random() * 10);
                                StatusEffectInstance fireResistanceEffect = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false);
                                player.addStatusEffect(fireResistanceEffect);
                            }
                        }
                    }
                }
            });
        }
    }
}