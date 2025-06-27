package com.irgendwer01.gsecore;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.irgendwer01.gsecore.items.Pebbles;
import com.irgendwer01.gsecore.metatileentities.MetaTileEntities;
import com.irgendwer01.gsecore.recipes.CraftingRecipes;
import com.irgendwer01.gsecore.recipes.ExNihiloRecipes;
import com.irgendwer01.gsecore.recipes.GTRecipes;
import com.irgendwer01.gsecore.world.WorldTypeStoneblock;

import zone.rong.mixinbooter.ILateMixinLoader;

@Mod(modid = Tags.MODID,
     name = Tags.MODNAME,
     version = Tags.VERSION,
     dependencies = "required-after:gregtech;" +
             "required-after:exnihilocreatio;")
public class GSECoreMod implements ILateMixinLoader {

    public static final Logger logger = LogManager.getLogger("GSECore");
    public static Pebbles GTPebbles;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        new WorldTypeStoneblock();
        GTPebbles = new Pebbles();
        MetaTileEntities.registerMetaTileEntities();
        File file = new File(Loader.instance().getConfigDir() + "/gregtech/sieve_drops.json");
        if (!file.exists()) {
            InputStream inputStream = this.getClass().getResourceAsStream("/assets/gsecore/exnihilo/sieve_drops.json");
            try {
                Files.copy(inputStream, file.getAbsoluteFile().toPath());
                inputStream.close();
            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ExNihiloRecipes.registerRecipes();
        CraftingRecipes.RegisterCraftingRecipes();
        GTRecipes.registerExNihiloRecipes();
    }

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.gsecore.json");
    }
}
