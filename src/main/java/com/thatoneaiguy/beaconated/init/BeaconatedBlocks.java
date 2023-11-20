package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.blocks.VibraniumIlluminator;
import com.thatoneaiguy.beaconated.blocks.VibraniumChomper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class BeaconatedBlocks {

    public static final Block VIBRANIUM_BLOCK = registerBlock("vibranium_block",
            new Block(FabricBlockSettings.of(Material.METAL).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)), BeaconatedItemGroup.BEACONATED);

    public static final Block VIBRANIUM_CASING = registerBlock("vibranium_casing",
            new Block(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(25.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)), BeaconatedItemGroup.BEACONATED);

    public static final Block VIBRANIUM_BULB = registerBlock("vibranium_bulb",
            new Block(FabricBlockSettings.of(Material.METAL).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).luminance(15)), BeaconatedItemGroup.BEACONATED);

    public static final Block VIBRANIUM_ILLUMINATOR = registerBlock("vibranium_illuminator",
            new VibraniumIlluminator(FabricBlockSettings.of(Material.METAL).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).luminance(10)), BeaconatedItemGroup.BEACONATED);

    public static final Block VIBRANIUM_CHNOMPNER = registerBlock("vibranium_chnompner",
            new VibraniumChomper(FabricBlockSettings.of(Material.METAL).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).luminance(10)), BeaconatedItemGroup.BEACONATED);

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Beaconated.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(Beaconated.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Beaconated.LOGGER.debug("Turning the cogs!");
    }
}

