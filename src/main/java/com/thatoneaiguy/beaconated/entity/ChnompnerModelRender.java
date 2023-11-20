package com.thatoneaiguy.beaconated.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;

@Environment(EnvType.CLIENT)
public class ChnompnerModelRender extends EvokerFangsEntityModel {
    public ChnompnerModelRender(ModelPart root) {
        super(root);
    }
}
