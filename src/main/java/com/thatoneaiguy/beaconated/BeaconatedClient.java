package com.thatoneaiguy.beaconated;

import com.thatoneaiguy.beaconated.entity.ChnompnerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.util.ModelIdentifier;

public class BeaconatedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Beaconated.CHNOMPNER_ENTITY_TYPE, ChnompnerRenderer::new);

        ModelLoadingRegistry.INSTANCE.registerModelProvider((resources, out) -> out.accept(new ModelIdentifier("beaconated", "harvest_scythe_gui", "inventory")));
    }
}
