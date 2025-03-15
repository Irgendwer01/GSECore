package com.irgendwer01.gsecore.metatileentities.multi.parts;

import com.irgendwer01.gsecore.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class GSEMetaTileEntityEnergyHatch extends MetaTileEntityEnergyHatch {

    public GSEMetaTileEntityEnergyHatch(ResourceLocation metaTileEntityId, int tier, int amperage, boolean isExportHatch) {
        super(metaTileEntityId, tier, amperage, isExportHatch);
    }

    //Fix hatches not showing up in JEI
    @Override
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems) {
        for (MetaTileEntityEnergyHatch hatch : MetaTileEntities.ENERGY_OUTPUT_HATCH_4A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }

        for (MetaTileEntityEnergyHatch hatch : MetaTileEntities.ENERGY_OUTPUT_HATCH_16A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
    }
}