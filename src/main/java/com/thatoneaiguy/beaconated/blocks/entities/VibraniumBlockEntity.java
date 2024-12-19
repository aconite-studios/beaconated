package com.thatoneaiguy.beaconated.blocks.entities;

import com.thatoneaiguy.beaconated.entity.Chnompner;
import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class VibraniumBlockEntity extends BlockEntity {
    public VibraniumBlockEntity(BlockPos pos, BlockState state) {
        super(BeaconatedBlockEntities.BLOCK, pos, state);
    }

    static Random random = new Random();
    static int tick = random.nextInt(1,20*120);


    public static void tick(World world, BlockPos blockPos, BlockState blockState, VibraniumBlockEntity entity) {

    int b = 1;
    if (world.getBlockState(blockPos.down(b)).isOf(BeaconatedBlocks.VIBRANIUM_BULB)) {
        BeaconatedGlobalMechanics.ParticleSystem(world, blockPos,0.75,1);
        tick--;
        Vec3d centerPos = blockPos.toCenterPos();
        if (tick <= 0) {
            if (!world.getBlockState(blockPos.up(b)).isOf(Blocks.LIGHTNING_ROD)) {
                    LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                    lightning.setPosition(centerPos);
                    world.spawnEntity(lightning);
                    tick = random.nextInt(1, 20*60);
            }else {
                world.getPlayers().forEach(player -> {
                    if (player.getBlockPos().isWithinDistance(blockPos, 16)) {
                        if (!player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                            lightning.setPosition(player.getX(), player.getY(), player.getZ());
                            world.spawnEntity(lightning);
                        }
                    }
                });
                tick = random.nextInt(1, 20*120);
            }
        }

    }
    }
}
