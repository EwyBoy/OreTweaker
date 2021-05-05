package com.ewyboy.oretweaker.util;

import net.minecraft.world.gen.IDecoratable;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import java.util.List;
import java.util.function.Supplier;

public class FeatureUtils {

    public static ConfiguredFeature<?, ?> getFeature(ConfiguredFeature<?, ?> feature) {
        ConfiguredFeature<?, ?> currentFeature = feature;
        if (currentFeature.feature instanceof DecoratedFeature) {
            do {
                currentFeature = ((DecoratedFeatureConfig) currentFeature.config()).feature.get();
            } while (currentFeature.feature instanceof DecoratedFeature);
        }
        return currentFeature;
    }

    public static void destroyFeature(List<Supplier<ConfiguredFeature<?, ?>>> features, List<Supplier<ConfiguredFeature<?, ?>>> destroy) {
        for (Supplier<ConfiguredFeature<?, ?>> feature : destroy) {
            features.remove(feature);
        }
    }

    public static IDecoratable<ConfiguredFeature<?, ?>> getVerticalRange(IDecoratable<ConfiguredFeature<?, ?>> feature, int minY, int maxY) {
        return feature.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minY, minY, maxY)));
    }

}
