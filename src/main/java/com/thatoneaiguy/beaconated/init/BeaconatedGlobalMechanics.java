package com.thatoneaiguy.beaconated.init;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Random;
public abstract class BeaconatedGlobalMechanics {

    private static final Random random = new Random();

    public static void ParticleSystem(World world, BlockPos pos, double size, int count) {
        if (world != null && world.isClient) {
            for (int b = 0; b < count; b++) {
                double xOffset = random.nextGaussian() * size;
                double yOffset = random.nextGaussian() * size;
                double zOffset = random.nextGaussian() * size;
                world.addParticle(ParticleTypes.END_ROD, pos.getX() + .5f + xOffset, pos.getY() + 1.0 + yOffset, pos.getZ() + .5f + zOffset, 0.0, -0.25, 0.0);
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
}

