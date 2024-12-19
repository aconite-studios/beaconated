package com.thatoneaiguy.beaconated.statuseffect;

import com.thatoneaiguy.beaconated.init.BeaconatedGlobalMechanics;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class SolidifiedHeartStatusEffect extends StatusEffect {
    public SolidifiedHeartStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean mialib$shouldBeCleared(LivingEntity entity, StatusEffectInstance instance) {
        return false;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        BeaconatedGlobalMechanics.ParticleSystem(entity.getWorld(),entity.getBlockPos(),0.75,1);
    }
}
