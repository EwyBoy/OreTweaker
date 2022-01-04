package com.ewyboy.oretweaker.util;

import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.function.Supplier;

public class FeatureUtils {

    public static PlacedFeature getFeature(PlacedFeature feature) {
        return feature;
    }

    public static void destroyFeature(List<Supplier<PlacedFeature>> features, List<Supplier<PlacedFeature>> destroy) {
        for (Supplier<PlacedFeature> feature : destroy) {
            features.remove(feature);
        }
    }

    /*public static Decoratable<ConfiguredFeature<?, ?>> getVerticalRange(Decoratable<ConfiguredFeature<?, ?>> feature, int minY, int maxY) {
        return feature.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)))));
    }*/

}
