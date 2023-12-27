package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.items.HarvestScythe;
import com.thatoneaiguy.beaconated.items.VibraniumPendant;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class BeaconatedItems {

    public static final Item VIBRANIUM_INGOT = registerItem("vibranium_ingot", new Item(new FabricItemSettings().group(BeaconatedItemGroup.BEACONATED).rarity(Rarity.UNCOMMON)));

    public static final Item HARVEST_SCYTHE = registerItem("harvest_scythe", new HarvestScythe(new FabricItemSettings().group(BeaconatedItemGroup.BEACONATED).rarity(Rarity.UNCOMMON).maxCount(1)));

    public static final Item VIBRANIUM_PENDANT = registerItem("vibranium_pendant", new VibraniumPendant(new FabricItemSettings().group(BeaconatedItemGroup.BEACONATED).rarity(Rarity.UNCOMMON).maxCount(1)));

    public static final Item GUILDED_DIARY = registerItem("guilded_diary", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1).group(BeaconatedItemGroup.BEACONATED)));

    public static final Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Beaconated.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Beaconated.LOGGER.debug("Registering items for" + Beaconated.MOD_ID);
    }
}

