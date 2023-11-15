package com.thatoneaiguy.beaconated.datagen;

import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import com.thatoneaiguy.beaconated.init.BeaconatedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class BeaconatedModelGenerator extends FabricModelProvider {
    public BeaconatedModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
