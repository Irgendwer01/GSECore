package com.irgendwer01.gsecore.metatileentities.multi.electric;

import static com.irgendwer01.gsecore.recipes.GTRecipes.LARGE_SIEVE_RECIPES;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import exnihilocreatio.ModBlocks;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

public class MetaTileEntityLargeSieveMultiblock extends RecipeMapMultiblockController {

    public MetaTileEntityLargeSieveMultiblock(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, LARGE_SIEVE_RECIPES);
        this.recipeMapWorkable = new LargeSieveWorkable(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeSieveMultiblock(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" CCCCC ", " CCCCC ", " CCCCC ", " CCCCC ")
                .aisle("CCCCCCC", "CDDDDDC", "C#####C", "CFFFFFC")
                .aisle("CCCCCCC", "CDDDDDC", "C#####C", "CFFFFFC")
                .aisle("CCCCCCC", "CDDDDDC", "C#####C", "CFFFFFC")
                .aisle("CCCCCCC", "CDDDDDC", "C#####C", "CFFFFFC")
                .aisle(" CCCCC ", " CCSCC ", " CCCCC ", " CCCCC ")
                .where('S', selfPredicate())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)))
                .where('D', states(ModBlocks.sieve.getDefaultState()))
                .where('F', frames(Materials.Steel))
                .where('C', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                        .setMinGlobalLimited(42)
                        .or(autoAbilities()))
                .where('#', air())
                .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.SOLID_STEEL_CASING;
    }

    protected static class LargeSieveWorkable extends MultiblockRecipeLogic {

        public LargeSieveWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected int[] runOverclockingLogic(@NotNull IRecipePropertyStorage propertyStorage, int recipeEUt,
                                             long maxVoltage, int duration, int amountOC) {
            return super.runOverclockingLogic(propertyStorage, recipeEUt, maxVoltage, duration, amountOC);
        }

        @NotNull
        @Override
        public ParallelLogicType getParallelLogicType() {
            return ParallelLogicType.MULTIPLY;
        }

        @Override
        public int getParallelLimit() {
            return 2 * GTUtility.getTierByVoltage(getMaxVoltage());
        }
    }
}
