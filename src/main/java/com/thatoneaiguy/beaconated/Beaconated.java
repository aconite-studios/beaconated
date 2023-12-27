package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.entity.Chnompner;
import com.thatoneaiguy.beaconated.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.command.GiveCommand;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.minecraft.server.command.CommandManager.*;

import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class Beaconated implements ModInitializer {

	public static final String MOD_ID = "beaconated";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier vibranium_step = new Identifier("beaconated:block.vibranium.step");

	public static final Identifier vibranium_place = new Identifier("beaconated:block.vibranium.place");

	public static SoundEvent VIBRANIUM_STEP = new SoundEvent(vibranium_step);

	public static SoundEvent VIBRANIUM_PLACE = new SoundEvent(vibranium_place);

	public static final EntityType<Chnompner> CHNOMPNER_ENTITY_TYPE = Registry.register(

			Registry.ENTITY_TYPE,
			new Identifier(MOD_ID, "chnompner"),
			FabricEntityTypeBuilder.<Chnompner>create(SpawnGroup.MISC, Chnompner::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.75F))
					.fireImmune()
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build());

	@Override
	public void onInitialize() {

		BeaconatedItems.registerModItems();

		BeaconatedBlocks.registerModBlocks();

		BeaconatedBlockEntities.registerBlockEntities();

		BeaconatedEffects.registerStatusEffect();

		LOGGER.info("Building the machinery");
	}
}