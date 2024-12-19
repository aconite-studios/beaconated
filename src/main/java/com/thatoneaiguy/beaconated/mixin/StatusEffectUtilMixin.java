package com.thatoneaiguy.beaconated.mixin;

import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public class StatusEffectUtilMixin {
    @Inject(method = "getDurationText", at = @At("HEAD"), cancellable = true)
    private static void beaconated$changeTime(@NotNull StatusEffectInstance effect, float multiplier, CallbackInfoReturnable<Text> cir) {
        if (effect.getEffectType() == BeaconatedEffects.SOLIDIFIED_HEART && effect.isInfinite()) {
            cir.setReturnValue(Text.translatable("effect.beaconated.solidified_heart.pendant").formatted(Formatting.YELLOW));
        }
    }
}