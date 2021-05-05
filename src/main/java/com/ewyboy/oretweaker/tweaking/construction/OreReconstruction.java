package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.FeatureUtils;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OreReconstruction {

    public static final List<ConfiguredFeature<?, ?>> reconstructedOres = new LinkedList<>();

    public static void reconstruct() {
        reconstructFeatureFromJSON();
    }

    private static void reconstructFeatureFromJSON() {
        List<OreEntry> ores = JSONHandler.oreConfig.getOreConfig();
        for (OreEntry ore : ores) {
            ConfiguredFeature<?, ?> reconstructedOre = reconstructOre(
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getFiller()))),
                    ore.getMinY(),
                    ore.getMaxY(),
                    ore.getSpawnRate(),
                    ore.getMaxVeinSize()
            );
            reconstructedOres.add(reconstructedOre);
        }
    }

    private static ConfiguredFeature<?, ?> reconstructOre(Block ore, Block filler, int minY, int maxY, int spawnRate, int maxVeinSize) {
        ModLogger.info("Reconstructing ore: " + ore);
        return register(Objects.requireNonNull(ore.getRegistryName()).getPath(), reconstructFeature(
                ore,
                filler,
                minY,
                maxY,
                spawnRate,
                maxVeinSize
        ));
    }

    private static ConfiguredFeature<?, ?> reconstructFeature(Block ore, Block filler, int minY, int maxY, int spawnRate, int maxVeinSize) {
        ConfiguredFeature<?, ?> feature = Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(filler), ore.defaultBlockState(), maxVeinSize));
        feature = feature.count(spawnRate);
        feature = FeatureUtils.getVerticalRange(feature, minY, maxY).squared();

        return feature;
    }

    private static <FeatureConfig extends IFeatureConfig> ConfiguredFeature<FeatureConfig, ?> register(String name, ConfiguredFeature<FeatureConfig, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void BiomeLoadingEvent(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for (ConfiguredFeature<?, ?> reconstructedOre : reconstructedOres) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, reconstructedOre);
        }
    }

}
