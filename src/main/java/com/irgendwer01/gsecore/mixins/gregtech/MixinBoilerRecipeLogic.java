package com.irgendwer01.gsecore.mixins.gregtech;

import com.irgendwer01.gsecore.GSEConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import gregtech.api.capability.impl.BoilerRecipeLogic;

@Mixin(BoilerRecipeLogic.class)
public abstract class MixinBoilerRecipeLogic {

    @ModifyVariable(method = "trySearchNewRecipe", at = @At("STORE"), ordinal = 1, remap = false)
    private int newBoilerLogic(int fuelBurnTime) {
        if (GSEConfig.largeBoilerHigherEfficiency) {
            return fuelBurnTime * 5;
        }
        return fuelBurnTime;
    }
}
