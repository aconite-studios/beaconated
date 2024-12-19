package com.thatoneaiguy.beaconated.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import com.thatoneaiguy.beaconated.init.BeaconatedItems;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({HeadFeatureRenderer.class})
public class HeadFeatureRendererMixin<T extends LivingEntity> {

    public HeadFeatureRendererMixin() {
    }


    @WrapOperation(
            method = {"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"
            )}
    )
    private ItemStack beaconated$addFeature(LivingEntity entity, EquipmentSlot slot, Operation<ItemStack> original) {
        return BeaconatedGlobalMechanics.isWearingTrinkets(entity, BeaconatedItems.VIBRANIUM_PENDANT) ?
                BeaconatedGlobalMechanics.getWornTrinkets(entity,BeaconatedItems.VIBRANIUM_PENDANT) :
                original.call(entity, slot);
    }

}