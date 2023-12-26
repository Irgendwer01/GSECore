package com.irgendwer01.gsecore.recipes;

import static gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES;

import java.util.*;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.irgendwer01.gsecore.recipes.recipemaps.SieveRecipeMap;

import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.HammerReward;
import exnihilocreatio.registries.types.Meltable;
import exnihilocreatio.registries.types.Siftable;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.chance.output.ChancedOutputLogic;

public class GTRecipes {

    // Recipe maps
    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new SieveRecipeMap("electric_sieve", 2, false,
            37,
            true, 0, false, 0, false,
            new SimpleRecipeBuilder().duration(100).EUt(4), false)
                    .setSound(SoundEvents.BLOCK_SAND_PLACE)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED);

    public static void registerExNihiloRecipes() {
        // Mirror Ex Nihilo Sifter recipes to Sifter RecipeMap
        for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
            for (ItemStack stack : recipe.getSievables()) {
                if (SIEVE_RECIPES.findRecipe(4, Arrays.asList(stack, recipe.getMesh()), new ArrayList<>(), true) !=
                        null)
                    continue;
                SimpleRecipeBuilder builder = SIEVE_RECIPES.recipeBuilder().notConsumable(recipe.getMesh())
                        .inputs(stack);

                for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(stack)) {
                    if (siftable.getMeshLevel() == recipe.getMesh().getMetadata())
                        if ((int) (siftable.getChance() * (float) ChancedOutputLogic.getMaxChancedValue()) >=
                                ChancedOutputLogic.getMaxChancedValue()) {
                                    builder.outputs(siftable.getDrop().getItemStack());
                                } else {
                                    builder.chancedOutput(siftable.getDrop().getItemStack(),
                                            (int) (siftable.getChance() *
                                                    (float) ChancedOutputLogic.getMaxChancedValue()),
                                            200);
                                }
                }
                builder.buildAndRegister();
            }
        }

        // Mirror Ex Nihilo Crucible recipes to Fluid Extractor RecipeMap
        for (Map.Entry<Ingredient, Meltable> entry : ExNihiloRegistryManager.CRUCIBLE_STONE_REGISTRY.getRegistry()
                .entrySet()) {
            for (ItemStack stack : entry.getKey().getMatchingStacks()) {
                if (EXTRACTOR_RECIPES.findRecipe(30, Collections.singletonList(stack), new ArrayList<>(), true) !=
                        null) {
                    continue;
                }
                SimpleRecipeBuilder builder = EXTRACTOR_RECIPES.recipeBuilder().duration(40).EUt(30);
                builder.input(stack.getItem(), 1, stack.getMetadata());
                builder.fluidOutputs(new FluidStack(FluidRegistry.getFluid(entry.getValue().getFluid()),
                        entry.getValue().getAmount()));
                builder.buildAndRegister();
            }
        }

        // Mirror Ex Nihilo Hammer recipes to Forge Hammer RecipeMap. NOTE: It will only include the first Drop, any
        // other drop is ignored!
        for (Map.Entry<Ingredient, List<HammerReward>> entry : ExNihiloRegistryManager.HAMMER_REGISTRY.getRegistry()
                .entrySet()) {
            for (ItemStack stack : entry.getKey().getMatchingStacks()) {
                if (FORGE_HAMMER_RECIPES.findRecipe(10, Collections.singletonList(stack), new ArrayList<>(), true) !=
                        null) {
                    continue;
                }
                SimpleRecipeBuilder builder = FORGE_HAMMER_RECIPES.recipeBuilder().EUt(10).duration(100);
                builder.input(stack.getItem(), 1, stack.getMetadata());
                HammerReward reward = entry.getValue().get(0);

                if (reward.getChance() == 1f) {
                    builder.output(reward.getStack().getItem(), reward.getStack().getCount(),
                            reward.getStack().getMetadata());
                } else {
                    builder.chancedOutput(reward.getStack(), (int) reward.getChance(), 250);
                }
                builder.buildAndRegister();
            }
        }
    }
}
