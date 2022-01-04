package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.BiomeFiltering;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getReplace()))),
                            ore.getMinY(),
                            ore.getMaxY(),
                            ore.getSpawnRate(),
                            ore.getMaxVeinSize() + 2,
                            false
                    ) : reconstructOre(
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getReplace()))),
                            ore.getMinY(),
                            ore.getMaxY(),
                            ore.getSpawnRate(),
                            ore.getMaxVeinSize() + 1,
                            false
                    );

                    PlacedFeature placedFeature = reconstructPlacedFeature(reconstructedOre, ore.getSpawnRate(), ore.getMinY(), ore.getMaxY());
                    register(placedFeature.toString(), placedFeature);

                    biomeBlackListMap.put(reconstructedOre, ore.getBiomeBlacklist());
                    biomeWhiteListMap.put(reconstructedOre, ore.getBiomeWhitelist());
                    reconstructedOres.add(placedFeature);

                    //reconstructDeepslateOre(ore);

                } catch (Exception ignored) {
                    ModLogger.error(ore.getOre() + " can't be reconstructed from JSON..");
                }
            } else {
                ModLogger.error(ore.getOre() + " can't be reconstructed ??");
            }
        }
    }

    private static ConfiguredFeature<?, ?> reconstructOre(Block ore, Block filler, int minY, int maxY, float spawnRate, int maxVeinSize, boolean isDeepslate) {
        ModLogger.debug("Reconstructing ore: " + ore);
        String registryName = String.format("%s_%s_%s_%s_%s_%s",
                Objects.requireNonNull(ore.getRegistryName()).getPath(),
                Objects.requireNonNull(filler.getRegistryName()).getPath(),
                minY,
                maxY,
                spawnRate,
                maxVeinSize
        );

        if (isDeepslate) registryName = "deepslate_" + registryName;

        ModLogger.debug("Registry Name: " + registryName);

        return register(registryName, reconstructConfiguredFeature(
                ore,
                filler,
                maxVeinSize
        ));
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
        ModLogger.debug("Deepslate Ore: " + Objects.requireNonNull(deepslate_ore).getRegistryName() + " : " + deepslate_ore.getName());

        return deepslate_ore;
    }

   /* private static void reconstructDeepslateOre(OreEntry ore) {
        Block deepslate_ore = hasDeepslateVariant(ore);
        ConfiguredFeature<?, ?> reconstructedDeepslateOre = ore.getMaxVeinSize() == 1 ? reconstructOre(
                Objects.requireNonNull(deepslate_ore),
                Objects.requireNonNull(Blocks.DEEPSLATE),
                ore.getMinY(),
                ore.getMaxY(),
                ore.getSpawnRate(),
                ore.getMaxVeinSize() + 2,
                true
        ) : reconstructOre(
                Objects.requireNonNull(deepslate_ore),
                Objects.requireNonNull(Blocks.DEEPSLATE),
                ore.getMinY(),
                ore.getMaxY(),
                ore.getSpawnRate(),
                ore.getMaxVeinSize() + 1,
                true
        );
        biomeBlackListMap.put(reconstructedDeepslateOre, ore.getBiomeBlacklist());
        biomeWhiteListMap.put(reconstructedDeepslateOre, ore.getBiomeWhitelist());
        reconstructedOres.add(reconstructedDeepslateOre);
    }*/

    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifiers) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifiers, BiomeFilter.biome());
    }

    private static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), placementModifier);
    }

    private static ConfiguredFeature<?, ?> reconstructConfiguredFeature(Block ore, Block filler, int maxVeinSize) {
        return Feature.ORE.configured(new OreConfiguration(new BlockMatchTest(filler), ore.defaultBlockState(), maxVeinSize));
    }

    private static PlacedFeature reconstructPlacedFeature(ConfiguredFeature<?, ?> configuredFeature, float spawnRate, int minY, int maxY) {
        return configuredFeature.placed(rareOrePlacement((int) spawnRate, HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY))));
    }

    private static <FeatureConfig extends FeatureConfiguration> ConfiguredFeature<FeatureConfig, ?> register(String name, ConfiguredFeature<FeatureConfig, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
    }
    private static PlacedFeature register(String name, PlacedFeature placedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), placedFeature);
    }

    private static boolean isReconstructableObject(OreEntry entry) {
        return entry.getReplace() != null && entry.getMinY() != -1 && entry.getMaxY() != -1 && entry.getSpawnRate() != -1 && entry.getMaxVeinSize() != -1 && entry.getBiomeBlacklist() != null && entry.getBiomeWhitelist() != null;
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
            biomeFilteringMap.put(reconstructedOre, applyBiomeDictionaryFiltering(setBiomeFiltering(biomeBlackListMap.get(reconstructedOre), biomeWhiteListMap.get(reconstructedOre))));
            biomeFilteringOptionMap.put(reconstructedOre, getBiomeFilteringOption(biomeBlackListMap.get(reconstructedOre), biomeWhiteListMap.get(reconstructedOre)));
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