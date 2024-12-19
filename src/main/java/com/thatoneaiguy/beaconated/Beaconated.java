package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.entity.Chnompner;
import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import com.thatoneaiguy.beaconated.init.BeaconatedItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

public class Beaconated implements ModInitializer {

	public static final String MOD_ID = "beaconated";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final SoundEvent ITEM_SHARP_ATTACK = registerSound("item.sharp_sweep");

	// lodestone particles
	public static LodestoneWorldParticleType SPARK = new LodestoneWorldParticleType();

	public static final EntityType<Chnompner> CHNOMPNER_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "chnompner"),
			FabricEntityTypeBuilder.<Chnompner>create(SpawnGroup.MISC, Chnompner::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.75F))
					.fireImmune()
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build());

	@Override
	public void onInitialize() {

		// lodestone particles
		SPARK = Registry.register(Registries.PARTICLE_TYPE, id("pixel"), SPARK);

		BeaconatedItems.registerModItems();

		BeaconatedBlocks.registerModBlocks();

		BeaconatedBlockEntities.registerBlockEntities();

		BeaconatedEffects.registerStatusEffect();

		LOGGER.info("Building the machinery");
	}

	private static SoundEvent registerSound(String id) {
		return Registry.register(Registries.SOUND_EVENT, id(id), SoundEvent.of(id(id)));
	}

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID,id);
	}
}