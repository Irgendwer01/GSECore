package com.irgendwer01.gsecore.mixins;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import nl.requios.effortlessbuilding.helper.SurvivalHelper;

@Mixin(SurvivalHelper.class)
public class SurvivalHelperMixin {

    @Inject(method = "placeBlock", at = @At("HEAD"), remap = false, cancellable = true)
    private static void PlaceBlockMixin(World world, EntityPlayer player, BlockPos pos, IBlockState blockState,
                                        ItemStack origstack, EnumFacing facing, Vec3d hitVec, boolean skipPlaceCheck,
                                        boolean skipCollisionCheck, boolean playSound,
                                        CallbackInfoReturnable<Boolean> cir) {
        int meta = blockState.getBlock().damageDropped(blockState);
        origstack = ItemStack.EMPTY;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
            ItemStack stack = player.inventory.mainInventory.get(i);
            if (stack.getItem().equals(Item.getItemFromBlock(blockState.getBlock())) && stack.getMetadata() == meta) {
                origstack = stack;
                break;
            }
        }
        if (origstack.equals(ItemStack.EMPTY)) cir.setReturnValue(false);
    }
}
