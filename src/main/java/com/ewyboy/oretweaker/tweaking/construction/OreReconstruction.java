package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFiltering;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
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

    public static final List<Holder<PlacedFeature>> reconstructedOres = new LinkedList<>();

    public static void reconstruct(IEventBus forgeBus) {
        forgeBus.addListener(EventPriority.LOWEST, OreReconstruction :: BiomeLoadingEvent);
    }

    private static final Map<Holder<PlacedFeature>, BiomeFilters> biomeFilterMap = new HashMap<>();

    private static void reconstructFeatureFromJSON() {
        List<OreEntry> ores = JSONHandler.oreConfig.getOreConfig();
        for (OreEntry ore : ores) {
            if (isReconstructableObject(ore)) {
                try {
                    for (String replace : ore.getFillers()) {

                        Holder<ConfiguredFeature<OreConfiguration, ?>> configuredFeature = reconstructConfigured(
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

                        Holder<PlacedFeature> placedFeature = reconstructPlaced(configuredFeature,
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                                replace.toLowerCase(),
                                ore.getMinY(),
                                ore.getMaxY(),
                                ore.getSpawnRate(),
                                ore.getMaxVeinSize(),
                                ore.getDiscardChanceOnAirExposure(),
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

    private static String makeRegistryNameUnique(String name) {
        if (registryNames.contains(name)) {
            int i = 1;
            while (registryNames.contains(name + "_" +  i)) {
                i++;
            }
            return name + "_" +  i;
        }
        return name;
    }

    private static final List<String> registryNames = new ArrayList<>();

    private static String createUniqueRegistryName(Block block, String filler, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, String distribution, boolean isDeepSlate, String featureType) {
        String registryName = isDeepSlate ? "deepslate_" : "ore_";

        registryName = registryName + String.format("%s_%s_%s_%s_%s_%s_%s_%s_%s_feature",
                Objects.requireNonNull(block.getRegistryName()).getPath(),
                filler,
                minY,
                maxY,
                spawnRate,
                maxVeinSize,
                discardChanceOnAirExposure,
                distribution,
                featureType
        );

        registryName = makeRegistryNameUnique(registryName);
        ModLogger.debug("Registry Name: " + registryName);
        registryNames.add(registryName);

        return registryName;
    }

    private static Holder<ConfiguredFeature<OreConfiguration, ?>> reconstructConfigured(OreEntry entry, Block ore, String filler, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, boolean isDeepslate) {
        ModLogger.debug("Reconstructing configured feature: " + ore);

        String fillerName;
        Block fillerBlock = null;
        TagKey<Block> fillerTag = null;

        ModLogger.info("Filler :: " + filler);

        if (filler.contains(":")) {
            fillerBlock = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(filler)));
            fillerName = Objects.requireNonNull(fillerBlock.getRegistryName()).getPath();
        } else if (!filler.isEmpty()) {
            fillerTag = BlockTags.create(new ResourceLocation(filler));
            fillerName = filler;
        } else {
            return null;
        }

        ModLogger.info("Filler Name :: " + fillerName);

        String registryName = createUniqueRegistryName(ore, fillerName, minY, maxY, spawnRate, maxVeinSize, discardChanceOnAirExposure, entry.getDistribution().name().toLowerCase(), isDeepslate, "configured");

        ModLogger.debug("Filler Block :: " + fillerBlock);
        ModLogger.debug("Filler Tag :: " + fillerTag);

        if (fillerBlock != null) {
            if (hasDeepslateVariant(entry)) {
                return FeatureUtils.register(
                        registryName, Feature.ORE, configureFeature(
                                ore,
                                getDeepslateVariant(entry),
                                fillerBlock,
                                maxVeinSize,
                                discardChanceOnAirExposure
                        )
                );
            } else {
                return FeatureUtils.register(
                        registryName, Feature.ORE, configureFeature(
                                ore,
                                fillerBlock,
                                maxVeinSize,
                                discardChanceOnAirExposure
                        )
                );
            }
        }

        if (!fillerTag.toString().isEmpty()) {
            if (hasDeepslateVariant(entry)) {
                return FeatureUtils.register(
                        registryName, Feature.ORE, configureFeature(
                                ore,
                                getDeepslateVariant(entry),
                                fillerTag,
                                maxVeinSize,
                                discardChanceOnAirExposure
                        )
                );
            } else {
                return FeatureUtils.register(
                        registryName, Feature.ORE, configureFeature(
                                ore,
                                fillerTag,
                                maxVeinSize,
                                discardChanceOnAirExposure
                        )
                );
            }
        }

        return null;
    }


    private static OreConfiguration configureFeature(Block ore, Block deepslate_ore, Block fillerTag, int maxVeinSize, float discardChanceOnAirExposure) {
        List<OreConfiguration.TargetBlockState> target = List.of(OreConfiguration.target(new BlockMatchTest(fillerTag), ore.defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, deepslate_ore.defaultBlockState()));
        return new OreConfiguration(target, maxVeinSize, discardChanceOnAirExposure);
    }

    private static OreConfiguration configureFeature(Block ore, Block deepslate_ore, TagKey<Block> fillerTag, int maxVeinSize, float discardChanceOnAirExposure) {
        List<OreConfiguration.TargetBlockState> target = List.of(OreConfiguration.target(new TagMatchTest(fillerTag), ore.defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, deepslate_ore.defaultBlockState()));
        return new OreConfiguration(target, maxVeinSize, discardChanceOnAirExposure);
    }

    private static OreConfiguration configureFeature(Block ore, Block filler, int maxVeinSize, float discardChanceOnAirExposure) {
        return new OreConfiguration(new BlockMatchTest(filler), ore.defaultBlockState(), maxVeinSize, discardChanceOnAirExposure);
    }

    private static OreConfiguration configureFeature(Block ore, TagKey<Block> filler, int maxVeinSize, float discardChanceOnAirExposure) {
        return new OreConfiguration(new TagMatchTest(filler), ore.defaultBlockState(), maxVeinSize, discardChanceOnAirExposure);
    }

    private static Holder<PlacedFeature> reconstructPlaced(Holder<ConfiguredFeature<OreConfiguration, ?>> configuredFeature, Block ore, String fillerName, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, Distribution distribution, boolean isDeepslate) {
        ModLogger.debug("Reconstructing placed feature: " + ore);

        String registryName = createUniqueRegistryName(ore, fillerName, minY, maxY, spawnRate, maxVeinSize, discardChanceOnAirExposure, distribution.name().toLowerCase(), isDeepslate, "placed");

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
        ModLogger.debug("Format Deepslate :: " + newResourceLocation);

        return new ResourceLocation(newResourceLocation);
    }

    private static Block getDeepslateVariant(OreEntry entry) {
        Block deepslate_ore = ForgeRegistries.BLOCKS.getValue(formatDeepslate(entry));
        ModLogger.debug("Deepslate Ore: " + Objects.requireNonNull(deepslate_ore).getRegistryName() + " : " + deepslate_ore.getName());

        return deepslate_ore;
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

    private static boolean isReconstructableObject(OreEntry entry) {
        return entry.getFillers() != null && entry.getMinY() != - 1 && entry.getMaxY() != - 1 && entry.getSpawnRate() != - 1 && entry.getMaxVeinSize() != - 1 && entry.getBiomeFilter().getBiomeBlacklist() != null && entry.getBiomeFilter().getBiomeWhitelist() != null;
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

    private static final Map<Holder<PlacedFeature>, List<String>> biomeFilteringMap = new HashMap<>();
    private static final Map<Holder<PlacedFeature>, BiomeFiltering> biomeFilteringOptionMap = new HashMap<>();

    private static void filterBiomes() {
        for (Holder<PlacedFeature> reconstructedOre : reconstructedOres) {
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
        for (Holder<PlacedFeature> reconstructedOre : reconstructedOres) {
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

        if (!filteredBiomes) {
            filterBiomes();
            filteredBiomes = true;
        }

        buildBiomes(Objects.requireNonNull(event.getName()).toString(), event.getGeneration());
    }

    private static void filterGeneration(String currentBiome, List<String> biomeFilter, BiomeFiltering filteringOption, BiomeGenerationSettingsBuilder generation, Holder<PlacedFeature> reconstructedOre) {
        switch (filteringOption) {
            case WHITELIST:
                if (biomeFilter.contains(currentBiome))
                    generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            case BLACKLIST:
                if (! biomeFilter.contains(currentBiome))
                    generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            case NONE:
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + biomeFilter);
        }
    }

}