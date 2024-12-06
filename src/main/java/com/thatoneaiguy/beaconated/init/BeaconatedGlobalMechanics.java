package com.thatoneaiguy.beaconated.init;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
public abstract class BeaconatedGlobalMechanics {

    private static final Random random = new Random();

    public static void ParticleSystem(World world, BlockPos pos, int size, int count) {

        if (world != null && world.isClient) {
            for (int b = 0; b < count; b++) {
                double xOffset = random.nextGaussian() * size;
                double yOffset = random.nextGaussian() * size;
                double zOffset = random.nextGaussian() * size;
                world.addParticle(ParticleTypes.END_ROD, pos.getX() + xOffset, pos.getY() + 1.0 + yOffset, pos.getZ() + zOffset, 0.0, 0.0, 0.0);
            }
        }
    }
}

