package com.ewyboy.oretweaker.tweaking;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.FeatureUtils;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class OreReconstructior {

    private static void reconstructFeatureFromJSON() {
        List<OreEntry> ores = JSONHandler.oreConfig.getOreConfig();

        for (OreEntry ore : ores) {
            ModLogger.info("Reconstructing ore: " + ore.getOre());
            register(ore.getOre(), reconstructFeature(
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getOre()))),
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.getFiller()))),
                    ore.getMinY(),
                    ore.getMaxY(),
                    ore.getSpawnRate(),
                    ore.getMaxVeinSize()
            ));
        }
    }

    private static <TweakedOre extends IFeatureConfig> void register(String name, ConfiguredFeature<TweakedOre, ?> configuredFeature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OreTweaker.MOD_ID, name), configuredFeature);
    }

    private static ConfiguredFeature<?, ?> reconstructFeature(Block ore, Block filler, int minY, int maxY, int spawnRate, int maxVeinSize) {
        ConfiguredFeature<?, ?> feature = Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(filler), ore.defaultBlockState(), maxVeinSize));
        feature = feature.count(spawnRate);
        feature = FeatureUtils.getVerticalRange(feature, minY, maxY).squared();

        return feature;
    }

}
