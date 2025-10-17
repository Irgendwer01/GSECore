package com.irgendwer01.gsecore.mixins.gregtech;

import com.irgendwer01.gsecore.GSEConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import gregtech.common.metatileentities.multi.BoilerType;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;

@Mixin(MetaTileEntityLargeBoiler.class)
public class MixinMetaTileEntityLargeBoiler {

    @Redirect(method = "addInformation",
              at = @At(value = "INVOKE",
                       target = "Lgregtech/common/metatileentities/multi/BoilerType;runtimeBoost(I)I"),
              remap = false)
    private int injected(BoilerType boilerType, int e) {
        if (GSEConfig.largeBoilerHigherEfficiency) {
            return boilerType.runtimeBoost(100);
        }
        return boilerType.runtimeBoost(20);
    }
}
