package com.irgendwer01.gsecore.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.*;

import net.minecraft.util.ResourceLocation;

import com.irgendwer01.gsecore.metatileentities.electric.MetaTileEntitySieve;
import com.irgendwer01.gsecore.metatileentities.multi.electric.MetaTileEntityLargeSieveMultiblock;
import com.irgendwer01.gsecore.metatileentities.multi.parts.GSEMetaTileEntityEnergyHatch;
import com.irgendwer01.gsecore.metatileentities.steam.MetaTileEntitySteamSieve;

import gregtech.api.GTValues;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;

public class MetaTileEntities {

    // Machines
    public static MetaTileEntitySteamSieve STEAM_SIEVE_BRONZE;
    public static MetaTileEntitySteamSieve STEAM_SIEVE_STEEL;
    public static MetaTileEntitySieve[] SIEVES = new MetaTileEntitySieve[GTValues.V.length - 1];
    public static MetaTileEntityLargeSieveMultiblock LARGE_SIEVE;

    public static MetaTileEntityEnergyHatch[] ENERGY_OUTPUT_HATCH_4A = new MetaTileEntityEnergyHatch[2]; // LV-MV
    public static MetaTileEntityEnergyHatch[] ENERGY_OUTPUT_HATCH_16A = new MetaTileEntityEnergyHatch[2]; // LV-MV

    public static void registerMetaTileEntities() {
        STEAM_SIEVE_BRONZE = registerMetaTileEntity(4000,
                new MetaTileEntitySteamSieve(new ResourceLocation(GTValues.MODID, "sieve.steam"), false));
        STEAM_SIEVE_STEEL = registerMetaTileEntity(4001,
                new MetaTileEntitySteamSieve(new ResourceLocation(GTValues.MODID, "steam_sieve_steel"), true));

        SIEVES[0] = registerMetaTileEntity(4002,
                new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.lv"), 1));
        SIEVES[1] = registerMetaTileEntity(4003,
                new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.mv"), 2));
        SIEVES[2] = registerMetaTileEntity(4004,
                new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.hv"), 3));
        SIEVES[3] = registerMetaTileEntity(4005,
                new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.ev"), 4));
        SIEVES[4] = registerMetaTileEntity(4006,
                new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.iv"), 5));

        if (getMidTier("sieve")) {
            SIEVES[5] = registerMetaTileEntity(4007,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.luv"), 6));
            SIEVES[6] = registerMetaTileEntity(4008,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.zpm"), 7));
            SIEVES[7] = registerMetaTileEntity(4009,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.uv"), 8));
        }

        if (getHighTier("sieve")) {
            SIEVES[8] = registerMetaTileEntity(4010,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.uhv"), 9));
            SIEVES[9] = registerMetaTileEntity(4011,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.uev"), 10));
            SIEVES[10] = registerMetaTileEntity(4012,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.uiv"), 11));
            SIEVES[11] = registerMetaTileEntity(4013,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.uxv"), 12));
            SIEVES[12] = registerMetaTileEntity(4014,
                    new MetaTileEntitySieve(new ResourceLocation(GTValues.MODID, "sieve.opv"), 13));
        }

        LARGE_SIEVE = registerMetaTileEntity(4015,
                new MetaTileEntityLargeSieveMultiblock(new ResourceLocation(GTValues.MODID, "large_sieve")));

        ENERGY_OUTPUT_HATCH_4A[0] = registerMetaTileEntity(4016,
                new GSEMetaTileEntityEnergyHatch(new ResourceLocation(GTValues.MODID, "energy_hatch.output_4a.lv"), 1,
                        4, true));
        ENERGY_OUTPUT_HATCH_16A[0] = registerMetaTileEntity(4017,
                new GSEMetaTileEntityEnergyHatch(new ResourceLocation(GTValues.MODID, "energy_hatch.output_16a.lv"), 1,
                        16, true));
        ENERGY_OUTPUT_HATCH_4A[1] = registerMetaTileEntity(4018,
                new GSEMetaTileEntityEnergyHatch(new ResourceLocation(GTValues.MODID, "energy_hatch.output_4a.mv"), 2,
                        4, true));
        ENERGY_OUTPUT_HATCH_16A[1] = registerMetaTileEntity(4019,
                new GSEMetaTileEntityEnergyHatch(new ResourceLocation(GTValues.MODID, "energy_hatch.output_16a.mv"), 2,
                        16, true));
    }
}
