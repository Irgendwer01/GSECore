package com.irgendwer01.gsecore.mixins.vic;

import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.bartz24.voidislandcontrol.ClientEventHandler;
import com.irgendwer01.gsecore.world.WorldTypeStoneblock;

@Mixin(ClientEventHandler.class)
public class ClientEventHandlerMixin {

    @Redirect(method = "playerUpdate",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/storage/WorldInfo;getTerrainType()Lnet/minecraft/world/WorldType;"))
    public WorldType getTerrainType(WorldInfo instance) {
        if (instance.getTerrainType() instanceof WorldTypeStoneblock) {
            return WorldType.byName("voidworld");
        }
        return instance.getTerrainType();
    }
}
