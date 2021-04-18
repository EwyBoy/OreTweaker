package com.ewyboy.oretweaker.json.objects;

public class OreEntry {

    private String ore;
    private String filler;
    private int minY;
    private int maxY;
    private int maxVeinSize;
    private int spawnRate;

    public OreEntry(String oreName, String filler, Integer minY, Integer maxY, Integer maxVeinSize, Integer spawnRate) {}

    public OreEntry(String ore, String filler, int minY, int maxY, int maxVeinSize, int spawnRate) {
        this.ore = ore;
        this.filler = filler;
        this.minY = minY;
        this.maxY = maxY;
        this.maxVeinSize = maxVeinSize;
        this.spawnRate = spawnRate;
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

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    @Override
    public String toString() {
        return "OreEntry{" + "ore='" + ore + '\'' + ", filler='" + filler + '\'' + ", minY=" + minY + ", maxY=" + maxY + ", maxVeinSize=" + maxVeinSize + ", spawnRate=" + spawnRate + '}';
    }

}
