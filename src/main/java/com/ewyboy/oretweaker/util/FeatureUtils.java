package com.ewyboy.oretweaker.util;

import net.minecraft.world.level.levelgen.Decoratable;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.DecoratedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratedFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.List;
import java.util.function.Supplier;

public class FeatureUtils {

    public static ConfiguredFeature<?, ?> getFeature(ConfiguredFeature<?, ?> feature) {
        ConfiguredFeature<?, ?> currentFeature = feature;
        if (currentFeature.feature instanceof DecoratedFeature) {
            do {
                currentFeature = ((DecoratedFeatureConfiguration) currentFeature.config()).feature.get();
            } while (currentFeature.feature instanceof DecoratedFeature);
        }
        return currentFeature;
    }

    public static void destroyFeature(List<Supplier<ConfiguredFeature<?, ?>>> features, List<Supplier<ConfiguredFeature<?, ?>>> destroy) {
        for (Supplier<ConfiguredFeature<?, ?>> feature : destroy) {
            features.remove(feature);
        }
    }

    public static Decoratable<ConfiguredFeature<?, ?>> getVerticalRange(Decoratable<ConfiguredFeature<?, ?>> feature, int minY, int maxY) {
        return feature.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)))));
    }

}
