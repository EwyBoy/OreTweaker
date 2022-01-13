package com.ewyboy.oretweaker.json.objects.biome;

import java.util.Collections;
import java.util.List;

public class BiomeFilters {

    private List<String> biomeBlacklist;
    private List<String> biomeWhitelist;

    public BiomeFilters() {
        this.biomeBlacklist = Collections.emptyList();
        this.biomeWhitelist = Collections.emptyList();
    }

    public BiomeFilters(List<String> biomeBlacklist, List<String> biomeWhitelist) {
        this.biomeBlacklist = biomeBlacklist;
        this.biomeWhitelist = biomeWhitelist;
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
