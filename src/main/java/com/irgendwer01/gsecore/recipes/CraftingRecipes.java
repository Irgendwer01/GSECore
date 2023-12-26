package com.irgendwer01.gsecore.recipes;

import static com.irgendwer01.gsecore.metatileentities.MetaTileEntities.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.stick;
import static gregtech.api.unification.ore.OrePrefix.stone;
import static gregtech.common.blocks.BlockSteamCasing.SteamCasingType.BRONZE_HULL;
import static gregtech.loaders.recipe.CraftingComponent.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.irgendwer01.gsecore.GSECore;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModItems;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.loaders.recipe.MetaTileEntityLoader;

public class CraftingRecipes {

    public static void RegisterCraftingRecipes() {
        // Machine Recipes
        MetaTileEntityLoader.registerMachineRecipe(SIEVES, "CPC", "FMF", "OSO", 'M', HULL, 'C', CIRCUIT, 'O', CABLE,
                'F', CONVEYOR, 'S', new ItemStack(ModBlocks.sieve), 'P', PISTON);
        ModHandler.addShapedRecipe(true, "steam_sieve_bronze", STEAM_SIEVE_BRONZE.getStackForm(), "BPB", "BMB", "BSB",
                'B', new UnificationEntry(OrePrefix.pipeSmallFluid, Materials.Bronze), 'M',
                MetaBlocks.STEAM_CASING.getItemVariant(BRONZE_HULL), 'S', new ItemStack(ModBlocks.sieve), 'P',
                Blocks.PISTON);
        ModHandler.addShapedRecipe(true, "steam_sieve_steel", STEAM_SIEVE_STEEL.getStackForm(), "BPB", "WMW", "BBB",
                'B', new UnificationEntry(OrePrefix.pipeSmallFluid, Materials.WroughtIron), 'M',
                STEAM_SIEVE_BRONZE.getStackForm(), 'W', new UnificationEntry(OrePrefix.plate, Materials.WroughtIron),
                'P', new UnificationEntry(OrePrefix.plate, Materials.Steel));

        // Pebbles
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_2");
        ModHandler.addShapedRecipe("bronze_mesh", new ItemStack(ModItems.mesh, 1, 2), "TST", "STS", "TST",
                'T', new UnificationEntry(stick, Materials.Bronze),
                'S', new ItemStack(Items.STRING));
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_3");
        ModHandler.addShapedRecipe("steel_mesh", new ItemStack(ModItems.mesh, 1, 3), "TST", "STS", "TST",
                'T', new UnificationEntry(stick, Steel),
                'S', new ItemStack(Items.STRING));
        ModHandler.removeRecipeByName("exnihilocreatio:item_mesh_4");
        ModHandler.addShapedRecipe("aluminium_mesh", new ItemStack(ModItems.mesh, 1, 4), "TST", "STS", "TST",
                'T', new UnificationEntry(stick, Aluminium),
                'S', new ItemStack(Items.STRING));

        ModHandler.addShapedRecipe("basalt", OreDictUnifier.get(stone, Basalt, 1), "PP", "PP", 'P',
                new ItemStack(GSECore.GTPebbles, 1, 0));
        ModHandler.addShapedRecipe("black_granite", OreDictUnifier.get(stone, GraniteBlack, 1), "PP", "PP", 'P',
                new ItemStack(GSECore.GTPebbles, 1, 1));
        ModHandler.addShapedRecipe("marble", OreDictUnifier.get(stone, Marble, 1), "PP", "PP", 'P',
                new ItemStack(GSECore.GTPebbles, 1, 2));
        ModHandler.addShapedRecipe("red_granite", OreDictUnifier.get(stone, GraniteRed, 1), "PP", "PP", 'P',
                new ItemStack(GSECore.GTPebbles, 1, 3));
    }
}
