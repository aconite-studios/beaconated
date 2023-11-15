package com.thatoneaiguy.beaconated.items;

import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VibraniumPendant extends Item {
    public VibraniumPendant(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            for (var slotItem : player.getInventory().main) {
                if (slotItem.getItem().getName().equals(stack.getItem().getName())) {
                    player.addStatusEffect(new StatusEffectInstance(BeaconatedEffects.SOLIDIFIED_HEART, 100, 0, false, false));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
