package com.ewyboy.oretweaker.json.objects;

import com.ewyboy.oretweaker.config.Settings;

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

    @Override
    public String toString() {
        if (Settings.SETTINGS.debugMode.get()) {
            return "OreConfig {" +
                    "\n" + "\t" +  "oreConfig=" + oreConfig + "\n" +
                    '}' + "\n";
        } else {
            return "OreConfig{" + "oreConfig=" + oreConfig + '}';
        }
    }

}
