package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFiltering;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import static net.minecraft.data.worldgen.features.OreFeatures.DEEPSLATE_ORE_REPLACEABLES;

public class OreReconstruction {

    public static final List<PlacedFeature> reconstructedOres = new LinkedList<>();

    public static void reconstruct(IEventBus forgeBus) {
        forgeBus.addListener(EventPriority.LOWEST, OreReconstruction :: BiomeLoadingEvent);
    }

    private static final Map<PlacedFeature, BiomeFilters> biomeFilterMap = new HashMap<>();

    private static void reconstructFeatureFromJSON() {
        List<OreEntry> ores = JSONHandler.oreConfig.getOreConfig();
        for (OreEntry ore : ores) {
            if (isReconstructableObject(ore)) {
                try {
                    for (String replace : ore.getFillers()) {
                        ConfiguredFeature<?, ?> reconstructedOre = reconstructFeature(
                                ore,
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                                replace.toLowerCase(),
                                ore.getMinY(),
                                ore.getMaxY(),
                                ore.getSpawnRate(),
                                ore.getMaxVeinSize(),
                                ore.getDiscardChanceOnAirExposure(),
                                false
                        );
                        PlacedFeature placedFeature = reconstructPlaced(reconstructedOre, Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(replace.toLowerCase()))),
                                ore.getMinY(),
                                ore.getMaxY(),
                                ore.getSpawnRate(),
                                ore.getMaxVeinSize(),
                                ore.getDistribution(),
                                false
                        );

                        biomeFilterMap.put(placedFeature, ore.getBiomeFilter());
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

    private static ConfiguredFeature<?, ?> reconstructFeature(OreEntry entry, Block ore, String filler, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, boolean isDeepslate) {
        ModLogger.debug("Reconstructing ore: " + ore);

        String fillerName;
        Block fillerBlock = null;
        Tag<Block> fillerTag = null;

        if (filler.contains(":")) {
            fillerBlock = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(filler)));
            fillerName = Objects.requireNonNull(fillerBlock.getRegistryName()).getPath();
        } else if (!filler.isEmpty()) {
            fillerTag = BlockTags.bind(filler);
            fillerName = filler;
        } else {
            return null;
        }

        String registryName = String.format("%s_%s_%s_%s_%s_%s_feature",
                Objects.requireNonNull(ore.getRegistryName()).getPath(),
                fillerName,
                minY,
                maxY,
                spawnRate,
                maxVeinSize
        );

        ModLogger.debug("Registry Name: " + registryName);

        if (fillerBlock != null) {
            if (hasDeepslateVariant(entry)) {
                return register(registryName, reconstructConfiguredFeature(
                        ore,
                        getDeepslateVariant(entry),
                        fillerBlock,
                        maxVeinSize,
                        discardChanceOnAirExposure
                ));
            } else {
                return register(registryName, reconstructConfiguredFeature(
                        ore,
                        fillerBlock,
                        maxVeinSize,
                        discardChanceOnAirExposure
                ));
            }
        }

        if (fillerTag != null) {
            if (hasDeepslateVariant(entry)) {
                return register(registryName, reconstructConfiguredFeature(
                        ore,
                        getDeepslateVariant(entry),
                        fillerTag,
                        maxVeinSize,
                        discardChanceOnAirExposure
                ));
            } else {
                return register(registryName, reconstructConfiguredFeature(
                        ore,
                        fillerTag,
                        maxVeinSize,
                        discardChanceOnAirExposure
                ));
            }
        }

        return null;
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

    private static boolean hasDeepslateVariant(OreEntry entry) {
        if (Settings.SETTINGS.autoGenerateDeepslateVariants.get()) {
            return ForgeRegistries.BLOCKS.getValue(formatDeepslate(entry)) != null || ForgeRegistries.BLOCKS.getValue(formatDeepslate(entry)) != Blocks.AIR;
        }
        return false;
    }

    private static ResourceLocation formatDeepslate(OreEntry entry) {
        String[] ore_split = entry.getOre().split(":");
        String domain = ore_split[0];
        String name = ore_split[1];
        name = "deepslate_" + name;
        String newResourceLocation = domain + ":" + name;
        ModLogger.debug("Deepslate :: " + newResourceLocation);

        return new ResourceLocation(newResourceLocation);
    }

    private static Block getDeepslateVariant(OreEntry entry) {
        Block deepslate_ore = ForgeRegistries.BLOCKS.getValue(formatDeepslate(entry));
        ModLogger.debug("Deepslate Ore: " + Objects.requireNonNull(deepslate_ore).getRegistryName() + " : " + deepslate_ore.getName());

        return deepslate_ore;
    }

    private static ConfiguredFeature<?, ?> reconstructConfiguredFeature(Block ore, Block deepslate_ore, Block fillerTag, int maxVeinSize, float discardChanceOnAirExposure) {
        List<OreConfiguration.TargetBlockState> target = List.of(OreConfiguration.target(new BlockMatchTest(fillerTag), ore.defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, deepslate_ore.defaultBlockState()));
        return Feature.ORE.configured(new OreConfiguration(target, maxVeinSize, discardChanceOnAirExposure));
    }

    private static ConfiguredFeature<?, ?> reconstructConfiguredFeature(Block ore, Block deepslate_ore, Tag<Block> fillerTag, int maxVeinSize, float discardChanceOnAirExposure) {
        List<OreConfiguration.TargetBlockState> target = List.of(OreConfiguration.target(new TagMatchTest(fillerTag), ore.defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, deepslate_ore.defaultBlockState()));
        return Feature.ORE.configured(new OreConfiguration(target, maxVeinSize, discardChanceOnAirExposure));
    }

    private static ConfiguredFeature<?, ?> reconstructConfiguredFeature(Block ore, Block filler, int maxVeinSize, float discardChanceOnAirExposure) {
        return Feature.ORE.configured(new OreConfiguration(new BlockMatchTest(filler), ore.defaultBlockState(), maxVeinSize, discardChanceOnAirExposure));
    }

    private static ConfiguredFeature<?, ?> reconstructConfiguredFeature(Block ore, Tag<Block> filler, int maxVeinSize, float discardChanceOnAirExposure) {
        return Feature.ORE.configured(new OreConfiguration(new TagMatchTest(filler), ore.defaultBlockState(), maxVeinSize, discardChanceOnAirExposure));
    }

    private static PlacedFeature reconstructPlacedFeature(ConfiguredFeature<?, ?> configuredFeature, float spawnRate, int minY, int maxY, Distribution distribution) {
        List<PlacementModifier> placementBuilder = new LinkedList<>();

        placementBuilder.add(spawnRate < 1 ? RarityFilter.onAverageOnceEvery((int) (1 / spawnRate)) : CountPlacement.of((int) spawnRate));
        placementBuilder.add(InSquarePlacement.spread());

        switch (distribution) {
            case TRIANGLE -> placementBuilder.add(HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)));
            case UNIFORM -> placementBuilder.add(HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)));
            case NONE -> {}
        }

        placementBuilder.add(BiomeFilter.biome());

        return configuredFeature.placed(placementBuilder);
    }

    private static <FeatureConfig extends FeatureConfiguration> ConfiguredFeature<FeatureConfig, ?> register(String name, ConfiguredFeature<FeatureConfig, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
    }
    private static PlacedFeature register(String name, PlacedFeature placedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), placedFeature);
    }

    private static boolean isReconstructableObject(OreEntry entry) {
        return entry.getFillers() != null && entry.getMinY() != -1 && entry.getMaxY() != -1 && entry.getSpawnRate() != -1 && entry.getMaxVeinSize() != -1 && entry.getBiomeFilter().getBiomeBlacklist() != null && entry.getBiomeFilter().getBiomeWhitelist() != null;
    }

    private static List<String> setBiomeFiltering(List<String> blackList, List<String> whiteList) {
        return whiteList.isEmpty() ? blackList.isEmpty() ? Collections.emptyList() : blackList : whiteList;
    }

    private static BiomeFiltering getBiomeFilteringOption(List<String> blackList, List<String> whiteList) {
        return whiteList.isEmpty() ? blackList.isEmpty() ? BiomeFiltering.NONE : BiomeFiltering.BLACKLIST : BiomeFiltering.WHITELIST;
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
    private static final Map<PlacedFeature, BiomeFiltering> biomeFilteringOptionMap = new HashMap<>();

    private static void filterBiomes() {
        for (PlacedFeature reconstructedOre : reconstructedOres) {
            biomeFilteringMap.put(reconstructedOre, applyBiomeDictionaryFiltering(
                    setBiomeFiltering(
                            biomeFilterMap.get(reconstructedOre).getBiomeBlacklist(),
                            biomeFilterMap.get(reconstructedOre).getBiomeWhitelist()
                    )
            ));
            biomeFilteringOptionMap.put(reconstructedOre, getBiomeFilteringOption(
                    biomeFilterMap.get(reconstructedOre).getBiomeBlacklist(),
                    biomeFilterMap.get(reconstructedOre).getBiomeWhitelist()
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

    private static void filterGeneration(String currentBiome, List<String> biomeFilter, BiomeFiltering filteringOption, BiomeGenerationSettingsBuilder generation, PlacedFeature reconstructedOre) {
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