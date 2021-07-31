package com.ewyboy.oretweaker.world;

import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;

import java.util.Random;

public class OreTweakerHeightProvider extends HeightProvider {

    public OreTweakerHeightProvider() {
        super();
    }

    @Override
    public int sample(Random random, WorldGenerationContext worldGenerationContext) {
        return 0;
    }

    @Override
    public HeightProviderType<?> getType() {
        return HeightProviderType.VERY_BIASED_TO_BOTTOM;
    }
}
