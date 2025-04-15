package com.thatoneaiguy.beaconated.items;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;

public interface CustomArmPoseItem {
    @Nullable
    BipedEntityModel.ArmPose getArmPose(ItemStack stack, AbstractClientPlayerEntity player, Hand hand);
}
