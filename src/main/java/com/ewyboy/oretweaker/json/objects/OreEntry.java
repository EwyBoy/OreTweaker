package com.ewyboy.oretweaker.json.objects;

import com.ewyboy.oretweaker.config.Settings;

import java.util.List;

public class OreEntry {

    private String ore;
    private String filler;
    private int minY;
    private int maxY;
    private int maxVeinSize;
    private float spawnRate;
    private List<String> biomeBlacklist;
    private List<String> biomeWhitelist;

    public OreEntry(String oreName, String filler, Integer minY, Integer maxY, Integer maxVeinSize, Float spawnRate, List<String> biomeBlacklist, List<String> biomeWhitelist) { }

    public OreEntry(String ore) {
        this.ore = ore;
        this.filler = null;
        this.minY = -1;
        this.maxY = -1;
        this.maxVeinSize = -1;
        this.spawnRate = -1;
        this.biomeBlacklist = null;
        this.biomeWhitelist = null;
    }

    public OreEntry(String ore, String filler, int minY, int maxY, int maxVeinSize, float spawnRate, List<String> biomeBlacklist, List<String> biomeWhitelist) {
        this.ore = ore;
        this.filler = filler;
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

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
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

    public void setSpawnRate(int spawnRate) {
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

    @Override
    public String toString() {
        if (Settings.SETTINGS.debugMode.get()) {
            return "\n" + "OreEntry {" + "\n" +
                    "\t" + "ore='" + ore + '\'' + "\n" +
                    "\t" + "filler='" + filler + '\'' + "\n" +
                    "\t" + "minY=" + minY + "\n" +
                    "\t" + "maxY=" + maxY + "\n" +
                    "\t" + "maxVeinSize=" + maxVeinSize + "\n" +
                    "\t" + "spawnRate=" + spawnRate + "\n" +
                    "\t" + "biomeBlacklist=" + biomeBlacklist + "\n" +
                    "\t" + "biomeWhitelist=" + biomeWhitelist + "\n" +
                    '}';
        } else {
            return "OreEntry{" +
                    "ore='" + ore + '\'' +
                    ", filler='" + filler + '\'' +
                    ", minY=" + minY +
                    ", maxY=" + maxY +
                    ", maxVeinSize=" + maxVeinSize +
                    ", spawnRate=" + spawnRate +
                    ", biomeBlacklist=" + biomeBlacklist +
                    ", biomeWhitelist=" + biomeWhitelist +
                    '}';
        }
    }
}
