package com.ewyboy.oretweaker.util;

import net.minecraft.world.gen.IDecoratable;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class FeatureUtils {

    public static IDecoratable<ConfiguredFeature<?,?>> getVerticalRange(IDecoratable<ConfiguredFeature<?,?>> feature, int minY, int maxY) {
        return feature.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minY, minY, maxY)));
    }

}
