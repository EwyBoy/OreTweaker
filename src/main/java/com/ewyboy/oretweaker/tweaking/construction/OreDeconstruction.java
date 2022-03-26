package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.ScatteredOreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OreDeconstruction {

    public static List<Holder<PlacedFeature>> destroy = new LinkedList<>();

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
            destroyFeature(biomeBuilder.getFeatures(deco), filterFeatures(biomeBuilder.getFeatures(deco)));
        }
    }

    public static PlacedFeature getFeature(PlacedFeature feature) {
        return feature;
    }

    public static void destroyFeature(List<Holder<PlacedFeature>> features, List<Holder<PlacedFeature>> destroy) {
        for (Holder<PlacedFeature> feature : destroy) {
            features.remove(feature);
        }
    }

    private static void incinerator(Block targetBlock, Holder<PlacedFeature> targetFeature) {
        if (targetBlock != null) {
            ModLogger.debug("Deconstructed ore generation for " + targetBlock.getRegistryName());
            OreEntry oreEntry = new OreEntry(Objects.requireNonNull(targetBlock.getRegistryName()).toString());
            if (JSONHandler.containsEntry(oreEntry)) {
                destroy.add(targetFeature);
            }
        }
    }

    private static List<Holder<PlacedFeature>> filterFeatures(List<Holder<PlacedFeature>> features) {
        for (Holder<PlacedFeature> feature : features) {

            Block targetBlock;
            PlacedFeature targetFeature = getFeature(feature.value());

            for (ConfiguredFeature<?, ?> configuredFeature : targetFeature.getFeatures().toList()) {
                if (configuredFeature.feature() instanceof OreFeature || configuredFeature.feature() instanceof ScatteredOreFeature) {
                    OreConfiguration config = (OreConfiguration) configuredFeature.config();
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
