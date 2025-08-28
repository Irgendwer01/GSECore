package com.irgendwer01.gsecore;

import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MODID)
public class GSEConfig {

    @Config.Comment("Sets the player's gamemode automatically to Spectator if they're in the overworld")
    public static boolean owspectatoronly = false;
}
