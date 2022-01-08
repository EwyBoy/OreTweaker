package com.ewyboy.oretweaker.json.objects.spawn;

public class SpawnFilter {

    private BiomeFilter biomeFilter;
    private DimensionFilter dimensionFilter;

    public SpawnFilter() {
        this.biomeFilter = null;
        this.dimensionFilter = null;
    }

    public SpawnFilter(BiomeFilter biomeFilter, DimensionFilter dimensionFilter) {
        this.biomeFilter = biomeFilter;
        this.dimensionFilter = dimensionFilter;
    }

    public BiomeFilter getBiomeFilter() {
        return biomeFilter;
    }

    public void setBiomeFilter(BiomeFilter biomeFilter) {
        this.biomeFilter = biomeFilter;
    }

    public DimensionFilter getDimensionFilter() {
        return dimensionFilter;
    }

    public void setDimensionFilter(DimensionFilter dimensionFilter) {
        this.dimensionFilter = dimensionFilter;
    }
}
