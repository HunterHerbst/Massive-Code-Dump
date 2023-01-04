package com.darkwaterkiller.schmatches;

import java.util.Objects;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.BlockRayTraceResult;





public class Match extends Item {
    
    
    public Match(Properties properties) {
        super(properties);
    }

    private void playLightingSound(World world, PlayerEntity playerEntity, BlockPos blockPosForFire) {
        world.playSound(playerEntity, blockPosForFire, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
    }

    private void lightFire(ItemUseContext context, World world, PlayerEntity playerEntity) {
        // get block position for fire
        BlockPos blockPosForFire = context.getPos().offset(context.getFace());
        // if block clicked is a campfire, light it
        
        if(world.getBlockState(context.getPos()).getBlock() instanceof net.minecraft.block.CampfireBlock) {
            world.setBlockState(context.getPos(), world.getBlockState(context.getPos()).with(CampfireBlock.LIT, true));
            playLightingSound(world, playerEntity, blockPosForFire);
        }
        else if(AbstractFireBlock.canLightBlock(world, blockPosForFire, context.getPlacementHorizontalFacing())) {
            BlockState blockstate = AbstractFireBlock.getFireForPlacement(world, blockPosForFire);
            world.setBlockState(blockPosForFire, blockstate, 11);
            playLightingSound(world, playerEntity, blockPosForFire);
        }
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        
        // Get the world
        World world = context.getWorld();

        // Make sure world isn't remote
        if(!world.isRemote) {
            // get player and require it non null
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            // get clicked block blockstate
            BlockState clickedBlock = world.getBlockState(context.getPos());

            // perform right click action on block
            lightFire(context, world, playerEntity);
            // remove one match from stack
            stack.shrink(1);
        }
        
        return super.onItemUseFirst(stack, context);
    }
}