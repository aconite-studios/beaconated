package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.items.HarvestScythe;
import com.thatoneaiguy.beaconated.items.VibraniumPendant;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class BeaconatedItems {

    public static final Item VIBRANIUM_INGOT = registerItem("vibranium_ingot", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    public static final Item HARVEST_SCYTHE = registerItem("harvest_scythe", new HarvestScythe(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));

    public static final Item VIBRANIUM_PENDANT = registerItem("vibranium_pendant", new VibraniumPendant(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));

    public static final Item GUILDED_DIARY = registerItem("guilded_diary", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Beaconated.id(name), item);
    }

    public static void registerModItems() {
        Beaconated.LOGGER.debug("Registering items for" + Beaconated.MOD_ID);
    }
}

