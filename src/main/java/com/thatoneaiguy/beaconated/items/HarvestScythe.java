package com.thatoneaiguy.beaconated.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.thatoneaiguy.beaconated.Beaconated;
import com.thatoneaiguy.beaconated.init.BeaconatedEffects;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.UUID;

public class HarvestScythe extends HoeItem implements CustomAttackItem {

    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public HarvestScythe(FabricItemSettings settings) {
        super(ToolMaterials.NETHERITE, 7, -3.2F, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 9, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -3, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.5, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.5, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!attacker.isOnGround()) {
            applyReversedKnockBack(target, attacker);
        }

        attacker.getWorld().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), Beaconated.ITEM_SHARP_ATTACK, attacker.getSoundCategory(), 1.0F, 1.0F);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean postKnockBack(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.isOnGround()) {
            applyReversedKnockBack(target, attacker);
        }
        return CustomAttackItem.super.postKnockBack(stack, target, attacker);
    }

    private void applyReversedKnockBack(LivingEntity target, LivingEntity attacker) {
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

        double xKnockBack = xDiff * strength;
        double zKnockBack = zDiff * strength;

        target.takeKnockback(strength, xKnockBack, zKnockBack);
        target.velocityModified = true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        double attack = 9;
        double attackSpeed = -3;

        if (entity instanceof LivingEntity player && player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
            attack = 10;
            attackSpeed = -2;
        }
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", attack, EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
            builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.5, EntityAttributeModifier.Operation.ADDITION));
            builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", 0.5, EntityAttributeModifier.Operation.ADDITION));
            this.attributeModifiers = builder.build();

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public float getDamage(PlayerEntity player, ItemStack stack) {
        if (player.hasStatusEffect(BeaconatedEffects.SOLIDIFIED_HEART)) {
            return CustomAttackItem.super.getDamage(player,stack) + 1;
        }
        return CustomAttackItem.super.getDamage(player, stack);
    }

    @Override
    public void playSound(PlayerEntity player, SoundEvent sound) {
        // override attack sounds
    }

    @Override
    public boolean shouldSweep(ItemStack stack) {
        // to sweep...
        return true;
    }

    @Override
    public void applyKnockBack(LivingEntity livingEntity, PlayerEntity player, Entity target) {
        // change KnockBack
        livingEntity.velocityModified = true;
        livingEntity.setVelocity(target.getVelocity());
    }

    @Override
    public boolean originalSweep(boolean canCritical,boolean canKnockBack, boolean isItemCharged, PlayerEntity player) {
        // so you can sweep even if you crit
        return isItemCharged && !canKnockBack && player.horizontalSpeed-player.prevHorizontalSpeed < (double)player.getMovementSpeed();
    }

    @Override
    public Iterator<LivingEntity> getSweptEntities(PlayerEntity player, Entity target) {
        // bigger sweep
        return player.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(2, 0.5, 2)).iterator();
    }
}
