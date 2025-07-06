package com.irgendwer01.gsecore.mixins.gregtech;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMufflerHatch;

@Mixin(value = MetaTileEntityMufflerHatch.class, remap = false)
public class MixinMetaTileEntityMufflerHatch {

    /**
     * @author Irgendwer01
     * @reason Prevent Muffler lag
     */
    @Overwrite
    public void recoverItemsTable(List<ItemStack> recoveryItems) {}
}
