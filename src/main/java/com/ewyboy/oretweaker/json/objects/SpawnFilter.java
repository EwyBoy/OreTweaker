package com.ewyboy.oretweaker.json.objects;

import java.util.Collections;
import java.util.List;

public class SpawnFilter {

    private List<String> biomeBlacklist;
    private List<String> biomeWhitelist;
    private List<String> dimensionBlacklist;
    private List<String> dimensionWhitelist;

    public SpawnFilter() {
        this.biomeBlacklist = Collections.emptyList();
        this.biomeWhitelist = Collections.emptyList();
        this.dimensionBlacklist = Collections.emptyList();
        this.dimensionWhitelist =Collections.emptyList();
    }

    public SpawnFilter(List<String> biomeBlacklist, List<String> biomeWhitelist, List<String> dimensionBlacklist, List<String> dimensionWhitelist) {
        this.biomeBlacklist = biomeBlacklist;
        this.biomeWhitelist = biomeWhitelist;
        this.dimensionBlacklist = dimensionBlacklist;
        this.dimensionWhitelist = dimensionWhitelist;
    }


}
