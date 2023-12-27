package com.thatoneaiguy.beaconated.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class HarvestScythe extends SwordItem {

    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public HarvestScythe(FabricItemSettings settings) {
        super(ToolMaterials.NETHERITE, 6, -3.2F, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 6, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -3.2, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.5, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.isOnGround()) {
            applyReversedKnockback(target, attacker);
            target.velocityModified = true;
        }
        return super.postHit(stack, target, attacker);
    }

    private void applyReversedKnockback(LivingEntity target, LivingEntity attacker) {
        Vec3d attackerPos = attacker.getPos();
        Vec3d targetPos = target.getPos();
        double xDiff = targetPos.x - attackerPos.x;
        double zDiff = targetPos.z - attackerPos.z;
        double distance = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

        EntityAttributeInstance reachAttribute = attacker.getAttributeInstance(ReachEntityAttributes.REACH);
        double maxReach = reachAttribute == null ? 5.0 : reachAttribute.getValue();
        double maxStrength = 0.8;
        double strength = Math.min(0.8, Math.max(0.2, maxStrength * (distance / maxReach)));

        target.setVelocity(0, 0, 0);

        double xKnockback = xDiff * strength;
        double zKnockback = zDiff * strength;

        target.takeKnockback(strength, xKnockback, zKnockback);
    }
}
