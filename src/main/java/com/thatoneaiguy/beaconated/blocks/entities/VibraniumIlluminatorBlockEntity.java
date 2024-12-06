package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VibraniumIlluminatorBlockEntity extends BlockEntity{
    public VibraniumIlluminatorBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.VIBRANIUM_ILLUMINATOR, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumIlluminatorBlockEntity entity) {
        int i = 1;
        if (world.getBlockState(blockPos.down(i)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
            BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,2,1);
            world.getPlayers().forEach(player -> {
                if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                    if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                            StatusEffectInstance glowingEffect = new StatusEffectInstance(StatusEffects.GLOWING, 100, 0, true, false);
                            player.addStatusEffect(glowingEffect);
                        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,16,6);
                    }
                }
            });
        }
    }
}

