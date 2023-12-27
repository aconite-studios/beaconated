package com.thatoneaiguy.beaconated.mixin;

import com.thatoneaiguy.beaconated.init.BeaconatedItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    private static final ModelIdentifier HARVEST_SCYTHE_GUI = new ModelIdentifier("beaconated:harvest_scythe_gui#inventory");

    @Shadow
    private @Final ItemModels models;

    @ModifyVariable(method = "renderItem", at = @At("HEAD"), argsOnly = true)
    private BakedModel beaconated_guiModel(BakedModel model, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel modelAgain) {
        boolean bl = renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND;
        if (bl) {
            if (stack.isOf(BeaconatedItems.HARVEST_SCYTHE)) {
                return models.getModelManager().getModel(HARVEST_SCYTHE_GUI);
            }
        }
        return model;
    }
}
