package com.irgendwer01.gsecore.mixins.vic;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.bartz24.voidislandcontrol.EventHandler;
import com.bartz24.voidislandcontrol.api.IslandManager;
import com.irgendwer01.gsecore.world.WorldTypeStoneblock;

@Mixin(value = EventHandler.class)
public class EventHandlerMixin {

    @Inject(method = "createSpawn", at = @At("HEAD"), cancellable = true, remap = false)
    private static void createSpawn(EntityPlayer player, World world, BlockPos spawn, CallbackInfo ci) {
        if (world.getWorldInfo().getTerrainType() instanceof WorldTypeStoneblock) {
            spawnPlat(null, world, spawn, IslandManager.getIndexOfIslandType("stoneblock"));
            ci.cancel();
        }
    }

    @Shadow(remap = false)
    private static void spawnPlat(@Nullable EntityPlayer player, World world, BlockPos spawn, int type) {}

    @Inject(method = "spawnPlat", at = @At("HEAD"), remap = false)
    private static void spawnPlat(EntityPlayer player, World world, BlockPos spawn, int type, CallbackInfo ci) {
        if (world.getWorldInfo().getTerrainType() instanceof WorldTypeStoneblock) {
            type = IslandManager.getIndexOfIslandType("stoneblock");
        }
    }

    @Redirect(method = "playerLogin",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/storage/WorldInfo;getTerrainType()Lnet/minecraft/world/WorldType;"))
    public WorldType getTerrainTypePlayerLogin(WorldInfo instance) {
        if (instance.getTerrainType() instanceof WorldTypeStoneblock) {
            return WorldType.byName("voidworld");
        }
        return instance.getTerrainType();
    }

    @Redirect(method = "playerUpdate",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/storage/WorldInfo;getTerrainType()Lnet/minecraft/world/WorldType;"))
    public WorldType getTerrainTypePlayerUpdate(WorldInfo instance) {
        if (instance.getTerrainType() instanceof WorldTypeStoneblock) {
            return WorldType.byName("voidworld");
        }
        return instance.getTerrainType();
    }

    @Redirect(method = "onPlayerRespawn",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/storage/WorldInfo;getTerrainType()Lnet/minecraft/world/WorldType;"))
    public WorldType getTerrainTypeOnPlayerRespawn(WorldInfo instance) {
        if (instance.getTerrainType() instanceof WorldTypeStoneblock) {
            return WorldType.byName("voidworld");
        }
        return instance.getTerrainType();
    }
}
