package com.ewyboy.oretweaker.json.objects;

import java.util.List;

public class OreConfig {

    private List<OreEntry> oreConfig;

    public OreConfig(List<OreEntry> oreConfig) {
        this.oreConfig = oreConfig;
    }

    public List<OreEntry> getOreConfig() {
        return oreConfig;
    }

    public void setOreConfig(List<OreEntry> oreConfig) {
        this.oreConfig = oreConfig;
    }

}
