package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.entity.Chnompner;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class BeaconatedEntities {
    static Map<EntityType<?>, Identifier> ENTITIES = new LinkedHashMap();
    public static EntityType<Chnompner> CHNOMPNER = createEntity("chnompner", FabricEntityTypeBuilder.<Chnompner>create(SpawnGroup.MISC, Chnompner::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).fireImmune().trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static void register() {
        ENTITIES.keySet().forEach((entity) -> {
            Registry.register(Registry.ENTITY_TYPE, (Identifier)ENTITIES.get(entity), entity);
        });
    }

    static <T extends EntityType<?>> T createEntity(String name, T entity) {
        ENTITIES.put(entity, Identifier.tryParse(Beaconated.MOD_ID));
        return entity;
    }
}
