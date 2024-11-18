package com.irgendwer01.gsecore;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.irgendwer01.gsecore.items.ItemGTWand;
import com.irgendwer01.gsecore.items.Pebbles;
import com.irgendwer01.gsecore.metatileentities.MetaTileEntities;
import com.irgendwer01.gsecore.recipes.CraftingRecipes;
import com.irgendwer01.gsecore.recipes.ExNihiloRecipes;
import com.irgendwer01.gsecore.recipes.GTRecipes;
import com.irgendwer01.gsecore.world.WorldTypeStoneblock;

import gregtech.api.GTValues;
import gregtech.api.items.toolitem.IGTTool;
import gregtech.common.items.ToolItems;
import zone.rong.mixinbooter.ILateMixinLoader;

import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE;

@Mod(modid = Tags.MODID,
     name = Tags.MODNAME,
     version = Tags.VERSION,
     dependencies = "required-after:gregtech;" +
             "required-after:exnihilocreatio;")
public class GSECoreMod implements ILateMixinLoader {

    public static final Logger logger = LogManager.getLogger("GSECore");
    public static Pebbles GTPebbles;
    public static IGTTool WAND;

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
        WAND = ToolItems.register(ItemGTWand.Builder.of(GTValues.MODID, "wand")
                .toolStats(w -> w.cannotAttack()
                        .damagePerAction(1)
                        .baseDurability(250))
                .toolClasses("wand")
                .oreDict("toolWand"));
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
