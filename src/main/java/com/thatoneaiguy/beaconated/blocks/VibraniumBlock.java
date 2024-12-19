package com.thatoneaiguy.beaconated.blocks;

import com.thatoneaiguy.beaconated.blocks.entities.VibraniumBlockEntity;
import com.thatoneaiguy.beaconated.blocks.entities.VibraniumChomperBlockEntity;
import com.thatoneaiguy.beaconated.blocks.entities.VibraniumPhiltreBlockEntity;
import com.thatoneaiguy.beaconated.init.BeaconatedBlockEntities;
import com.thatoneaiguy.beaconated.init.BeaconatedBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class VibraniumBlock extends BlockWithEntity implements BlockEntityProvider {
    public VibraniumBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VibraniumBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BeaconatedBlockEntities.BLOCK, VibraniumBlockEntity::tick);
    }
}

