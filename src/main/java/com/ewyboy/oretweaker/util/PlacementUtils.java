package com.ewyboy.oretweaker.util;

import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PlacementUtils {

    /**
     * Extension of {@link OrePlacements}
     * View all {@link PlacementModifierType}'s here.
     **/

    public static List<PlacementModifier> orePlacement(PlacementModifier rarity, PlacementModifier heightRange) {
        return List.of(rarity, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int rarity, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(rarity), placementModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), placementModifier);
    }

}
