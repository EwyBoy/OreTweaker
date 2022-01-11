package com.ewyboy.oretweaker.json.objects.spawn;

public class SpawnFilter {

    private BiomeFilter biomeFilter;

    public SpawnFilter() {
        this.biomeFilter = null;
    }

    public SpawnFilter(BiomeFilter biomeFilter) {
        this.biomeFilter = biomeFilter;
    }

    public BiomeFilter getBiomeFilter() {
        return biomeFilter;
    }

    public void setBiomeFilter(BiomeFilter biomeFilter) {
        this.biomeFilter = biomeFilter;
    }
}
