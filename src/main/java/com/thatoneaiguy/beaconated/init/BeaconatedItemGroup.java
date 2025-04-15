package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class BeaconatedItemGroup {

    public static final ItemGroup BEACONATED = Registry.register(Registries.ITEM_GROUP,
            Beaconated.id("beaconated"),
            FabricItemGroup.builder().displayName(Text.of("Beaconated"))
                    .icon(() -> new ItemStack(BeaconatedItems.VIBRANIUM_INGOT)).entries((displayContext, entries) -> {
                        entries.add(BeaconatedItems.VIBRANIUM_INGOT);
                        entries.add(BeaconatedItems.VIBRANIUM_PENDANT);
                        entries.add(BeaconatedItems.HARVEST_SCYTHE);
                        entries.add(BeaconatedItems.GUILDED_DIARY);
                        entries.add(BeaconatedBlocks.VIBRANIUM_BLOCK);
                        entries.add(BeaconatedBlocks.VIBRANIUM_CASING);
                        entries.add(BeaconatedBlocks.VIBRANIUM_BULB);
                        entries.add(BeaconatedBlocks.VIBRANIUM_CHNOMPNER);
                        entries.add(BeaconatedBlocks.VIBRANIUM_PHILTRE);
                        entries.add(BeaconatedBlocks.VIBRANIUM_ILLUMINATOR);
                    }).build());

    public static void registerModItemGroup() {
        Beaconated.LOGGER.debug("Registering item group for" + Beaconated.MOD_ID);
    }
}
