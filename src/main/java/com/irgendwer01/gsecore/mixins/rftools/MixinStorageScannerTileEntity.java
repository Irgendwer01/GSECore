package com.irgendwer01.gsecore.mixins.rftools;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import org.cyclops.integrateddynamics.core.tileentity.TileCableConnectable;
import org.cyclops.integrateddynamics.core.tileentity.TileCableConnectableInventory;
import org.cyclops.integrateddynamics.core.tileentity.TileMultipartTicking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.irgendwer01.gsecore.GSECoreMod;
import com.llamalad7.mixinextras.sugar.Local;

import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.common.metatileentities.storage.MetaTileEntityCrate;
import gregtech.common.metatileentities.storage.MetaTileEntityQuantumChest;
import gregtech.common.pipelike.itempipe.tile.TileEntityItemPipe;
import mcjty.rftools.blocks.storagemonitor.StorageScannerTileEntity;
import openblocks.common.tileentity.TileEntityBlockBreaker;
import openblocks.common.tileentity.TileEntityBlockPlacer;

@Mixin(StorageScannerTileEntity.class)
public class MixinStorageScannerTileEntity {

    @Redirect(method = "inventoryAddNew",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;getBoolean(Ljava/lang/String;)Z"))
    private boolean redirect(NBTTagCompound instance, String key, @Local(name = "te") TileEntity te) {
        if (te instanceof MetaTileEntityHolder metaTileEntityHolder) {
            return !(metaTileEntityHolder.getMetaTileEntity() instanceof MetaTileEntityCrate) ||
                    !(metaTileEntityHolder.getMetaTileEntity() instanceof MetaTileEntityQuantumChest);
        }
        if (te instanceof TileEntityItemPipe) {
            return true;
        }
        if (GSECoreMod.isIDLoaded) {
            if (te instanceof TileCableConnectable || te instanceof TileCableConnectableInventory ||
                    te instanceof TileMultipartTicking) {
                return true;
            }
        }
        if (GSECoreMod.isOpenBlocksLoaded) {
            if (te instanceof TileEntityBlockBreaker || te instanceof TileEntityBlockPlacer) {
                return true;
            }
        }
        return instance.getBoolean(key);
    }
}
