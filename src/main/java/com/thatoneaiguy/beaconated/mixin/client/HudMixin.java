package com.thatoneaiguy.beaconated.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.BeaconatedClient;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class HudMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow private int scaledWidth;

    @Inject(method = "drawHeart", at = @At("TAIL"))
    private void beaconated$drawCustomHeart(DrawContext context, InGameHud.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart, CallbackInfo ci) {
        if (!blinking && type == InGameHud.HeartType.NORMAL && MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player && (player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART))) {
            if (player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                context.drawTexture(BeaconatedClient.PENDANT, x, y, halfHeart ? 9 : 0, v, 9, 9);
            }
        }
    }

    @ModifyArg(method = "renderStatusEffectOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    protected Identifier beaconated$renderStatusEffectOverlay(Identifier texture, @Local StatusEffect statusEffect) {
        if ( statusEffect == BeaconatedEffects.SOLIDIFIED_HEART) {
            return BeaconatedClient.PENDANT;
        }
        return texture;
    }
}
