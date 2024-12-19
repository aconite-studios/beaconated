package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.entity.ChnompnerRenderer;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

import static com.thatoneaiguy.beaconated.Beaconated.SPARK;

public class BeaconatedClient implements ClientModInitializer {

    public static final Identifier PENDANT = new Identifier("beaconated", "textures/gui/solidified_hearts.png");

    @Override
    public void onInitializeClient() {

        // lodestone particles
        ParticleFactoryRegistry.getInstance().register(SPARK, LodestoneWorldParticleType.Factory::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                BeaconatedBlocks.VIBRANIUM_BULB,
                BeaconatedBlocks.VIBRANIUM_BLOCK,
                BeaconatedBlocks.VIBRANIUM_CASING,
                BeaconatedBlocks.VIBRANIUM_ILLUMINATOR,
                BeaconatedBlocks.VIBRANIUM_CHNOMPNER,
                BeaconatedBlocks.VIBRANIUM_PHILTRE
        );

        EntityRendererRegistry.register(Beaconated.CHNOMPNER_ENTITY_TYPE, ChnompnerRenderer::new);

    }
}
