package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.BiomeFiltering;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.FeatureUtils;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

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

                    reconstructDeepslateOre(ore);

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

    private static ResourceLocation formatDeepslate(OreEntry ore) {
        String[] ore_split = ore.getOre().split(":");
        String domain = ore_split[0];
        String name = ore_split[1];
        name = "deepslate_" + name;
        String newResourceLocation = domain + ":" + name;
        ModLogger.debug("Deepslate test: " + newResourceLocation);

        return new ResourceLocation(newResourceLocation);
    }

    private static Block hasDeepslateVariant(OreEntry ore) {
        Block deepslate_ore = ForgeRegistries.BLOCKS.getValue(formatDeepslate(ore));
        ModLogger.info("Deepslate Ore: " + Objects.requireNonNull(deepslate_ore).getRegistryName() + " : " + deepslate_ore.getName());

        return deepslate_ore;
    }

    private static void reconstructDeepslateOre(OreEntry ore) {
        Block deepslate_ore = hasDeepslateVariant(ore);

        ConfiguredFeature<?, ?> reconstructedDeepslateOre = ore.getMaxVeinSize() == 1 ? reconstructOre(
                Objects.requireNonNull(deepslate_ore),
                Objects.requireNonNull(Blocks.DEEPSLATE),
                ore.getMinY(),
                ore.getMaxY(),
                ore.getSpawnRate(),
                ore.getMaxVeinSize() + 2
        ) : reconstructOre(
                Objects.requireNonNull(deepslate_ore),
                Objects.requireNonNull(Blocks.DEEPSLATE),
                ore.getMinY(),
                ore.getMaxY(),
                ore.getSpawnRate(),
                ore.getMaxVeinSize() + 1
        );

        biomeBlackListMap.put(reconstructedDeepslateOre, ore.getBiomeBlacklist());
        biomeWhiteListMap.put(reconstructedDeepslateOre, ore.getBiomeWhitelist());

        reconstructedOres.add(reconstructedDeepslateOre);
    }

    private static ConfiguredFeature<?, ?> reconstructFeature(Block ore, Block filler, int minY, int maxY, float spawnRate, int maxVeinSize) {
        ConfiguredFeature<?, ?> feature = Feature.ORE.configured(new OreConfiguration(new BlockMatchTest(filler), ore.defaultBlockState(), maxVeinSize));
        feature = FeatureUtils.getVerticalRange(feature, minY, maxY).squared();
        feature = spawnRate < 1 ? feature.rarity((int) (1 / spawnRate)) : feature.count((int) spawnRate);

        Block deepslate_ore = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("deepslate_" + Objects.requireNonNull(ore.getRegistryName())));

        if (deepslate_ore != null) {
            ConfiguredFeature<?, ?> deepslate_feature = Feature.ORE.configured(new OreConfiguration(new BlockMatchTest(Blocks.DEEPSLATE), deepslate_ore.defaultBlockState(), maxVeinSize));
            deepslate_feature = FeatureUtils.getVerticalRange(deepslate_feature, minY, maxY).squared();
            deepslate_feature = spawnRate < 1 ? feature.rarity((int) (1 / spawnRate)) : feature.count((int) spawnRate);
            return deepslate_feature;
        }

        return feature;
    }

    private static <FeatureConfig extends FeatureConfiguration> ConfiguredFeature<FeatureConfig, ?> register(String name, ConfiguredFeature<FeatureConfig, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
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

    private static Boolean reconstructed = false;

    public static void BiomeLoadingEvent(final BiomeLoadingEvent event) {
        if (!reconstructed) {
            reconstructFeatureFromJSON();
            reconstructed = true;
        }

        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        for (ConfiguredFeature<?, ?> reconstructedOre : reconstructedOres) {
            ResourceLocation currentBiome = Objects.requireNonNull(event.getName());
            List<String> currentBiomeFilter = setBiomeFilteringOption(biomeBlackListMap.get(reconstructedOre), biomeWhiteListMap.get(reconstructedOre));
            BiomeFiltering currentBiomeFilteringOption = getBiomeFilteringOption(biomeBlackListMap.get(reconstructedOre), biomeWhiteListMap.get(reconstructedOre));
            filterGeneration(currentBiome, currentBiomeFilter, currentBiomeFilteringOption, generation, reconstructedOre);
        }
    }

    private static void filterGeneration(ResourceLocation currentBiome, List<String> biomeFilter, BiomeFiltering filteringOption, BiomeGenerationSettingsBuilder generation, ConfiguredFeature<?, ?> reconstructedOre) {
        switch (filteringOption) {
            case WHITELIST:
                if (biomeFilter.contains(currentBiome))
                    generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, reconstructedOre);
                break;
            case BLACKLIST:
                if (!biomeFilter.contains(currentBiome))
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
