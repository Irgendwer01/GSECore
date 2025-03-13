package com.irgendwer01.gsecore.mixins.gregtech;

import static gregtech.api.util.GTUtility.getMetaTileEntity;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import gregtech.api.block.BlockCustomParticle;
import gregtech.api.block.machines.BlockMachine;
import gregtech.api.metatileentity.MetaTileEntity;

// Remove when new CEu version is out
@Mixin(BlockMachine.class)
public abstract class MixinBlockMachine extends BlockCustomParticle {

    public MixinBlockMachine(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @NotNull
    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getItem(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        MetaTileEntity metaTileEntity = getMetaTileEntity(world, pos);
        if (metaTileEntity == null)
            return ItemStack.EMPTY;
        return metaTileEntity.getStackForm();
    }
}
