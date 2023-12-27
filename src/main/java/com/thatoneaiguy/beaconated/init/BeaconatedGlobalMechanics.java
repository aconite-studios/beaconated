package com.thatoneaiguy.beaconated.init;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
public abstract class BeaconatedGlobalMechanics {

    private static final Random random = new Random();

    public static void ParticleSystem(World world, BlockPos pos) {

        if (world != null && world.isClient) {
            for (int b = 0; b < 6; b++) {
                double xOffset = random.nextGaussian() * 16;
                double yOffset = random.nextGaussian() * 16;
                double zOffset = random.nextGaussian() * 16;
                world.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5 + xOffset, pos.getY() + 1.0 + yOffset, pos.getZ() + 0.5 + zOffset, 0.0, 0.0, 0.0);
            }
        }
    }
}

