package com.ewyboy.oretweaker.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;

public class OreTweakerFeatureConfig implements FeatureConfiguration, DecoratorConfiguration {

    public static final Codec<OreTweakerFeatureConfig> CODEC = RecordCodecBuilder.create((group) -> group.group(RuleTest.CODEC.fieldOf("target").forGetter((target) -> target.target), BlockState.CODEC.fieldOf("state").forGetter((state) -> state.state), Codec.intRange(0, 64).fieldOf("size").forGetter((size) -> size.size)).apply(group, OreTweakerFeatureConfig::new));

    public final RuleTest target;
    public final int size;
    public final BlockState state;

    public OreTweakerFeatureConfig(RuleTest filler, BlockState blockState, int size) {
        this.size = size;
        this.state = blockState;
        this.target = filler;
    }

}
