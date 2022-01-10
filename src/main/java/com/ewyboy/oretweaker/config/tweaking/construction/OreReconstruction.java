package com.ewyboy.oretweaker.config.tweaking.construction;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.objects.spawn.SpawnFilter;
import com.ewyboy.oretweaker.json.objects.spawn.SpawnFiltering;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import com.ewyboy.oretweaker.util.PlacementUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class OreReconstruction {

    public static final List<PlacedFeature> reconstructedOres = new LinkedList<>();

    public static void reconstruct(IEventBus forgeBus) {
        forgeBus.addListener(EventPriority.LOWEST, OreReconstruction :: BiomeLoadingEvent);
    }

    private static final Map<PlacedFeature, SpawnFilter> spawnFilterMap = new HashMap<>();

    private static void reconstructFeatureFromJSON() {
        List<OreEntry> ores = JSONHandler.oreConfig.getOreConfig();
        for (OreEntry ore : ores) {
            if (isReconstructableObject(ore)) {
                try {
                    for (String replace : ore.getFillers()) {
                        ConfiguredFeature<?, ?> reconstructedOre = reconstructFeature(
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(replace))),
                                ore.getMinY(),
                                ore.getMaxY(),
                                ore.getSpawnRate(),
                                ore.getMaxVeinSize(),
                                ore.getDiscardChanceOnAirExposure(),
                                false
                        );
                        PlacedFeature placedFeature = reconstructPlaced(reconstructedOre, Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(replace))),
                                ore.getMinY(),
                                ore.getMaxY(),
                                ore.getSpawnRate(),
                                ore.getMaxVeinSize(),
                                ore.getDistribution(),
                                false
                        );

                        spawnFilterMap.put(placedFeature, ore.getSpawnFilter());
                        reconstructedOres.add(placedFeature);
                    }
                } catch (Exception ignored) {
                    ModLogger.error(ore.getOre() + " can't be reconstructed from JSON..");
                }
            } else {
                ModLogger.error(ore.getOre() + " can't be reconstructed ??");
            }
        }
    }

    private static ConfiguredFeature<?, ?> reconstructFeature(Block ore, Block filler, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, boolean isDeepslate) {
        ModLogger.debug("Reconstructing ore: " + ore);
        String registryName = String.format("%s_%s_%s_%s_%s_%s_feature",
                Objects.requireNonNull(ore.getRegistryName()).getPath(),
                Objects.requireNonNull(filler.getRegistryName()).getPath(),
                minY,
                maxY,
                spawnRate,
                maxVeinSize
        );

        ModLogger.debug("Registry Name: " + registryName);

        return register(registryName, reconstructConfiguredFeature(
                ore,
                filler,
                maxVeinSize,
                discardChanceOnAirExposure
        ));
    }

    private static PlacedFeature reconstructPlaced(ConfiguredFeature<?,?> feature, Block ore, Block filler, int minY, int maxY, float spawnRate, int maxVeinSize, Distribution distribution, boolean isDeepslate) {
        ModLogger.debug("Reconstructing ore: " + ore);
        String registryName = String.format("%s_%s_%s_%s_%s_%s_placed",
                Objects.requireNonNull(ore.getRegistryName()).getPath(),
                Objects.requireNonNull(filler.getRegistryName()).getPath(),
                minY,
                maxY,
                spawnRate,
                maxVeinSize
        );

        ModLogger.debug("Registry Name: " + registryName);

        return register(registryName, reconstructPlacedFeature(
                feature,
                spawnRate,
                minY, maxY,
                distribution
        ));
    }

    private static ConfiguredFeature<?, ?> reconstructConfiguredFeature(Block ore, Block filler, int maxVeinSize, float discardChanceOnAirExposure) {
        return Feature.ORE.configured(new OreConfiguration(new BlockMatchTest(filler), ore.defaultBlockState(), maxVeinSize, discardChanceOnAirExposure));
    }

    private static PlacedFeature reconstructPlacedFeature(ConfiguredFeature<?, ?> configuredFeature, float spawnRate, int minY, int maxY, Distribution distribution) {
       return switch (distribution) {
           case COUNT -> configuredFeature.placed(PlacementUtils.orePlacement(CountPlacement.of(UniformInt.of(0, (int) spawnRate)), HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY))));
           case UNIFORM -> configuredFeature.placed(PlacementUtils.commonOrePlacement((int) spawnRate, HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY))));
           case TRIANGLE -> configuredFeature.placed(PlacementUtils.commonOrePlacement((int) spawnRate, HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY))));
           case SPREAD -> configuredFeature.placed(InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)), BiomeFilter.biome());
       };
    }

    private static <FeatureConfig extends FeatureConfiguration> ConfiguredFeature<FeatureConfig, ?> register(String name, ConfiguredFeature<FeatureConfig, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
    }
    private static PlacedFeature register(String name, PlacedFeature placedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), placedFeature);
    }

    private static boolean isReconstructableObject(OreEntry entry) {
        return entry.getFillers() != null && entry.getMinY() != -1 && entry.getMaxY() != -1 && entry.getSpawnRate() != -1 && entry.getMaxVeinSize() != -1 && entry.getSpawnFilter().getBiomeFilter().getBiomeBlacklist() != null && entry.getSpawnFilter().getBiomeFilter().getBiomeWhitelist() != null;
    }

    private static List<String> setBiomeFiltering(List<String> blackList, List<String> whiteList) {
        return whiteList.isEmpty() ? blackList.isEmpty() ? Collections.emptyList() : blackList : whiteList;
    }

    private static SpawnFiltering getBiomeFilteringOption(List<String> blackList, List<String> whiteList) {
        return whiteList.isEmpty() ? blackList.isEmpty() ? SpawnFiltering.NONE : SpawnFiltering.BLACKLIST : SpawnFiltering.WHITELIST;
    }

    private static List<String> applyBiomeDictionaryFiltering(List<String> biomeEntries) {
        List<String> biomeFilter = new ArrayList<>();
        for (String biomeEntry : biomeEntries) {
            if (biomeEntry.contains(":")) {
                biomeFilter.add(biomeEntry);
            } else {
                BiomeDictionary.Type type = BiomeDictionary.Type.getType(biomeEntry);
                Set<ResourceKey<Biome>> biomes = BiomeDictionary.getBiomes(type);
                biomes.forEach(biome -> biomeFilter.add(biome.location().toString()));
            }
        }
        return biomeFilter;
    }

    private static final Map<PlacedFeature, List<String>> biomeFilteringMap = new HashMap<>();
    private static final Map<PlacedFeature, SpawnFiltering> biomeFilteringOptionMap = new HashMap<>();

    private static void filterBiomes() {
        for (PlacedFeature reconstructedOre : reconstructedOres) {
            biomeFilteringMap.put(reconstructedOre, applyBiomeDictionaryFiltering(
                    setBiomeFiltering(
                            spawnFilterMap.get(reconstructedOre).getBiomeFilter().getBiomeBlacklist(),
                            spawnFilterMap.get(reconstructedOre).getBiomeFilter().getBiomeWhitelist()
                    )
            ));
            biomeFilteringOptionMap.put(reconstructedOre, getBiomeFilteringOption(
                    spawnFilterMap.get(reconstructedOre).getBiomeFilter().getBiomeBlacklist(),
                    spawnFilterMap.get(reconstructedOre).getBiomeFilter().getBiomeWhitelist()
            ));
        }
    }

    private static void buildBiomes(String biomeName, BiomeGenerationSettingsBuilder biomeBuilder) {
        for (PlacedFeature reconstructedOre : reconstructedOres) {
            filterGeneration(
                    biomeName,
                    biomeFilteringMap.get(reconstructedOre),
                    biomeFilteringOptionMap.get(reconstructedOre),
                    biomeBuilder,
                    reconstructedOre
            );
        }
    }

    private static Boolean reconstructed = false;
    private static Boolean filteredBiomes = false;

    public static void BiomeLoadingEvent(final BiomeLoadingEvent event) {
        if (!reconstructed) {
            reconstructFeatureFromJSON();
            reconstructed = true;
        }

        if(!filteredBiomes) {
            filterBiomes();
            filteredBiomes = true;
        }

        buildBiomes(Objects.requireNonNull(event.getName()).toString(), event.getGeneration());
    }

    private static void filterGeneration(String currentBiome, List<String> biomeFilter, SpawnFiltering filteringOption, BiomeGenerationSettingsBuilder generation, PlacedFeature reconstructedOre) {
        switch (filteringOption) {
            case WHITELIST:
                if (biomeFilter.contains(currentBiome)) generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre); break;
            case BLACKLIST:
                if (!biomeFilter.contains(currentBiome)) generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre); break;
            case NONE:
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + biomeFilter);
        }
    }

}