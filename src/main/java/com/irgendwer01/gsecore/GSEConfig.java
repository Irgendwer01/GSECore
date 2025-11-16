package com.irgendwer01.gsecore;

import net.minecraftforge.common.config.Config;

import com.cleanroommc.configanytime.ConfigAnytime;

@Config(modid = Tags.MODID)
public class GSEConfig {

    @Config.Comment("Sets the player's gamemode automatically to Spectator if they're in the overworld")
    public static boolean owspectatoronly = false;

    @Config.Comment("Gives Large Boilers 5x more Efficiency")
    public static boolean largeBoilerHigherEfficiency = false;

    @Config.Comment("Makes End Portal Frames breakable")
    public static boolean breakableEndPortal = false;

    @Config.Comment("Allows for an Stonegen that uses Witchwater + Lava and generates a different Stone depending on the flow, needs Ex Nihilo installed")
    public static boolean customStoneGen = false;

    @Config.Comment("Modifies the Industrial Steam Engine's structure & recipe to be harder")
    public static boolean harderSteamEngine = false;

    @Config.Comment("Makes the Openblocks block breaker only break blocks if there's an inventory behind it")
    public static boolean blockBreakerNeedsInventory = false;

    @Config.Comment("Enables the Large Steam (Forge) Hammer")
    public static boolean enableLSH = false;

    @Config.Comment("Allows the Gas Collector to work in Personal Worlds")
    public static boolean gasCollectorPW = false;

    @Config.Comment("Adds GCYL drops to the Ex Nihilo sifting table and some extra additions")
    public static boolean gcylDrops = false;

    static {
        ConfigAnytime.register(GSEConfig.class);
    }
}
