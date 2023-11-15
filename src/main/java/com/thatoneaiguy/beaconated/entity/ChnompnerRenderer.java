package com.thatoneaiguy.beaconated.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EvokerFangsEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class ChnompnerRenderer  extends EvokerFangsEntityRenderer {
    private static final Identifier TEXTURE = new Identifier("textures/entity/illager/evoker_fangs.png");

    public ChnompnerRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(EvokerFangsEntity evokerFangsEntity) {
        return TEXTURE;
    }
}
