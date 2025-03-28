package com.irgendwer01.gsecore.recipes.recipemaps;

import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.RecipeProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

public class SieveRecipeMap extends RecipeMap<SimpleRecipeBuilder> {

    private final boolean twoInputs;

    public SieveRecipeMap(@NotNull String unlocalizedName, int maxInputs, boolean modifyItemInputs, int maxOutputs,
                          boolean modifyItemOutputs, int maxFluidInputs, boolean modifyFluidInputs, int maxFluidOutputs,
                          boolean modifyFluidOutputs, @NotNull SimpleRecipeBuilder defaultRecipeBuilder,
                          boolean isHidden, boolean twoInputs) {
        super(unlocalizedName, maxInputs, modifyItemInputs, maxOutputs, modifyItemOutputs, maxFluidInputs,
                modifyFluidInputs, maxFluidOutputs, modifyFluidOutputs, defaultRecipeBuilder, isHidden);
        this.twoInputs = twoInputs;
    }

    @Override
    @NotNull
    public ModularUI.Builder createJeiUITemplate(IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems,
                                                 FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = new ModularUI.Builder(GuiTextures.BACKGROUND, 176, 192 + yOffset);
        builder.widget(
                new RecipeProgressWidget(200, 25, 50 + yOffset, 20, 20, this.progressBarTexture, this.moveType, this));
        this.addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        this.addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
        if (this.specialTexture != null && this.specialTexturePosition != null) {
            this.addSpecialTexture(builder);
        }

        return builder;
    }

    @Override
    protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler,
                                         FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
        if (isOutputs) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 6; x++) {
                    addSlot(builder, 61 + x * 18, y * 18, y * 6 + x, itemHandler, fluidHandler, false, true);
                }
            }
        } else {
            if (!twoInputs) {
                addSlot(builder, 26, 26, 0, itemHandler, fluidHandler, false, false);
            } else {
                addSlot(builder, 17, 26, 0, itemHandler, fluidHandler, false, false);
                addSlot(builder, 35, 26, 1, itemHandler, fluidHandler, false, false);
            }
        }
    }
}
