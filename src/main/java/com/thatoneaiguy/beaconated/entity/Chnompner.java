package com.thatoneaiguy.beaconated.entity;

import com.thatoneaiguy.beaconated.init.BeaconatedEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Chnompner extends EvokerFangsEntity {
    private int warmup;
    private boolean startedAttack;
    private int ticksLeft;
    private boolean playingAnimation;

    public Chnompner(World world, double x, double y, double z, float yaw, int warmup) {
        this(BeaconatedEntities.CHNOMPNER, world);
        this.warmup = warmup;
        this.setYaw(yaw * 57.295776F);
        this.setPosition(x, y, z);
    }

    public Chnompner(EntityType<Chnompner> chnompner, World world) {
        super(chnompner, world);
    }
}