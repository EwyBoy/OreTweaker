package com.ewyboy.oretweaker.json.objects;

import java.util.Collections;
import java.util.List;

public class OreEntry {

    private String ore;
    private String replace;
    private int minY;
    private int maxY;
    private int maxVeinSize;
    private float spawnRate;
    private List<String> biomeBlacklist;
    private List<String> biomeWhitelist;

    public OreEntry() {}

    public OreEntry(String ore) {
        this.ore = ore;
        this.replace = null;
        this.minY = -1;
        this.maxY = -1;
        this.maxVeinSize = -1;
        this.spawnRate = -1;
        this.biomeBlacklist = Collections.emptyList();
        this.biomeWhitelist = Collections.emptyList();
    }

    public OreEntry(String ore, String replace, int minY, int maxY, int maxVeinSize, float spawnRate, List<String> biomeBlacklist, List<String> biomeWhitelist) {
        this.ore = ore;
        this.replace = replace;
        this.minY = minY;
        this.maxY = maxY;
        this.maxVeinSize = maxVeinSize;
        this.spawnRate = spawnRate;
        this.biomeBlacklist = biomeBlacklist;
        this.biomeWhitelist = biomeWhitelist;
    }


    public String getOre() {
        return ore;
    }

    public void setOre(String ore) {
        this.ore = ore;
    }


    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
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

    public List<String> getBiomeBlacklist() {
        return biomeBlacklist;
    }

    public void setBiomeBlacklist(List<String> biomeBlacklist) {
        this.biomeBlacklist = biomeBlacklist;
    }

    public List<String> getBiomeWhitelist() {
        return biomeWhitelist;
    }

    public void setBiomeWhitelist(List<String> biomeWhitelist) {
        this.biomeWhitelist = biomeWhitelist;
    }
}
