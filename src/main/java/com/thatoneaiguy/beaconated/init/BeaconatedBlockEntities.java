package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.blocks.entities.VibraniumIlluminatorBlockEntity;
import com.thatoneaiguy.beaconated.blocks.entities.VibraniumChomperBlockEntity;
import com.thatoneaiguy.beaconated.blocks.entities.VibraniumPhiltreBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeaconatedBlockEntities {

    public static BlockEntityType<VibraniumIlluminatorBlockEntity> VIBRANIUM_ILLUMINATOR;

    public static BlockEntityType<VibraniumChomperBlockEntity> CHOMPER;

    public static BlockEntityType<VibraniumPhiltreBlockEntity> PHILTRE;

    public static void registerBlockEntities() {
        VIBRANIUM_ILLUMINATOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Beaconated.MOD_ID, "vibranium_illuminator"),
                FabricBlockEntityTypeBuilder.create(VibraniumIlluminatorBlockEntity::new,
                        BeaconatedBlocks.VIBRANIUM_ILLUMINATOR).build(null));
        CHOMPER = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Beaconated.MOD_ID, "vibranium_chnompner"),
                FabricBlockEntityTypeBuilder.create(VibraniumChomperBlockEntity::new,
                        BeaconatedBlocks.VIBRANIUM_CHNOMPNER).build(null));
        PHILTRE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Beaconated.MOD_ID, "vibranium_philtre"),
                FabricBlockEntityTypeBuilder.create(VibraniumPhiltreBlockEntity::new,
                        BeaconatedBlocks.VIBRANIUM_PHILTRE).build(null));
    }
}
