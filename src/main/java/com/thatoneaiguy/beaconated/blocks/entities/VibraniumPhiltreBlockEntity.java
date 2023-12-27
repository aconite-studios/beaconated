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

    static double effectMath = Math.random();
    static double effect = effectMath * 10;
    static int effectIntConversion = (int)effect;
    static int effectDoubleConversion = effectIntConversion;
    static double finalEffect = effectIntConversion / 10;


    public VibraniumPhiltreBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.PHILTRE, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumPhiltreBlockEntity entity) {
        System.out.println(finalEffect);

        int b = 1;
        if (world.getBlockState(blockPos.down(b)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
            world.getPlayers().forEach(player -> {
                if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                    if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos);

                        ticks = ticks - 1;

                        if(ticks < 0) {
                            ticks = 400;

                            if(finalEffect < 0.1) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance slownessEffect = new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2, true, false);
                                player.addStatusEffect(slownessEffect);
                            }
                            if(finalEffect == 0.2) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance slowfallEffect = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0, true, false);
                                player.addStatusEffect(slowfallEffect);
                            }
                            if(finalEffect == 0.3) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(StatusEffects.POISON, 200, 0, true, false);
                                player.addStatusEffect(poisonEffect);
                            }
                            if(finalEffect == 0.4) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(PickYourPoison.NUMBNESS, 200, 0, true, false);
                                player.addStatusEffect(poisonEffect);
                            }
                            if(finalEffect == 0.5) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(PickYourPoison.VULNERABILITY, 200, 0, true, false);
                                player.addStatusEffect(poisonEffect);
                            }if(finalEffect == 0.6) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance slownessEffect = new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2, true, false);
                                player.addStatusEffect(slownessEffect);
                            }
                            if(finalEffect == 0.7) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance slowfallEffect = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0, true, false);
                                player.addStatusEffect(slowfallEffect);
                            }
                            if(finalEffect == 0.8) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(StatusEffects.POISON, 200, 0, true, false);
                                player.addStatusEffect(poisonEffect);
                            }
                            if(finalEffect == 0.9) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(PickYourPoison.NUMBNESS, 200, 0, true, false);
                                player.addStatusEffect(poisonEffect);
                            }
                            if(finalEffect == 0) {
                                double effectMath = Math.random();
                                double effect = effectMath * 10;
                                int effectIntConversion = (int)effect;
                                effectDoubleConversion = effectIntConversion;
                                finalEffect = effectIntConversion / 10;
                                StatusEffectInstance poisonEffect = new StatusEffectInstance(PickYourPoison.VULNERABILITY, 200, 0, true, false);
                                player.addStatusEffect(poisonEffect);
                            }
                        }
                    }
                }
            });
        }
    }
}