package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.FeatureUtils;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.ReplaceBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceBlockConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class OreDeconstruction {

    public static void deconstruct(IEventBus forgeBus) {
        forgeBus.addListener(EventPriority.LOWEST, OreDeconstruction :: BiomeLoadingEvent);
    }

    private static final List<GenerationStep.Decoration> decorations = new LinkedList<>();

    static {
        decorations.add(GenerationStep.Decoration.UNDERGROUND_ORES);
        decorations.add(GenerationStep.Decoration.UNDERGROUND_DECORATION);
    }

    public static void BiomeLoadingEvent(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder biomeBuilder = event.getGeneration();
        for (GenerationStep.Decoration deco : decorations) {
            FeatureUtils.destroyFeature(biomeBuilder.getFeatures(deco), filterFeatures(biomeBuilder.getFeatures(deco)));
        }
    }

    public static List<Supplier<PlacedFeature>> destroy = new LinkedList<>();

    private static void incinerator(Block targetBlock, Supplier<PlacedFeature> targetFeature) {
        if (targetBlock != null) {
            ModLogger.debug("Deconstructed ore generation for " + targetBlock.getRegistryName());
            OreEntry oreEntry = new OreEntry(Objects.requireNonNull(targetBlock.getRegistryName()).toString());
            if (JSONHandler.containsEntry(oreEntry)) {
                destroy.add(targetFeature);
            }
        }
    }

    private static List<Supplier<PlacedFeature>> filterFeatures(List<Supplier<PlacedFeature>> features) {
        for (Supplier<PlacedFeature> feature : features) {

            Block targetBlock;
            PlacedFeature targetFeature = FeatureUtils.getFeature(feature.get());

            for (ConfiguredFeature<?, ?> configuredFeature : targetFeature.getFeatures().toList()) {
                if (configuredFeature.feature instanceof OreFeature) {
                    OreConfiguration config = (OreConfiguration) configuredFeature.config;
                    for (OreConfiguration.TargetBlockState state : config.targetStates) {
                        targetBlock = state.state.getBlock();
                        ModLogger.debug(Objects.requireNonNull(targetBlock.getRegistryName()).toString());
                        incinerator(targetBlock, feature);
                    }
                }
            }
        }
        return destroy;
    }

}
