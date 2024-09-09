package com.irgendwer01.gsecore.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;

import com.bartz24.voidislandcontrol.config.ConfigOptions;

public class WorldTypeStoneblock extends WorldType {

    public WorldTypeStoneblock() {
        super("stoneblock");
    }

    public boolean hasInfoNotice() {
        return true;
    }

    @Override
    public int getMinimumSpawnHeight(World world) {
        return ConfigOptions.islandSettings.islandYLevel;
    }

    public int getSpawnFuzz() {
        return 2;
    }

    @Override
    public float getCloudHeight() {
        return ConfigOptions.worldGenSettings.cloudLevel;
    }

    @Override
    public double getHorizon(World world) {
        return ConfigOptions.worldGenSettings.horizonLevel;
    }

    public BiomeProvider getBiomeProvider(World world) {
        if (ConfigOptions.worldGenSettings.worldBiomeID > -1) {
            return new BiomeProviderSingle(Biome.getBiome(ConfigOptions.worldGenSettings.worldBiomeID));
        } else {
            return new BiomeProvider(world.getWorldInfo());
        }
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        ChunkGeneratorFlat provider = new ChunkGeneratorFlat(world, world.getSeed(), false, "3;255*minecraft:stone");
        world.setSeaLevel(63);
        return provider;
    }

    public boolean handleSlimeSpawnReduction(java.util.Random random, World world) {
        return super.handleSlimeSpawnReduction(random, world);
    }
}
