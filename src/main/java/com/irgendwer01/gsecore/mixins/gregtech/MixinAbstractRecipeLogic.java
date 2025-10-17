package com.irgendwer01.gsecore.mixins.gregtech;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import personalworlds.world.PWWorldProvider;

@Mixin(AbstractRecipeLogic.class)
public class MixinAbstractRecipeLogic {

    @Redirect(method = "checkDimensionRequirement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getDimension()I"))
    private int getDimension(WorldProvider instance) {
        if (instance instanceof PWWorldProvider) {
            return 0;
        }
        return instance.getDimension();
    }
}
