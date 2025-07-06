package com.irgendwer01.gsecore.mixins.gregtech;

import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMufflerHatch;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(MetaTileEntityMufflerHatch.class)
public class MixinMetaTileEntityMufflerHatch {

    /**
     * @author Irgendwer01
     * @reason Prevent Muffler lag
     */
    @Overwrite
    public void recoverItemsTable(List<ItemStack> recoveryItems) {
    }
}
