package com.irgendwer01.gsecore.mixins.vic;

import com.bartz24.voidislandcontrol.AdminCommand;
import com.irgendwer01.gsecore.world.WorldTypeStoneblock;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AdminCommand.class)
public class AdminCommandMixin {

    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/WorldInfo;getTerrainType()Lnet/minecraft/world/WorldType;"))
    public WorldType getTerrainType(WorldInfo instance) {
        if (instance.getTerrainType() instanceof WorldTypeStoneblock) {
            return WorldType.byName("voidworld");
        }
        return instance.getTerrainType();
    }
}
