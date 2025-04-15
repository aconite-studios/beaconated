package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.init.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VibraniumIlluminatorBlockEntity extends BlockEntity{
    public VibraniumIlluminatorBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.VIBRANIUM_ILLUMINATOR, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumIlluminatorBlockEntity entity) {
        int i = 1;
        if (world.getBlockState(blockPos.down(i)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
            BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,0.75,1);
            world.getPlayers().forEach(player -> {
                if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                    if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                            StatusEffectInstance glowingEffect = new StatusEffectInstance(StatusEffects.GLOWING, 100, 0, true, false);
                            player.addStatusEffect(glowingEffect);
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,16,6);
                        if (world instanceof ClientWorld server) {
                            BeaconatedGlobalMechanics.renderLine(server, blockPos.toCenterPos(),player.getEyePos().add(0,-0.5,0), Beaconated.SPARK);
                        }
                    }
                }
            });
        }
    }
}

