package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class BeaconatedItemGroup {
    public static final ItemGroup BEACONATED = FabricItemGroupBuilder.build(
            new Identifier(Beaconated.MOD_ID, "beaconated"), () -> new ItemStack(BeaconatedItems.VIBRANIUM_INGOT));
}
