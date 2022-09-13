package com.ewyboy.oretweaker.tweaking;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

import static net.minecraft.data.worldgen.features.OreFeatures.DEEPSLATE_ORE_REPLACEABLES;

public class ReconstructedConfiguredFeature {

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> reconstructConfigured(OreEntry entry, Block ore, String filler, int minY, int maxY, float spawnRate, int maxVeinSize, float discardChanceOnAirExposure, boolean isDeepslate) {
        ModLogger.debug("Reconstructing configured feature: " + ore);

        String fillerName;
        Block fillerBlock = null;
        TagKey<Block> fillerTag = null;

        ModLogger.info("Filler :: " + filler);

        if (filler.contains(":")) {
            fillerBlock = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(filler)));
            fillerName = Objects.requireNonNull(Registry.BLOCK.getKey(fillerBlock)).toString();
        } else if (!filler.isEmpty()) {
            fillerTag = BlockTags.create(new ResourceLocation(filler));
            fillerName = filler;
        } else {
            return null;
        }

        ModLogger.info("Filler Name :: " + fillerName);

        String registryName = ReconstructedFeature.createUniqueRegistryName(ore, fillerName, minY, maxY, spawnRate, maxVeinSize, discardChanceOnAirExposure, entry.getDistribution().name().toLowerCase(), isDeepslate, "configured");

        ModLogger.debug("Filler Block :: " + fillerBlock);
        ModLogger.debug("Filler Tag :: " + fillerTag);

        if (fillerBlock != null) {
            if (ReconstructedFeature.hasDeepslateVariant(entry)) {
                ModLogger.debug("Registering deepslate feature with block");
                return FeatureUtils.register(
                        registryName, Feature.ORE, configureFeature(
                                ore,
                                ReconstructedFeature.getDeepslateVariant(entry),
                                fillerBlock,
                                maxVeinSize,
                                discardChanceOnAirExposure
                        )
                );
            } else {
                ModLogger.debug("Registering feature with block");
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
            ModLogger.debug("Registering deepslate feature with tag");
            if (ReconstructedFeature.hasDeepslateVariant(entry)) {
                return FeatureUtils.register(
                        registryName, Feature.ORE, configureFeature(
                                ore,
                                ReconstructedFeature.getDeepslateVariant(entry),
                                fillerTag,
                                maxVeinSize,
                                discardChanceOnAirExposure
                        )
                );
            } else {
                ModLogger.debug("Registering feature with tag");
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

}
