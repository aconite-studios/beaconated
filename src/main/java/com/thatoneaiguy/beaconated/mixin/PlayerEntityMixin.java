package com.thatoneaiguy.beaconated.mixin;

import com.thatoneaiguy.beaconated.items.CustomAttackItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin{

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void beaconated$customAttackItem(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getMainHandStack().getItem() instanceof CustomAttackItem item) {
            item.attack(target, player);
            ci.cancel();
        }
    }
}
