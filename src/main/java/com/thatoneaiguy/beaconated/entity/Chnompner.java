package com.thatoneaiguy.beaconated.entity;

import com.thatoneaiguy.beaconated.Beaconated;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.world.World;

public class Chnompner extends EvokerFangsEntity {
    private boolean startedAttack;
    private int ticksLeft;
    private boolean playingAnimation;

    public Chnompner(World world, double x, double y, double z, float yaw) {
        this(Beaconated.CHNOMPNER_ENTITY_TYPE, world);
        this.setYaw(yaw * 57.295776F);
        this.setPosition(x, y, z);
    }

    public Chnompner(EntityType<Chnompner> chnompner, World world) {
        super(chnompner, world);
    }

}