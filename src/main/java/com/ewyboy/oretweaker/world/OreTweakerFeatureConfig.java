package com.ewyboy.oretweaker.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class OreTweakerFeatureConfig implements IFeatureConfig, IPlacementConfig {

    public static final Codec<OreTweakerFeatureConfig> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(RuleTest.CODEC.fieldOf("target").forGetter((p_236570_0_) -> {
            return p_236570_0_.target;
        }), BlockState.CODEC.fieldOf("state").forGetter((p_236569_0_) -> {
            return p_236569_0_.state;
        }), Codec.intRange(0, 64).fieldOf("size").forGetter((p_236567_0_) -> {
            return p_236567_0_.size;
        })).apply(p_236568_0_, OreTweakerFeatureConfig :: new);
    });

    public final RuleTest target;
    public final int size;
    public final BlockState state;

    public OreTweakerFeatureConfig(RuleTest filler, BlockState blockState, int size) {
        this.size = size;
        this.state = blockState;
        this.target = filler;
    }

}
