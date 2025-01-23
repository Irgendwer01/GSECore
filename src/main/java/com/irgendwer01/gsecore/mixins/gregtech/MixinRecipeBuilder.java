package com.irgendwer01.gsecore.mixins.gregtech;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.chance.output.ChancedOutputLogic;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;

@Mixin(value = RecipeBuilder.class, remap = false)
public abstract class MixinRecipeBuilder {

    @Final
    @Shadow
    protected List<ChancedItemOutput> chancedOutputs;

    @Shadow
    protected EnumValidationResult recipeStatus;

    /**
     *
     * @author Irgendwer01
     * @reason Allow chanced output to have a base output of 0
     */
    @Overwrite
    public RecipeBuilder<? extends RecipeBuilder<?>> chancedOutput(ItemStack stack, int chance, int tierChanceBoost) {
        if (stack != null && !stack.isEmpty()) {
            if (0 <= chance && chance <= ChancedOutputLogic.getMaxChancedValue()) {
                this.chancedOutputs.add(new ChancedItemOutput(stack.copy(), chance, tierChanceBoost));
                return (RecipeBuilder<? extends RecipeBuilder<?>>) (Object) this;
            } else {
                GTLog.logger.error("Chance cannot be less than 0 or more than {}. Actual: {}.",
                        ChancedOutputLogic.getMaxChancedValue(), chance);
                GTLog.logger.error("Stacktrace:", new IllegalArgumentException());
                this.recipeStatus = EnumValidationResult.INVALID;
                return (RecipeBuilder<? extends RecipeBuilder<?>>) (Object) this;
            }
        } else {
            return (RecipeBuilder<? extends RecipeBuilder<?>>) (Object) this;
        }
    }
}
