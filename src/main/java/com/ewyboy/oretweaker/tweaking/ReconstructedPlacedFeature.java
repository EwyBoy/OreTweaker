package com.ewyboy.oretweaker.tweaking;

import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.LinkedList;
import java.util.List;

public class ReconstructedPlacedFeature {

    public static Holder<PlacedFeature> reconstructPlaced(Holder<ConfiguredFeature<OreConfiguration, ?>> configuredFeature, Block ore, String fillerName, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, Distribution distribution, boolean isDeepslate) {
        ModLogger.debug("Reconstructing placed feature: " + ore);

        String registryName = ReconstructedFeature.createUniqueRegistryName(ore, fillerName, minY, maxY, spawnRate, maxVeinSize, discardChanceOnAirExposure, distribution.name().toLowerCase(), isDeepslate, "placed");

        ModLogger.debug("Placed feature Name :: " + registryName);

        return PlacementUtils.register(
                registryName,
                configuredFeature,
                buildPlacementModifiers(
                        spawnRate,
                        minY, maxY,
                        distribution
                )
        );
    }

    private static List<PlacementModifier> buildPlacementModifiers(float spawnRate, int minY, int maxY, Distribution distribution) {
        List<PlacementModifier> placementBuilder = new LinkedList<>();

        placementBuilder.add(spawnRate < 1 ? RarityFilter.onAverageOnceEvery((int) (1 / spawnRate)) : CountPlacement.of((int) spawnRate));
        placementBuilder.add(InSquarePlacement.spread());

        switch (distribution) {
            case TRIANGLE -> placementBuilder.add(HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)));
            case UNIFORM -> placementBuilder.add(HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)));
            case NONE -> {}
        }

        placementBuilder.add(BiomeFilter.biome());

        return placementBuilder;
    }

}
