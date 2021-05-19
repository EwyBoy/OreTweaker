package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.FeatureUtils;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
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

    private static final List<GenerationStage.Decoration> decorations = new LinkedList<>();

    static {
        decorations.add(GenerationStage.Decoration.UNDERGROUND_ORES);
        decorations.add(GenerationStage.Decoration.UNDERGROUND_DECORATION);
    }

    public static void BiomeLoadingEvent(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for (GenerationStage.Decoration deco : decorations) {
            FeatureUtils.destroyFeature(generation.getFeatures(deco), filterFeatures(generation.getFeatures(deco)));
        }
    }

    public static List<Supplier<ConfiguredFeature<?, ?>>> destroy = new LinkedList<>();

    private static void incinerator(Block targetBlock, Supplier<ConfiguredFeature<?, ?>> targetFeature) {
        if (targetBlock != null) {
            ModLogger.debug("Deconstructed ore generation for " + targetBlock.getRegistryName());
            OreEntry placeholderEntry = new OreEntry(Objects.requireNonNull(targetBlock.getRegistryName()).toString());
            if (JSONHandler.containsEntry(placeholderEntry)) {
                destroy.add(targetFeature);
                ModLogger.debug(targetFeature.get().feature.getRegistryName() + " destroyed");
            }
        }
    }

    private static List<Supplier<ConfiguredFeature<?, ?>>> filterFeatures(List<Supplier<ConfiguredFeature<?, ?>>> features) {
        for (Supplier<ConfiguredFeature<?, ?>> feature : features) {
            Block targetBlock = null;
            ConfiguredFeature<?, ?> targetFeature = FeatureUtils.getFeature(feature.get());
            if (targetFeature.feature instanceof OreFeature || targetFeature.feature instanceof NoExposedOreFeature) {
                OreFeatureConfig config = (OreFeatureConfig) targetFeature.config;
                targetBlock = config.state.getBlock();
                ModLogger.debug(Objects.requireNonNull(targetBlock.getRegistryName()).toString());
                incinerator(targetBlock, feature);
            } else if (targetFeature.feature instanceof ReplaceBlockFeature) {
                ReplaceBlockConfig config = (ReplaceBlockConfig) targetFeature.config;
                targetBlock = config.state.getBlock();
                ModLogger.debug(Objects.requireNonNull(targetBlock.getRegistryName()).toString());
                incinerator(targetBlock, feature);
            }
        }
        return destroy;
    }

}
