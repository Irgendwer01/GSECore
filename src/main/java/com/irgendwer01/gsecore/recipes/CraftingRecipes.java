package com.irgendwer01.gsecore.recipes;

import static com.irgendwer01.gsecore.metatileentities.MetaTileEntities.*;
import static gregtech.api.unification.ore.OrePrefix.stick;
import static gregtech.api.unification.ore.OrePrefix.stone;
import static gregtech.common.blocks.BlockSteamCasing.SteamCasingType.BRONZE_HULL;
import static gregtech.loaders.recipe.CraftingComponent.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.irgendwer01.gsecore.GSECoreMod;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.MetaTileEntityLoader;

public class CraftingRecipes {

    public static void RegisterCraftingRecipes() {
        // Machine Recipes
        MetaTileEntityLoader.registerMachineRecipe(SIEVES, "CPC", "FMF", "OSO", 'M', HULL, 'C', CIRCUIT, 'O', CABLE,
                'F', CONVEYOR, 'S', new ItemStack(ModBlocks.sieve), 'P', PISTON);

        ModHandler.addShapedRecipe(true, "4a_lv_dynamo_hatch", ENERGY_OUTPUT_HATCH_4A[0].getStackForm(), "C C", "CEC",
                'C', OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.Tin), 'E',
                MetaTileEntities.ENERGY_OUTPUT_HATCH[1].getStackForm());
        ModHandler.addShapedRecipe(true, "16a_lv_dynamo_hatch", ENERGY_OUTPUT_HATCH_16A[0].getStackForm(), "CTC", "CEC",
                'C', OreDictUnifier.get(OrePrefix.cableGtOctal, Materials.Tin), 'E',
                ENERGY_OUTPUT_HATCH_4A[0].getStackForm(), 'T', MetaTileEntities.TRANSFORMER[1].getStackForm());
        ModHandler.addShapedRecipe(true, "4a_mv_dynamo_hatch", ENERGY_OUTPUT_HATCH_4A[1].getStackForm(), "C C", "CEC",
                'C', OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.Copper), 'E',
                MetaTileEntities.ENERGY_OUTPUT_HATCH[2].getStackForm());
        ModHandler.addShapedRecipe(true, "16a_mv_dynamo_hatch", ENERGY_OUTPUT_HATCH_16A[1].getStackForm(), "CTC", "CEC",
                'C', OreDictUnifier.get(OrePrefix.cableGtOctal, Materials.Copper), 'E',
                ENERGY_OUTPUT_HATCH_4A[1].getStackForm(), 'T', MetaTileEntities.TRANSFORMER[2].getStackForm());

        ModHandler.addShapedRecipe(true, "steam_sieve_bronze", STEAM_SIEVE_BRONZE.getStackForm(), "BPB", "BMB", "BSB",
                'B', new UnificationEntry(OrePrefix.pipeSmallFluid, Materials.Bronze), 'M',
                MetaBlocks.STEAM_CASING.getItemVariant(BRONZE_HULL), 'S', new ItemStack(ModBlocks.sieve), 'P',
                Blocks.PISTON);
        ModHandler.addShapedRecipe(true, "steam_sieve_steel", STEAM_SIEVE_STEEL.getStackForm(), "BPB", "WMW", "BBB",
                'B', new UnificationEntry(OrePrefix.pipeSmallFluid, Materials.TinAlloy), 'M',
                STEAM_SIEVE_BRONZE.getStackForm(), 'W', new UnificationEntry(OrePrefix.plate, Materials.WroughtIron),
                'P', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe(true, "large_sieve", LARGE_SIEVE.getStackForm(), "PCP", "CMC", "WWW",
                'P', PISTON.getIngredient(2),
                'M', SIEVES[1].getStackForm(), 'C', CIRCUIT.getIngredient(2),
                'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));

        // Pebbles
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_2");
        ModHandler.addShapedRecipe("bronze_mesh", new ItemStack(ModItems.mesh, 1, 2), "TST", "STS", "TST",
                'T', new UnificationEntry(stick, Materials.Bronze),
                'S', new ItemStack(Items.STRING));
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_3");
        ModHandler.addShapedRecipe("steel_mesh", new ItemStack(ModItems.mesh, 1, 3), "TST", "STS", "TST",
                'T', new UnificationEntry(stick, Materials.Steel),
                'S', new ItemStack(Items.STRING));
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_4");
        ModHandler.addShapedRecipe("aluminium_mesh", new ItemStack(ModItems.mesh, 1, 4), "TST", "STS", "TST",
                'T', new UnificationEntry(stick, Materials.Aluminium),
                'S', new ItemStack(Items.STRING));

        ModHandler.addShapedRecipe("basalt", OreDictUnifier.get(stone, Materials.Basalt, 1), "PP", "PP", 'P',
                new ItemStack(GSECoreMod.GTPebbles, 1, 0));
        ModHandler.addShapedRecipe("black_granite", OreDictUnifier.get(stone, Materials.GraniteBlack, 1), "PP", "PP",
                'P',
                new ItemStack(GSECoreMod.GTPebbles, 1, 1));
        ModHandler.addShapedRecipe("marble", OreDictUnifier.get(stone, Materials.Marble, 1), "PP", "PP", 'P',
                new ItemStack(GSECoreMod.GTPebbles, 1, 2));
        ModHandler.addShapedRecipe("red_granite", OreDictUnifier.get(stone, Materials.GraniteRed, 1), "PP", "PP", 'P',
                new ItemStack(GSECoreMod.GTPebbles, 1, 3));
    }
}
