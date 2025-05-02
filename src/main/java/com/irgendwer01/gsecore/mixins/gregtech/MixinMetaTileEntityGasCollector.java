package com.irgendwer01.gsecore.mixins.gregtech;

import gregtech.common.metatileentities.electric.MetaTileEntityGasCollector;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import personalworlds.world.PWWorldProvider;

@Mixin(value = MetaTileEntityGasCollector.class, remap = false)
public class MixinMetaTileEntityGasCollector {

    @Redirect(method = "checkRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getDimension()I"))
    private int getDimension(WorldProvider instance) {
        if (instance instanceof PWWorldProvider) {
            return 0;
        } else {
            return instance.getDimension();
        }
    }
}
