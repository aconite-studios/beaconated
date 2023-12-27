package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.entity.Chnompner;
import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VibraniumChomperBlockEntity extends BlockEntity {
    static int ticks = 30;
    public VibraniumChomperBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.CHOMPER, pos, state);
    }


    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumChomperBlockEntity entity) {

    int b = 1;
    if (world.getBlockState(blockPos.down(b)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
        world.getPlayers().forEach(player -> {
            if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                    BeaconatedGlobalMechanics.ParticleSystem(world, blockPos);

                    ticks = ticks - 1;

                    if(ticks < 0) {
                        ticks = 30;
                        Chnompner chnompner = new Chnompner(world, player.getX(), player.getY(), player.getZ(), player.getYaw());
                        chnompner.setPosition(player.getX(), player.getY(), player.getZ());
                        world.spawnEntity(chnompner);
                        }
                    }
                }
            });
        }
    }
}
