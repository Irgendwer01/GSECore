package com.irgendwer01.gsecore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.irgendwer01.gsecore.metatileentities.MetaTileEntities;
import com.irgendwer01.gsecore.recipes.CraftingRecipes;

import zone.rong.mixinbooter.ILateMixinLoader;

@Mod(modid = Tags.MODID,
     name = Tags.MODNAME,
     version = Tags.VERSION,
     dependencies = "required-after:gregtech;")
public class GSECoreMod implements ILateMixinLoader {

    public static final Logger logger = LogManager.getLogger("GSECore");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MetaTileEntities.registerMetaTileEntities();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        CraftingRecipes.RegisterCraftingRecipes();
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>() {

            {
                if (GSEConfig.largeBoilerHigherEfficiency) {
                    add("mixins.gsecore.boiler.json");
                }
                if (GSEConfig.harderSteamEngine) {
                    add("mixins.gsecore.steamengine.json");
                }
                if (GSEConfig.blockBreakerNeedsInventory) {
                    add("mixins.gsecore.blockbreaker.json");
                }
                if (GSEConfig.gasCollectorPW) {
                    add("mixins.gsecore.gascollector.json");
                }
            }
        };
    }
}
