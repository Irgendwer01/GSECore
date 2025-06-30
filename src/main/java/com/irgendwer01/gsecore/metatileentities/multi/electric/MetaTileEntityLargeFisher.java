package com.irgendwer01.gsecore.metatileentities.multi.electric;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.irgendwer01.gsecore.recipes.GTRecipes;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;

public class MetaTileEntityLargeFisher extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeFisher(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTRecipes.FISHER_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityLargeFisher(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" CCCCC ", " CCCCC ", " CCCCC ", " CCCCC ")
                .aisle("CCCCCCC", "CGGGGGC", "CDDDDDC", "CDDDDDC")
                .aisle("CCCCCCC", "CGGGGGC", "CDDDDDC", "CDDDDDC")
                .aisle("CCCCCCC", "CGGGGGC", "CDDDDDC", "CDDDDDC")
                .aisle("CCCCCCC", "CGGGGGC", "CDDDDDC", "CDDDDDC")
                .aisle(" CCCCC ", " CCSCC ", " CCCCC ", " CCCCC ")
                .where('S', selfPredicate())
                .where('G',
                        states(MetaBlocks.MULTIBLOCK_CASING
                                .getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('D', states(Blocks.WATER.getDefaultState()))
                .where('C',
                        states(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                                .getState(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING))
                                        .or(autoAbilities()))
                .where('#', air())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.VIBRATION_SAFE_CASING;
    }
}
