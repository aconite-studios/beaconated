package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.statuseffect.SolidifiedHeartStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeaconatedEffects {

    public static final StatusEffect SOLIDIFIED_HEART = registerStatusEffect("solidified_heart", new SolidifiedHeartStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFF891C));

    public static <T extends StatusEffect> T registerStatusEffect(String name, T effect) {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Beaconated.MOD_ID, name), effect);
        return effect;
    }

    public static void registerStatusEffect() {
        Beaconated.LOGGER.debug("Registering effects for" + Beaconated.MOD_ID);
    }
}
