package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.entity.Chnompner;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.entity.EvokerFangsEntityRenderer;
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.Random;
import java.util.UUID;

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

    public static void spawnFangs(LivingEntity player, BlockPos blockPos, World world) {
        double d = Math.min(player.getY(), player.getY());
        double e = Math.max(player.getY(), player.getY()) + 1.0;
        float f = (float) MathHelper.atan2(player.getZ() - blockPos.getX(), player.getX() - blockPos.getX());
        int i;

            float g;
            for(i = 0; i < 5; ++i) {
                g = f + (float)i * 3.1415927F * 0.4F;
                createFangs(blockPos.getX() + (double)MathHelper.cos(g) * 1.5, blockPos.getZ() + (double)MathHelper.sin(g) * 1.5, d, e, g, 0, world);
            }

            for(i = 0; i < 8; ++i) {
                g = f + (float)i * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
                createFangs(blockPos.getX() + (double)MathHelper.cos(g) * 2.5, blockPos.getZ() + (double)MathHelper.sin(g) * 2.5, d, e, g, 3, world);
            }
        }

    protected static void createFangs(double x, double z, double maxY, double y, float yaw, int warmup, World world) {
        BlockPos blockPos = new BlockPos(x, y, z);
        boolean bl = false;
        double d = 0.0;

        do {
            BlockPos blockPos2 = blockPos.down();
            BlockState blockState = world.getBlockState(blockPos2);
            if (blockState.isSideSolidFullSquare(world, blockPos2, Direction.UP)) {
                if (!world.isAir(blockPos)) {
                    BlockState blockState2 = world.getBlockState(blockPos);
                    VoxelShape voxelShape = blockState2.getCollisionShape(world, blockPos);
                    if (!voxelShape.isEmpty()) {
                        d = voxelShape.getMax(Direction.Axis.Y);
                    }
                }

                bl = true;
                break;
            }

            blockPos = blockPos.down();
        } while(blockPos.getY() >= MathHelper.floor(maxY) - 1);

        if (bl) {
            world.spawnEntity(new Chnompner(world, x, (double)blockPos.getY() + d, z, yaw, warmup));
        }
    }
}

