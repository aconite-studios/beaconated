package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.init.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Beaconated implements ModInitializer {

	public static final String MOD_ID = "beaconated";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier vibranium_step = new Identifier("beaconated:block.vibranium.step");
	public static final Identifier vibranium_place = new Identifier("beaconated:block.vibranium.place");

	public static SoundEvent VIBRANIUM_STEP = new SoundEvent(vibranium_step);
	public static SoundEvent VIBRANIUM_PLACE = new SoundEvent(vibranium_place);

	@Override
	public void onInitialize() {

		BeaconatedItems.registerModItems();
		BeaconatedBlocks.registerModBlocks();
		BeaconatedBlockEntities.registerBlockEntities();
		BeaconatedEffects.registerStatusEffect();
		BeaconatedEntities.register();

		LOGGER.info("Building the machinery");
	}
}