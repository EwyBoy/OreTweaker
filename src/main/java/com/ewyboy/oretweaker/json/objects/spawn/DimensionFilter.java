package com.ewyboy.oretweaker.json.objects.spawn;

import java.util.Collections;
import java.util.List;

public class DimensionFilter {

    private List<String> dimensionBlacklist;
    private List<String> dimensionWhitelist;

    public DimensionFilter() {
        this.dimensionBlacklist = Collections.emptyList();
        this.dimensionWhitelist = Collections.emptyList();;
    }

    public DimensionFilter(List<String> dimensionBlacklist, List<String> dimensionWhitelist) {
        this.dimensionBlacklist = dimensionBlacklist;
        this.dimensionWhitelist = dimensionWhitelist;
    }

    public List<String> getDimensionBlacklist() {
        return dimensionBlacklist;
    }

    public void setDimensionBlacklist(List<String> dimensionBlacklist) {
        this.dimensionBlacklist = dimensionBlacklist;
    }

    public List<String> getDimensionWhitelist() {
        return dimensionWhitelist;
    }

    public void setDimensionWhitelist(List<String> dimensionWhitelist) {
        this.dimensionWhitelist = dimensionWhitelist;
    }

}
