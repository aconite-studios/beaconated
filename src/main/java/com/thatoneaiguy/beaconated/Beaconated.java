package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.entity.Chnompner;
import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Beaconated implements ModInitializer {

	public static final String MOD_ID = "beaconated";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<Chnompner> CHNOMPNER_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
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

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID,id);
	}
}