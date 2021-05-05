package com.ewyboy.oretweaker.tweaking.construction;

import com.ewyboy.oretweaker.util.ModLogger;
import com.sun.media.jfxmedia.logging.Logger;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.function.Supplier;

public class OreDeconstruction {

    public static void deconstruct() {

    }

    private static void deconstructFeatureToJSON() {

    }

    public static void BiomeLoadingEvent(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        List<Supplier<ConfiguredFeature<?, ?>>> oreGenerationList = generation.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES);

        for (Supplier<ConfiguredFeature<?, ?>> oreGenerationEntry : oreGenerationList) {
            ConfiguredFeature<?, ?> oreEntry = oreGenerationEntry.get();
            ModLogger.info("Ore Feature: " + oreEntry.feature.getRegistryName());



        }


    }


}
