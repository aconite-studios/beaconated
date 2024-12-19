package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.init.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class VibraniumPhiltreBlockEntity extends BlockEntity {
    static int ticks = 400;

    static Random random = new Random();
    static int effect = random.nextInt(0,5);

    public VibraniumPhiltreBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.PHILTRE, pos, state);
    }

    public static void setEffect(StatusEffect instance, PlayerEntity player) {
        effect = random.nextInt(0,5);
        StatusEffectInstance effect = new StatusEffectInstance(instance, 200, 0, false, false);
        player.addStatusEffect(effect);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumPhiltreBlockEntity entity) {
        int b = 1;
        if (world.getBlockState(blockPos.down(b)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
            BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,0.75,1);
            world.getPlayers().forEach(player -> {
                if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                    if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,16,6);
                        if (world instanceof ClientWorld server) {
                            AzuraParticleRenderer.renderLine(server, blockPos.toCenterPos(),player.getEyePos(), Beaconated.SPARK, 0.1);
                        }

                        ticks--;

                        if(ticks < 0) {
                            ticks = 400;

                            switch (effect) {
                                case 0, 1 -> setEffect(StatusEffects.SLOWNESS,player);
                                case 2, 3 -> setEffect(StatusEffects.SLOW_FALLING,player);
                                case 4, 5 -> setEffect(StatusEffects.POISON,player);
                            }
                        }
                    }
                    if(player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,16,6);
                        ticks--;
                        if(ticks < 0) {
                            ticks = 400;
                            switch (effect) {
                                case 0, 5 -> setEffect(StatusEffects.REGENERATION,player);
                                case 1 -> setEffect(StatusEffects.RESISTANCE,player);
                                case 2 -> setEffect(StatusEffects.SPEED,player);
                                case 3 -> setEffect(StatusEffects.HASTE,player);
                                case 4 -> setEffect(StatusEffects.FIRE_RESISTANCE,player);
                            }
                        }
                    }
                }
            });
        }
    }
}