package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VibraniumChomperBlockEntity extends BlockEntity {
    public VibraniumChomperBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.CHOMPER, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumChomperBlockEntity entity) {
        int i = 1;
        if (world.getBlockState(blockPos.down(i)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {

            world.getPlayers().forEach(player -> {
                if (player.getBlockPos().isWithinDistance(blockPos, 64)) {
                    if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                        BeaconatedGlobalMechanics.spawnFangs(player, blockPos, world);
                    }
                }
            });
        }
    }
}
