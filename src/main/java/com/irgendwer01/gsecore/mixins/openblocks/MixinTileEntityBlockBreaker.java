package com.irgendwer01.gsecore.mixins.openblocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import openblocks.common.tileentity.TileEntityBlockBreaker;
import openmods.utils.InventoryUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityBlockBreaker.class)
public class MixinTileEntityBlockBreaker extends TileEntity {

    /**
     * @author Irgendwer01
     * @reason Don't let the Block Breaker running when there's no output inventory/output inventory is full
     */
    @Overwrite(remap = false)
    protected boolean canWork(IBlockState targetState, BlockPos target, EnumFacing direction) {
        EnumFacing dropSide = direction.getOpposite();

        IItemHandler targetInventory = InventoryUtils.tryGetHandler(this.world, this.pos.offset(dropSide), direction);
        Block block = targetState.getBlock();
        if (targetInventory != null) {
            return !block.isAir(targetState, this.world, target) && block != Blocks.BEDROCK && targetState.getBlockHardness(this.world, target) > -1.0F && (ItemHandlerHelper.insertItem(targetInventory, new ItemStack(Items.STICK), true) == ItemStack.EMPTY);
        }
        return false;
    }
}
