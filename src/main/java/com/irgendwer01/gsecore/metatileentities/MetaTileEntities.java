package com.irgendwer01.gsecore.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.*;

import com.irgendwer01.gsecore.GSEConfig;
import com.irgendwer01.gsecore.Tags;
import net.minecraft.util.ResourceLocation;

import com.irgendwer01.gsecore.metatileentities.multi.steam.MetaTileEntitySteamLargeHammer;


public class MetaTileEntities {

    // Machines
    public static MetaTileEntitySteamLargeHammer LARGE_STEAM_HAMMER;


    public static void registerMetaTileEntities() {

        if (GSEConfig.enableLSH) {
            LARGE_STEAM_HAMMER = registerMetaTileEntity(1,
                    new MetaTileEntitySteamLargeHammer(new ResourceLocation(Tags.MODID, "large_steam_hammer")));
        }
    }
}
