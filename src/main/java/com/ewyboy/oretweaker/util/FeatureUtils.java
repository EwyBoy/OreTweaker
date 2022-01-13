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

}
