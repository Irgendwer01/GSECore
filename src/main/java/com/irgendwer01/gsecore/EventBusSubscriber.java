package com.irgendwer01.gsecore;

import static gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

import exnihilocreatio.ModFluids;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

@Mod.EventBusSubscriber
public class EventBusSubscriber {

    // Ore prefixes
    public static OrePrefix oreChunk;
    public static OrePrefix oreEnderChunk;
    public static OrePrefix oreNetherChunk;

    // Icon Types
    public static MaterialIconType oreChunkIcon;
    public static MaterialIconType oreEnderChunkIcon;
    public static MaterialIconType oreNetherChunkIcon;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerMaterials(MaterialEvent event) {
        Materials.Glowstone.getProperties().setProperty(PropertyKey.ORE, new OreProperty(4, 1, true));
        oreChunkIcon = new MaterialIconType("oreChunk");
        oreEnderChunkIcon = new MaterialIconType("oreEnderChunk");
        oreNetherChunkIcon = new MaterialIconType("oreNetherChunk");

        oreChunk = new OrePrefix("oreChunk", -1, null, oreChunkIcon, ENABLE_UNIFICATION, hasOreProperty);
        oreEnderChunk = new OrePrefix("oreEnderChunk", -1, null, oreEnderChunkIcon, ENABLE_UNIFICATION, hasOreProperty);
        oreNetherChunk = new OrePrefix("oreNetherChunk", -1, null, oreNetherChunkIcon, ENABLE_UNIFICATION,
                hasOreProperty);

        oreChunk.setAlternativeOreName(OrePrefix.ore.name());
        oreEnderChunk.setAlternativeOreName(OrePrefix.oreEndstone.name());
        oreNetherChunk.setAlternativeOreName(OrePrefix.oreNetherrack.name());

        MetaItems.addOrePrefix(oreChunk, oreEnderChunk, oreNetherChunk);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void harvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().equals(Blocks.END_PORTAL_FRAME.getDefaultState())) {
            event.getDrops().add(new ItemStack(Blocks.END_PORTAL_FRAME));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void stoneGen(BlockEvent.FluidPlaceBlockEvent event) {
        if (event.getOriginalState().getBlock() == Blocks.FLOWING_LAVA) {
            BlockPos lavaPos = event.getLiquidPos(), witchWaterPos = null;
            World world = event.getWorld();
            for (BlockPos pos : new BlockPos[] { lavaPos.east(), lavaPos.west(), lavaPos.north(), lavaPos.south() })
                if (world.getBlockState(pos).getBlock() == ModFluids.blockWitchwater || world.getBlockState(pos).getBlock() == Blocks.FLOWING_WATER) {
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

    //Overworld as spectator only (if enabled)
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
}
