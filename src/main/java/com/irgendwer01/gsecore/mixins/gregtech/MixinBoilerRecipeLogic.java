package com.irgendwer01.gsecore.mixins.gregtech;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import gregtech.api.capability.impl.BoilerRecipeLogic;

@Mixin(BoilerRecipeLogic.class)
public abstract class MixinBoilerRecipeLogic {

    @ModifyVariable(method = "trySearchNewRecipe", at = @At("STORE"), ordinal = 1, remap = false)
    private int newBoilerLogic(int fuelBurnTime) {
        if (fuelBurnTime < 1200) {
            return 0;
        }
        // Remove when new CEu update is out (If #2661 really gets merged)
        return fuelBurnTime * 10;
    }
}
