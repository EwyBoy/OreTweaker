package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.BiomeFiltering;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.FeatureUtils;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.BiomeName;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class OreReconstruction {

    public static final List<ConfiguredFeature<?, ?>> reconstructedOres = new LinkedList<>();

    public static void reconstruct(IEventBus forgeBus) {
        forgeBus.addListener(EventPriority.LOWEST, OreReconstruction::BiomeLoadingEvent);
    }

    private static final Map<ConfiguredFeature<?, ?>, List<String>> biomeBlackListMap = new HashMap<>();
    private static final Map<ConfiguredFeature<?, ?>, List<String>> biomeWhiteListMap = new HashMap<>();

    private static void reconstructFeatureFromJSON() {
        List<OreEntry> ores = JSONHandler.oreConfig.getOreConfig();
        for (OreEntry ore : ores) {
            if (isReconstructableObject(ore)) {
                try {
                    ConfiguredFeature<?, ?> reconstructedOre = ore.getMaxVeinSize() == 1 ? reconstructOre(
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getFiller()))),
                            ore.getMinY(),
                            ore.getMaxY(),
                            ore.getSpawnRate(),
                            ore.getMaxVeinSize() + 2
                    ) : reconstructOre(
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getFiller()))),
                            ore.getMinY(),
                            ore.getMaxY(),
                            ore.getSpawnRate(),
                            ore.getMaxVeinSize() + 1
                    );
                    biomeBlackListMap.put(reconstructedOre, ore.getBiomeBlacklist());
                    biomeWhiteListMap.put(reconstructedOre, ore.getBiomeWhitelist());
                    reconstructedOres.add(reconstructedOre);
                } catch (Exception ignored) {
                    ModLogger.error(ore.getOre() + " can't be reconstructed from JSON..");
                }
            } else {
                ModLogger.error(ore.getOre() + " can't be reconstructed ??");
            }
        }
    }

    private static ConfiguredFeature<?, ?> reconstructOre(Block ore, Block filler, int minY, int maxY, float spawnRate, int maxVeinSize) {
        ModLogger.debug("Reconstructing ore: " + ore);
        String registryName = String.format("%s_%s_%d_%d_%f_%d",
                Objects.requireNonNull(ore.getRegistryName()).getPath(),
                Objects.requireNonNull(filler.getRegistryName()).getPath(),
                minY, maxY, spawnRate, maxVeinSize
        );
        return register(
                registryName, reconstructFeature(
                        ore,
                        filler,
                        minY,
                        maxY,
                        spawnRate,
                        maxVeinSize
                )
        );
    }

    private static ConfiguredFeature<?, ?> reconstructFeature(Block ore, Block filler, int minY, int maxY, float spawnRate, int maxVeinSize) {
        ConfiguredFeature<?, ?> feature = Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(filler), ore.defaultBlockState(), maxVeinSize));
        feature = FeatureUtils.getVerticalRange(feature, minY, maxY).squared();
        feature = spawnRate < 1 ? feature.chance((int) (1 / spawnRate)) : feature.count((int) spawnRate);

        return feature;
    }

    private static <FeatureConfig extends IFeatureConfig> ConfiguredFeature<FeatureConfig, ?> register(String name, ConfiguredFeature<FeatureConfig, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
    }

    private static boolean isReconstructableObject(OreEntry entry) {
        return entry.getFiller() != null && entry.getMinY() != -1 && entry.getMaxY() != -1 && entry.getSpawnRate() != -1 && entry.getMaxVeinSize() != -1 && entry.getBiomeBlacklist() != null && entry.getBiomeWhitelist() != null;
    }

    private static List<String> setBiomeFilteringOption(List<String> blackList, List<String> whiteList) {
        return whiteList.isEmpty() ? blackList.isEmpty() ? Collections.emptyList() : blackList : whiteList;
    }

    private static BiomeFiltering getBiomeFilteringOption(List<String> blackList, List<String> whiteList) {
        return whiteList.isEmpty() ? blackList.isEmpty() ? BiomeFiltering.NONE : BiomeFiltering.BLACKLIST : BiomeFiltering.WHITELIST;
    }

    private static List<String> applyBiomeDictionaryFiltering(List<String> biomeEntries) {
        List<String> biomeDictionaryFilter = new ArrayList<>();
        for (String biomeEntry : biomeEntries) {
            if (biomeEntry.contains(":")) {
                biomeDictionaryFilter.add(biomeEntry);
            } else {
                BiomeDictionary.Type type = BiomeDictionary.Type.getType(biomeEntry);

                Set<RegistryKey<Biome>> biomes = BiomeDictionary.getBiomes(type);
                biomes.forEach(biome -> {
                    ModLogger.debug(biome.location().toString());
                    biomeDictionaryFilter.add(biome.location().toString());
                });
            }
        }
        return biomeDictionaryFilter;
    }

    private static Boolean reconstructed = false;

    // TODO Cash all stuff here to reduce stress on loading
    public static void BiomeLoadingEvent(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        if (!reconstructed) {
            reconstructFeatureFromJSON();
            reconstructed = true;
        }

        for (ConfiguredFeature<?, ?> reconstructedOre : reconstructedOres) {
            String currentBiome = Objects.requireNonNull(event.getName()).toString();
            List<String> currentBiomeFilter = setBiomeFilteringOption(biomeBlackListMap.get(reconstructedOre), biomeWhiteListMap.get(reconstructedOre));
            BiomeFiltering currentBiomeFilteringOption = getBiomeFilteringOption(biomeBlackListMap.get(reconstructedOre), biomeWhiteListMap.get(reconstructedOre));

            ModLogger.debug("Pre biome filter: " + currentBiomeFilter);
            currentBiomeFilter = applyBiomeDictionaryFiltering(currentBiomeFilter);
            ModLogger.debug("Post biome filter: " + currentBiomeFilter);

            filterGeneration(currentBiome, currentBiomeFilter, currentBiomeFilteringOption, generation, reconstructedOre);
        }
    }

    private static void filterGeneration(String currentBiome, List<String> biomeFilter, BiomeFiltering filteringOption, BiomeGenerationSettingsBuilder generation, ConfiguredFeature<?, ?> reconstructedOre) {
        switch (filteringOption) {
            case WHITELIST:
                if (biomeFilter.contains(currentBiome))
                    generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            case BLACKLIST:
                if (!biomeFilter.contains(currentBiome))
                    generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            case NONE:
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + biomeFilter);
        }
    }

}
