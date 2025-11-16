package com.irgendwer01.gsecore;

import net.minecraft.block.BlockStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.irgendwer01.gsecore.recipes.SieveRecipes;

import exnihilocreatio.ModFluids;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.registry.MTEManager;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;

@Mod.EventBusSubscriber
public class EventBusSubscriber {

    @SubscribeEvent
    public static void registerMTERegistry(MTEManager.MTERegistryEvent event) {
        GregTechAPI.mteManager.createRegistry(Tags.MODID);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerMaterials(MaterialEvent event) {
        Materials.Glowstone.getProperties().setProperty(PropertyKey.ORE, new OreProperty(4, 1, true));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void harvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
        if (GSEConfig.breakableEndPortal && event.getState().equals(Blocks.END_PORTAL_FRAME.getDefaultState())) {
            event.getDrops().add(new ItemStack(Blocks.END_PORTAL_FRAME));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void stoneGen(BlockEvent.FluidPlaceBlockEvent event) {
        if (!GSEConfig.customStoneGen) {
            return;
        }
        if (event.getOriginalState().getBlock() == Blocks.LAVA || event.getOriginalState().getBlock() == Blocks.FLOWING_LAVA) {
            BlockPos lavaPos = event.getLiquidPos(), witchWaterPos = null;
            World world = event.getWorld();
            for (BlockPos pos : new BlockPos[] { lavaPos.east(), lavaPos.west(), lavaPos.north(), lavaPos.south() })
                if (world.getBlockState(pos).getBlock() == ModFluids.blockWitchwater) {
                            witchWaterPos = pos;
                            break;
                        }
            if (witchWaterPos != null) {
                switch (world.getBlockState(witchWaterPos).getBlock()
                        .getMetaFromState(world.getBlockState(witchWaterPos))) {
                    case 1:
                        switch (event.getWorld().rand.nextInt(5)) {
                            case 1:
                                event.setNewState(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT,
                                        BlockStone.EnumType.DIORITE));
                                break;
                            case 2:
                                event.setNewState(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT,
                                        BlockStone.EnumType.GRANITE));
                                break;
                            case 3:
                                event.setNewState(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT,
                                        BlockStone.EnumType.ANDESITE));
                                break;
                            case 4:
                                event.setNewState(Blocks.STONE.getDefaultState());
                                break;
                        }
                        break;
                    case 2:
                        event.setNewState(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT,
                                BlockStone.EnumType.DIORITE));
                        break;
                    case 3:
                        event.setNewState(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT,
                                BlockStone.EnumType.GRANITE));
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        event.setNewState(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT,
                                BlockStone.EnumType.ANDESITE));
                        break;
                }
            }
        }
    }

    // Overworld as spectator only (if enabled)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onDimensionEnter(EntityJoinWorldEvent event) {
        if (GSEConfig.owspectatoronly && event.getEntity() instanceof EntityPlayer player) {
            if (event.getWorld().provider.getDimension() == 0) {
                player.setGameType(GameType.ADVENTURE);
            } else if (!player.isAllowEdit()) {
                player.setGameType(GameType.SURVIVAL);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (GSEConfig.gcylDrops) {
            SieveRecipes.init();
        }
    }
}
