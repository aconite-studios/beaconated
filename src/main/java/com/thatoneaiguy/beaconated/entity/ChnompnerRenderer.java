package com.thatoneaiguy.beaconated.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EvokerFangsEntityRenderer;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ChnompnerRenderer  extends EvokerFangsEntityRenderer {
// doesnt work    private static final Identifier TEXTURE = new Identifier("beaconated/textures/entity/chnompner.png");

    public ChnompnerRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

 /*   public Identifier getTexture(EvokerFangsEntity evokerFangsEntity) {
        return TEXTURE;
    }
    */

}
