package com.irgendwer01.gsecore.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.fulltrix.gcyl.materials.GCYLMaterials;

import exnihilocreatio.ModBlocks;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.integration.exnihilo.ExNihiloModule;
import gregtech.integration.exnihilo.recipes.SieveDrops;

public class SieveRecipes {

    public static void init() {
        SieveDrops.removeDrop(new ItemStack(ModBlocks.netherrackCrushed),
                OreDictUnifier.get(ExNihiloModule.oreNetherChunk, Materials.Gold));
        SieveDrops.removeDrop(new ItemStack(ModBlocks.endstoneCrushed),
                OreDictUnifier.get(ExNihiloModule.oreEnderChunk, Materials.Gold));
        SieveDrops.removeDrop(new ItemStack(ModBlocks.crushedGranite),
                OreDictUnifier.get(ExNihiloModule.oreChunk, Materials.Gold));
        SieveDrops.removeDrop(new ItemStack(ModBlocks.endstoneCrushed),
                OreDictUnifier.get(ExNihiloModule.oreEnderChunk, Materials.Platinum));

        SieveDrops.addDrop(ModBlocks.crushedGranite,
                OreDictUnifier.get(ExNihiloModule.oreChunk, GCYLMaterials.PreciousMetal), 2, 0.12F);
        SieveDrops.addDrop(ModBlocks.endstoneCrushed,
                OreDictUnifier.get(ExNihiloModule.oreEnderChunk, GCYLMaterials.PlatinumMetallicPowder), 4, 0.005F);
        SieveDrops.addDrop(ModBlocks.netherrackCrushed,
                OreDictUnifier.get(ExNihiloModule.oreNetherChunk, Materials.Glowstone), 3, 0.11F);
        SieveDrops.addDrop("sand", new ItemStack(Items.PRISMARINE_CRYSTALS), 4, 0.1F);
        SieveDrops.addDrop("sand", new ItemStack(Items.PRISMARINE_SHARD), 4, 0.1F);
        SieveDrops.addDrop(ModBlocks.endstoneCrushed, new ItemStack(Items.CHORUS_FRUIT_POPPED), 4, 0.1F);
    }
}
