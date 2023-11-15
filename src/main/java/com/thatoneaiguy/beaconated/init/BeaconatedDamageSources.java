package com.thatoneaiguy.beaconated.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;

public class BeaconatedDamageSources {
    public static final DamageSource CHNOMP = (new DamageSource("chnomp")).setBypassesArmor().setUnblockable();
    public final String name;

    public static DamageSource chnomp(LivingEntity attacker) {
        return new EntityDamageSource("chnomp", attacker);
    }

    public BeaconatedDamageSources(String name) {
        this.name = name;
    }

    public Text getDeathMessage(LivingEntity entity) {
        LivingEntity livingEntity = entity.getPrimeAdversary();
        String string = "death.attack." + this.name;
        String string2 = string + ".player";
        return livingEntity != null ? Text.translatable(string2, new Object[]{entity.getDisplayName(), livingEntity.getDisplayName()}) : Text.translatable(string, new Object[]{entity.getDisplayName()});
    }
}