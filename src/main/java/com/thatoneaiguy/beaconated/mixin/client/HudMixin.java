package com.thatoneaiguy.beaconated.mixin.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thatoneaiguy.beaconated.BeaconatedClient;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

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

    @Inject(method = "renderStatusEffectOverlay", at = @At("TAIL"), cancellable = true)
    protected void beaconated$renderStatusEffectOverlay(DrawContext context, CallbackInfo ci) {
        assert this.client.player != null;
        Collection<StatusEffectInstance> collection = this.client.player.getStatusEffects();
        if (collection.contains(new StatusEffectInstance(BeaconatedEffects.SOLIDIFIED_HEART,-1,0,true,true))) {
            label40: {
                collection = this.client.player.getStatusEffects();
                if (!collection.isEmpty()) {
                    Screen var4 = this.client.currentScreen;
                    if (!(var4 instanceof AbstractInventoryScreen<?> abstractInventoryScreen)) {
                        break label40;
                    }
                    if (!abstractInventoryScreen.hideStatusEffectHud()) {
                        break label40;
                    }
                }
                return;
            }

            RenderSystem.enableBlend();
            int i = 0;
            int j = 0;
            StatusEffectSpriteManager statusEffectSpriteManager = this.client.getStatusEffectSpriteManager();
            List<Runnable> list = Lists.newArrayListWithExpectedSize(collection.size());

            for (StatusEffectInstance o : Ordering.natural().reverse().sortedCopy(collection)) {
                StatusEffect statusEffect = o.getEffectType();
                if (o.shouldShowIcon()) {
                    int k = this.scaledWidth;
                    int l = 1;
                    if (this.client.isDemo()) {
                        l += 15;
                    }
                    if (statusEffect.isBeneficial()) {
                        ++i;
                        k -= 25 * i;
                    } else {
                        ++j;
                        k -= 25 * j;
                        l += 26;
                    }
                    float f;
                    Identifier background = HandledScreen.BACKGROUND_TEXTURE;
                    if (o.getEffectType() == BeaconatedEffects.SOLIDIFIED_HEART) background = BeaconatedClient.PENDANT;
                    if (o.isAmbient()) {
                        f = 1.0F;
                        context.drawTexture(background, k, l, 165, 166, 24, 24);
                    } else {
                        context.drawTexture(background, k, l, 141, 166, 24, 24);
                        if (o.isDurationBelow(200)) {
                            int m = o.getDuration();
                            int n = 10 - m / 20;
                            f = MathHelper.clamp((float) m / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + MathHelper.cos((float) m * 3.1415927F / 5.0F) * MathHelper.clamp((float) n / 10.0F * 0.25F, 0.0F, 0.25F);
                        } else {
                            f = 1.0F;
                        }
                    }
                    Sprite sprite = statusEffectSpriteManager.getSprite(statusEffect);
                    int finalK = k;
                    int finalL = l;
                    list.add(() -> {
                        context.setShaderColor(1.0F, 1.0F, 1.0F, f);
                        context.drawSprite(finalK + 3, finalL + 3, 0, 18, 18, sprite);
                        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    });
                }
            }

            list.forEach(Runnable::run);
            ci.cancel();
        }
    }
}
