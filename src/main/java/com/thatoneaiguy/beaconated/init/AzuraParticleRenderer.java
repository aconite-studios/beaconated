package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

import java.awt.*;
import java.util.Random;


/* Credits
* Code made by Azura Nishio
* Thanks to her
*/
public class AzuraParticleRenderer {

    // Chain renderer
    public static void renderChain(ClientWorld world, Vec3d chainStartPos, Vec3d endPos, LodestoneWorldParticleType Particle1, LodestoneWorldParticleType Particle2) {
        double Xpos = chainStartPos.getX() + 0.5;
        double Ypos = chainStartPos.getY() + 0.5;
        double Zpos = chainStartPos.getZ() + 0.5;

        Vec3d targetPos = new Vec3d(endPos.x, endPos.y, endPos.z);
        double targetX = targetPos.getX() + 0.5;
        double targetY = targetPos.getY() + 0.5;
        double targetZ = targetPos.getZ() + 0.5;

        double deltaX = targetX - Xpos;
        double deltaY = targetY - Ypos;
        double deltaZ = targetZ - Zpos;

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        if (distance == 0) {
            return;
        }

        int ChainSteps = (int) ((distance / 0.3) - 4) * 20;
        Vec3d ChainStepSize = new Vec3d(deltaX / ChainSteps, deltaY / ChainSteps, deltaZ / ChainSteps);

        for (int i = 0; i < ChainSteps; i++) {
            double n = i * 0.025;
            Vec3d localOffset = new Vec3d(
                    0.25,
                    Math.abs(Math.round(n) - n) + Math.round(n) * 0.6,
                    Math.abs(n + 0.25 - Math.round(0.25 + n))
            );

            Vec3d yAxle = ChainStepSize.normalize();
            Vec3d xAxle = (yAxle.x == 0 && yAxle.z == 0) ? new Vec3d(1, 0, 0) : new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
            Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

            Vec3d globalParticlePos = targetPos
                    .subtract(yAxle.multiply(localOffset.y))
                    .add(xAxle.multiply(localOffset.getX()))
                    .add(zAxle.multiply(localOffset.getZ()));

            // Spawn for all players in the world
            Random random = new Random();

            Color color = new Color(250,235,215,128);
            WorldParticleBuilder.create(Particle1)
                    .enableForcedSpawn()
                    .setLightLevel(LightmapTextureManager.MAX_LIGHT_COORDINATE)
                    .setScaleData(GenericParticleData.create(0.06f).build())
                    .setTransparencyData(
                            GenericParticleData.create(1f, 1f, 0f)
                                    .setEasing(Easing.SINE_OUT)
                                    .build()
                    )
                    .setColorData(ColorParticleData.create(color, color).build())
                    .enableNoClip()
                    .setLifetime(10)
                    .setSpinData(SpinParticleData.create(0f,1f).build())
                    .setMotion( random.nextFloat(-0.01f,0.01f), 0.01f + random.nextFloat() * .01f, random.nextFloat(-0.01f,0.01f))
                    .setRenderType(LodestoneWorldParticleRenderType.PARTICLE_SHEET_TRANSLUCENT)
                    .spawn(world, globalParticlePos.getX(), globalParticlePos.getY(), globalParticlePos.getZ());



            globalParticlePos = targetPos
                    .subtract(yAxle.multiply(localOffset.y + 0.3))
                    .add(xAxle.multiply(localOffset.getZ()))
                    .add(zAxle.multiply(localOffset.getX()));

            // Spawn for all players in the world
            color = new Color(250,235,215,64);
            WorldParticleBuilder.create(Particle2)
                    .enableForcedSpawn()
                    .setLightLevel(LightmapTextureManager.MAX_LIGHT_COORDINATE)
                    .setScaleData(GenericParticleData.create(0.06f).build())
                    .setTransparencyData(
                            GenericParticleData.create(1f, 1f, 0f)
                                    .setEasing(Easing.SINE_OUT)
                                    .build()
                    )
                    .setColorData(ColorParticleData.create(color, color).build())
                    .enableNoClip()
                    .setLifetime(10)
                    .setSpinData(SpinParticleData.create(0f,1f).build())
                    .setMotion( random.nextFloat(-0.01f,0.01f), 0.01f + random.nextFloat() * .01f, random.nextFloat(-0.01f,0.01f))
                    .setRenderType(LodestoneWorldParticleRenderType.PARTICLE_SHEET_TRANSLUCENT)
                    .spawn(world, globalParticlePos.getX(), globalParticlePos.getY(), globalParticlePos.getZ());
        }
    }

    // Line renderer
    public static void renderLine(ClientWorld world, Vec3d chainStartPos, Vec3d endPos, LodestoneWorldParticleType Particle1, double space) {
        double Xpos = chainStartPos.getX() + 0.5;
        double Ypos = chainStartPos.getY() + 0.5;
        double Zpos = chainStartPos.getZ() + 0.5;

        Vec3d targetPos = new Vec3d(endPos.x, endPos.y, endPos.z);
        double targetX = targetPos.getX() + 0.5;
        double targetY = targetPos.getY() + 0.5;
        double targetZ = targetPos.getZ() + 0.5;

        double deltaX = targetX - Xpos;
        double deltaY = targetY - Ypos;
        double deltaZ = targetZ - Zpos;

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        if (distance == 0) {
            return;
        }

        int lineSteps = (int) Math.ceil(distance) * 20;
        Vec3d lineStepSize = new Vec3d(deltaX / lineSteps, deltaY / lineSteps, deltaZ / lineSteps);

        for (int i = 0; i < lineSteps; i++) {
            double n = i * space;
            Vec3d localOffset = new Vec3d(0, n, 0);

            Vec3d yAxle = lineStepSize.normalize();
            Vec3d xAxle = (yAxle.x == 0 && yAxle.z == 0) ? new Vec3d(1, 0, 0) : new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
            Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

            Vec3d globalParticlePos = targetPos
                    .subtract(yAxle.multiply(localOffset.y))
                    .add(xAxle.multiply(localOffset.getX()))
                    .add(zAxle.multiply(localOffset.getZ()));

            // Spawn for all players in the world

                //world.spawnParticles(player, Particle1, true, globalParticlePos.x, globalParticlePos.y, globalParticlePos.z, 1, 0, 0, 0, 0);
                Random random = new Random();

                Color color = new Color(250,235,215,128);
                WorldParticleBuilder.create(Particle1)
                        .enableForcedSpawn()
                        .setLightLevel(LightmapTextureManager.MAX_LIGHT_COORDINATE)
                        .setScaleData(GenericParticleData.create(0.06f).build())
                        .setTransparencyData(
                                GenericParticleData.create(1f, 1f, 0f)
                                        .setEasing(Easing.SINE_OUT)
                                        .build()
                        )
                        .setColorData(ColorParticleData.create(color, color).build())
                        .enableNoClip()
                        .setLifetime(10)
                        .setSpinData(SpinParticleData.create(0f,1f).build())
                        .setMotion( random.nextFloat(-0.01f,0.01f), 0.01f + random.nextFloat() * .01f, random.nextFloat(-0.01f,0.01f))
                        .setRenderType(LodestoneWorldParticleRenderType.PARTICLE_SHEET_TRANSLUCENT)
                        .spawn(world, globalParticlePos.getX(), globalParticlePos.getY(), globalParticlePos.getZ());
        }
    }

    // Circle renderer
    public static void renderCircle(ServerWorld world, Vec3d center, Vec3d direction, double radius, DefaultParticleType Particle1) {
        int ChainSteps = 120;

        for (int i = 0; i < ChainSteps; i++) {
            Vec3d localOffset = new Vec3d(
                    radius * Math.sin(Math.toRadians(3 * i)),
                    0,
                    radius * Math.cos(Math.toRadians(3 * i))
            );

            Vec3d yAxle = direction.normalize();
            Vec3d xAxle = (yAxle.x == 0 && yAxle.z == 0) ? new Vec3d(1, 0, 0) : new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
            Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

            Vec3d globalParticlePos = center
                    .subtract(yAxle.multiply(localOffset.y))
                    .add(xAxle.multiply(localOffset.getX()))
                    .add(zAxle.multiply(localOffset.getZ()));

            // Spawn for all players in the world
            for (ServerPlayerEntity player : world.getPlayers()) {
                world.spawnParticles(player, Particle1, true, globalParticlePos.x, globalParticlePos.y, globalParticlePos.z, 1, 0, 0, 0, 0);
            }
        }
    }

    public static Vec3d rotateAroundAxis(Vec3d vector, Vec3d axis, double angleDegrees) {
        Vec3d normalizedAxis = axis.normalize();

        double cosTheta = Math.cos(Math.toRadians(angleDegrees));
        double sinTheta = Math.sin(Math.toRadians(angleDegrees));

        Vec3d parallelComponent = normalizedAxis.multiply(vector.dotProduct(normalizedAxis));
        Vec3d perpendicularComponent = vector.subtract(parallelComponent);
        Vec3d crossProductComponent = normalizedAxis.crossProduct(vector);

        return parallelComponent
                .add(perpendicularComponent.multiply(cosTheta))
                .add(crossProductComponent.multiply(sinTheta));
    }
}