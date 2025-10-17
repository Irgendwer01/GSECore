package com.irgendwer01.gsecore.recipes;

import static com.irgendwer01.gsecore.metatileentities.MetaTileEntities.*;

import com.irgendwer01.gsecore.GSEConfig;

import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;

public class CraftingRecipes {

    public static void RegisterCraftingRecipes() {
        // Machine Recipes
        if (GSEConfig.enableLSH) {
            ModHandler.addShapedRecipe(true, "large_steam_hammer", LARGE_STEAM_HAMMER.getStackForm(), "CGC", "BMB",
                    "CGC",
                    'M', MetaTileEntities.STEAM_HAMMER_BRONZE.getStackForm(), 'B',
                    MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.BRONZE_PIPE),
                    'C', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS),
                    'G', new UnificationEntry(OrePrefix.gear, Materials.Potin));
        }
        if (GSEConfig.harderSteamEngine) {
            ModHandler.removeRecipeByOutput(GCYMMetaTileEntities.STEAM_ENGINE.getStackForm());
            ModHandler.addShapedRecipe(true, "industrial_steam_engine",
                    GCYMMetaTileEntities.STEAM_ENGINE.getStackForm(), "PBP", "BMB", "GSG",
                    'P', new UnificationEntry(OrePrefix.pipeHugeFluid, Materials.Potin),
                    'B', new UnificationEntry(OrePrefix.plate, Materials.Brass),
                    'M', MetaTileEntities.STEAM_TURBINE[1].getStackForm(),
                    'G', new UnificationEntry(OrePrefix.gear, Materials.Bronze),
                    'S', new UnificationEntry(OrePrefix.gear, Materials.Steel));
        }
    }
}
