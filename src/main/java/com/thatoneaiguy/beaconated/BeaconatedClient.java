package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.entity.ChnompnerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BeaconatedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Beaconated.CHNOMPNER_ENTITY_TYPE, ChnompnerRenderer::new);
    }
}
