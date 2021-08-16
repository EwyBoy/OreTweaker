package com.ewyboy.oretweaker.json.template.templates.defaults.overworld.ores;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class EmeraldOreTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    private static final List<String> INFESTED_AND_EMERALD_BIOMES = new LinkedList<>(); static {
        INFESTED_AND_EMERALD_BIOMES.add("minecraft:mountains");
        INFESTED_AND_EMERALD_BIOMES.add("minecraft:wooded_mountains");
        INFESTED_AND_EMERALD_BIOMES.add("minecraft:gravelly_mountains");
    }

    @Override
    public String templateName() {
        return "emerald_ore";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:emerald_ore",
                "minecraft:stone",
                4,
                32,
                1,
                5,
                emptyList,
                INFESTED_AND_EMERALD_BIOMES
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
