package com.thatoneaiguy.beaconated.init;

import net.minecraft.entity.damage.DamageSource;

public class BeaconatedDamageSource extends DamageSource {
    public static final DamageSource CHNOMPNER = new BeaconatedDamageSource("chnompner").setBypassesArmor().setUnblockable();

    protected BeaconatedDamageSource(String name) {
        super(name);
    }
}

