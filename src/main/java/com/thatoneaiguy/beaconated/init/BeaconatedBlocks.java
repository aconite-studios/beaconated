package com.thatoneaiguy.beaconated.init;

import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.blocks.VibraniumBlock;
import com.thatoneaiguy.beaconated.blocks.VibraniumIlluminator;
import com.thatoneaiguy.beaconated.blocks.VibraniumChomper;
import com.thatoneaiguy.beaconated.blocks.VibraniumPhiltre;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class BeaconatedBlocks {

    public static final Block VIBRANIUM_BLOCK = registerBlock("vibranium_block",
            new VibraniumBlock(FabricBlockSettings.create().requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block VIBRANIUM_CASING = registerBlock("vibranium_casing",
            new Block(FabricBlockSettings.create().requiresTool().strength(25.0F, 1200.0F).sounds(BlockSoundGroup.WOOD)));

    public static final Block VIBRANIUM_BULB = registerBlock("vibranium_bulb",
            new Block(FabricBlockSettings.create().requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).luminance(15)));

    public static final Block VIBRANIUM_ILLUMINATOR = registerBlock("vibranium_illuminator",
            new VibraniumIlluminator(FabricBlockSettings.create().requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).luminance(10)));

    public static final Block VIBRANIUM_CHNOMPNER = registerBlock("vibranium_chnompner",
            new VibraniumChomper(FabricBlockSettings.create().requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block VIBRANIUM_PHILTRE = registerBlock("vibranium_philtre",
            new VibraniumPhiltre(FabricBlockSettings.create().requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Beaconated.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Beaconated.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    }

    public static void registerModBlocks() {
        Beaconated.LOGGER.debug("Turning the cogs!");
    }
}

