package com.irgendwer01.gsecore.mixins.gregicality;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import gregicality.multiblocks.common.metatileentities.multiblock.generator.MetaTileEntitySteamEngine;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;

@Mixin(value = MetaTileEntitySteamEngine.class, remap = false)
public abstract class MixinMetaTileEntitySteamEngine extends FuelMultiblockController {

    public MixinMetaTileEntitySteamEngine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.STEAM_TURBINE_FUELS, GTValues.MV);
    }


    /**
     * @author Irgendwer01
     * @reason Make Industrial Steam Engine structure a bit more thicc
     */
    @Overwrite
    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX", "XXEX", "#XXX")
                .aisle("XXXX", "XXGX", "XXMX")
                .aisle("#XXX", "XXGX", "#XXX")
                .aisle("#XXX", "#XSX", "#XXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(30)
                        .or(autoAbilities(false, true, true, true, true, true, false)))
                .where('E', energyOutputPredicate())
                .where('G', states(getCasingState2()))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('#', any())
                .build();
    }


    /**
     * @author Irgendwer01
     * @reason Change tooltip accordingly to the changes
     */
    @Overwrite
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcym.machine.steam_engine.tooltip.1", GTValues.VNF[GTValues.EV]));
    }

    @Shadow
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return null;
    }

    @Shadow
    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return null;
    }

    @Shadow
    private static TraceabilityPredicate energyOutputPredicate() {
        return null;
    }

    @Shadow
    private static IBlockState getCasingState() {
        return null;
    }

    @Shadow
    private static IBlockState getCasingState2() {
        return null;
    }
}
