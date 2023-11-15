package com.thatoneaiguy.beaconated.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class HudMixin extends DrawableHelper {

    @Unique
    private static final Identifier PENDANT_HEARTS = new Identifier("beaconated", "textures/gui/solidified_hearts.png");

    @Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
    private void pickyourpoison$drawCustomHeart(MatrixStack matrices, InGameHud.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart, CallbackInfo ci) {
        if (!blinking && type == InGameHud.HeartType.NORMAL && MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player && (player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART))) {
            if (player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
                RenderSystem.setShaderTexture(0, PENDANT_HEARTS);
            }
            drawTexture(matrices, x, y, halfHeart ? 9 : 0, v, 9, 9);
            RenderSystem.setShaderTexture(0, GUI_ICONS_TEXTURE);
            ci.cancel();
        }
    }
}
