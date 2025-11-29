package com.irgendwer01.gsecore.mixins.openblocks;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import openblocks.common.tileentity.TileEntityBlockBreaker;
import openmods.fakeplayer.BreakBlockAction;
import openmods.fakeplayer.FakePlayerPool;
import openmods.utils.InventoryUtils;
import openmods.utils.ItemUtils;

@Mixin(TileEntityBlockBreaker.class)
public class MixinTileEntityBlockBreaker extends TileEntity {

    /**
     * @author Irgendwer01
     * @reason Don't let the Block Breaker running when there's no output inventory/output inventory is full
     */
    @Overwrite(remap = false)
    protected void doWork(IBlockState targetState, BlockPos target, EnumFacing direction) {
        List<ItemStack> drops = targetState.getBlock().getDrops(this.world, target, targetState, 0);
        if (!drops.isEmpty()) {
            EnumFacing dropSide = direction.getOpposite();
            IItemHandler targetInventory = InventoryUtils.tryGetHandler(this.world, this.pos.offset(dropSide),
                    direction);
            if (targetInventory != null) {
                AtomicBoolean canInsert = new AtomicBoolean();
                drops.forEach(drop -> {
                    if (ItemHandlerHelper.insertItem(targetInventory, drop, true) == ItemStack.EMPTY) {
                        canInsert.set(true);
                    } else {
                        canInsert.set(false);
                    }
                });
                if (canInsert.get()) {
                    List<EntityItem> realDrops = FakePlayerPool.instance.executeOnPlayer((WorldServer) this.world,
                            (new BreakBlockAction(this.world, target)).findEffectiveTool());
                    for (EntityItem drop : realDrops) {
                        ItemStack stack = drop.getItem();
                        ItemHandlerHelper.insertItem(targetInventory, stack, false);
                        ItemUtils.setEntityItemStack(drop, ItemStack.EMPTY);
                    }
                }
            }
        }
    }
}
