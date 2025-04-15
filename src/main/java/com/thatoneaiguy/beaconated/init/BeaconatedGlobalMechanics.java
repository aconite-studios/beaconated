package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

import java.awt.*;
import java.util.Optional;
import java.util.Random;
public abstract class BeaconatedGlobalMechanics {

    private static final Random random = new Random();

    public static void ParticleSystem(World world, BlockPos pos, double size, int count) {
        if (world instanceof ClientWorld client) {
            for (int b = 0; b < count; b++) {
                double xOffset = random.nextGaussian() * size;
                double yOffset = random.nextGaussian() * size;
                double zOffset = random.nextGaussian() * size;
                createLodestoneParticle(Beaconated.SPARK, new Vec3d(xOffset,yOffset,zOffset),client,80,0.12f);
            }
        }
    }

    public static boolean isWearingTrinkets(LivingEntity livingEntity, Item item) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
        return component.filter(trinketComponent -> trinketComponent.isEquipped(item)).isPresent();
    }

    public static ItemStack getWornTrinkets(LivingEntity livingEntity, Item item) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
        if (component.isPresent()) {
            for (Pair<SlotReference, ItemStack> slotReferenceItemStackPair : component.get().getAllEquipped()) {
                if (slotReferenceItemStackPair.getRight().getItem() == item) {
                    return slotReferenceItemStackPair.getRight();
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static void renderLine(ClientWorld world, Vec3d start, Vec3d end, LodestoneWorldParticleType Particle1) {
        double distance = start.distanceTo(end);
        Vec3d direction = end.subtract(start).normalize();
        int particleCount = (int) (distance * 10);

        for (int i = 0; i <= particleCount; i++) {
            double progress = (double) i / particleCount;
            Vec3d particlePos = start.add(direction.multiply(distance * progress));
            createLodestoneParticle(Particle1,particlePos,world);
        }
    }

    public static void createLodestoneParticle(LodestoneWorldParticleType Particle1,Vec3d globalParticlePos, ClientWorld world) {
        createLodestoneParticle(Particle1, globalParticlePos, world, 10, .06f);
    }

    public static void createLodestoneParticle(LodestoneWorldParticleType Particle1,Vec3d globalParticlePos, ClientWorld world, int lifetime, float scale) {
        java.util.Random random = new java.util.Random();

        Color color = new Color(250,235,175,64);
        Color color2 = new Color(250,235,25,64);
        WorldParticleBuilder.create(Particle1)
                .enableForcedSpawn()
                .setGravityStrength(0.25f)
                .setLightLevel(LightmapTextureManager.MAX_LIGHT_COORDINATE)
                .setScaleData(GenericParticleData.create(scale).build())
                .setTransparencyData(
                        GenericParticleData.create(.5f,0f)
                                .setEasing(Easing.CIRC_IN)
                                .build()
                )
                .setColorData(
                        ColorParticleData.create(color, color2)
                                .setEasing(Easing.CIRC_IN)
                                .build()
                )
                .enableNoClip()
                .enableCull()
                .setLifetime(lifetime)
                .setSpinData(SpinParticleData.create(0f,2f).build())
                .setMotion(random.nextFloat(-.01f,.01f), random.nextFloat(-0.5f,-0.05f), random.nextFloat(-.01f,.01f))
                .setRenderType(LodestoneWorldParticleRenderType.TRANSPARENT)
                .spawn(world, globalParticlePos.getX() + random.nextFloat(-.01f,.01f), globalParticlePos.getY() + random.nextFloat(-.01f,.01f), globalParticlePos.getZ() + random.nextFloat(-.01f,.01f));
    }
}

