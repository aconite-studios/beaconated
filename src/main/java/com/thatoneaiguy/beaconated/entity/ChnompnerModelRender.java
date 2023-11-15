package com.thatoneaiguy.beaconated.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ChnompnerModelRender <T extends Entity> extends SinglePartEntityModel<T> {
    private static final String BASE = "base";
    private static final String UPPER_JAW = "upper_jaw";
    private static final String LOWER_JAW = "lower_jaw";
    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart upperJaw;
    private final ModelPart lowerJaw;

    public ChnompnerModelRender(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.upperJaw = root.getChild("upper_jaw");
        this.lowerJaw = root.getChild("lower_jaw");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 10.0F, 12.0F, 10.0F), ModelTransform.pivot(-5.0F, 24.0F, -5.0F));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(40, 0).cuboid(0.0F, 0.0F, 0.0F, 4.0F, 14.0F, 8.0F);
        modelPartData.addChild("upper_jaw", modelPartBuilder, ModelTransform.pivot(1.5F, 24.0F, -4.0F));
        modelPartData.addChild("lower_jaw", modelPartBuilder, ModelTransform.of(-1.5F, 24.0F, 4.0F, 0.0F, 3.1415927F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float f = limbAngle * 2.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        f = 1.0F - f * f * f;
        this.upperJaw.roll = 3.1415927F - f * 0.35F * 3.1415927F;
        this.lowerJaw.roll = 3.1415927F + f * 0.35F * 3.1415927F;
        float g = (limbAngle + MathHelper.sin(limbAngle * 2.7F)) * 0.6F * 12.0F;
        this.upperJaw.pivotY = 24.0F - g;
        this.lowerJaw.pivotY = this.upperJaw.pivotY;
        this.base.pivotY = this.upperJaw.pivotY;
    }

    public ModelPart getPart() {
        return this.root;
    }
}
