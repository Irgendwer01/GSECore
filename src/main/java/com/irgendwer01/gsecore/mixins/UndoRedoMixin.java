package com.irgendwer01.gsecore.mixins;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import nl.requios.effortlessbuilding.buildmodifier.UndoRedo;

@Mixin(UndoRedo.class)
public class UndoRedoMixin {

    @Inject(method = "findItemStackInInventory", at = @At("HEAD"), cancellable = true, remap = false)
    private static void findItemStackInInventory(EntityPlayer player, IBlockState blockState,
                                                 CallbackInfoReturnable<ItemStack> cir) {
        int meta = blockState.getBlock().damageDropped(blockState);
        ItemStack stack = ItemStack.EMPTY;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
            ItemStack stack1 = player.inventory.mainInventory.get(i);
            if (stack1.getItem().equals(Item.getItemFromBlock(blockState.getBlock())) && stack1.getMetadata() == meta) {
                stack = stack1;
                break;
            }
        }
        cir.setReturnValue(stack);
    }
}
