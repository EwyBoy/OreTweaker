package com.ewyboy.oretweaker.json.objects;

import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;

import java.util.Collections;
import java.util.List;

public class OreEntry {

    private String ore;
    private List<String> fillers;
    private Distribution distribution;
    private int minY;
    private int maxY;
    private int maxVeinSize;
    private float spawnRate;
    private float discardChanceOnAirExposure;
    private BiomeFilters biomeFilters;
    private boolean replace;

    public OreEntry() {}

    public OreEntry(String ore) {
        this.ore = ore;
        this.fillers = Collections.emptyList();
        this.distribution = null;
        this.minY = -1;
        this.maxY = -1;
        this.maxVeinSize = -1;
        this.spawnRate = -1;
        this.discardChanceOnAirExposure = -1;
        this.biomeFilters = null;
        this.replace = true;
    }

    public OreEntry(String ore, List<String> fillers, Distribution distribution, int minY, int maxY, int maxVeinSize, float spawnRate, float discardChanceOnAirExposure, BiomeFilters biomeFilters, boolean replace) {
        this.ore = ore;
        this.fillers = fillers;
        this.distribution = distribution;
        this.minY = minY;
        this.maxY = maxY;
        this.maxVeinSize = maxVeinSize;
        this.spawnRate = spawnRate;
        this.discardChanceOnAirExposure = discardChanceOnAirExposure;
        this.biomeFilters = biomeFilters;
        this.replace = replace;
    }

    public String getOre() {
        return ore;
    }

    public void setOre(String ore) {
        this.ore = ore;
    }

    public List<String> getFillers() {
        return fillers;
    }

    public void setFillers(List<String> fillers) {
        this.fillers = fillers;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public void setMaxVeinSize(int maxVeinSize) {
        this.maxVeinSize = maxVeinSize;
    }

    public float getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(float spawnRate) {
        this.spawnRate = spawnRate;
    }

    public float getDiscardChanceOnAirExposure() {
        return discardChanceOnAirExposure;
    }

    public void setDiscardChanceOnAirExposure(float discardChanceOnAirExposure) {
        this.discardChanceOnAirExposure = discardChanceOnAirExposure;
    }

    public BiomeFilters getBiomeFilter() {
        return biomeFilters;
    }

    public void setBiomeFilter(BiomeFilters biomeFilters) {
        this.biomeFilters = biomeFilters;
    }

    public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }
}
