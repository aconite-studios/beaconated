package com.thatoneaiguy.beaconated.mixin.client;

import com.thatoneaiguy.beaconated.BeaconatedClient;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(AbstractInventoryScreen.class)
public abstract class AbstractInventoryScreenMixin<T extends ScreenHandler> extends HandledScreen<T> {

    public AbstractInventoryScreenMixin(T container, PlayerInventory playerInventory, Text name) {
        super(container, playerInventory, name);
    }

    @Inject(method = "drawStatusEffectBackgrounds", at = @At(value = "HEAD"), cancellable = true)
    private void beaconated$changeBackBackground(DrawContext context, int x, int height, Iterable<StatusEffectInstance> statusEffects, boolean wide, CallbackInfo ci) {
            int i = this.y;
            for(Iterator<StatusEffectInstance> statusEffectIterator = statusEffects.iterator(); statusEffectIterator.hasNext(); i += height) {
                StatusEffectInstance statusEffectInstance = statusEffectIterator.next();
                if (statusEffectInstance.getEffectType() == BeaconatedEffects.SOLIDIFIED_HEART) {
                    if (wide) {
                        context.drawTexture(BeaconatedClient.PENDANT, x, i, 0, 166, 120, 32);
                    } else {
                        context.drawTexture(BeaconatedClient.PENDANT, x, i, 0, 198, 32, 32);
                    }
                    ci.cancel();
                }
            }
    }
}