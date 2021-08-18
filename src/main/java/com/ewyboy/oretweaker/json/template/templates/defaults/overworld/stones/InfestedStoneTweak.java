package com.ewyboy.oretweaker.json.template.templates.defaults.overworld.stones;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class InfestedStoneTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    private static final List<String> INFESTED_AND_EMERALD_BIOMES = new LinkedList<>(); static {
        INFESTED_AND_EMERALD_BIOMES.add("minecraft:mountains");
        INFESTED_AND_EMERALD_BIOMES.add("minecraft:wooded_mountains");
        INFESTED_AND_EMERALD_BIOMES.add("minecraft:gravelly_mountains");
    }

    @Override
    public String templateName() {
        return "infested_stone";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:infested_stone",
                "minecraft:stone",
                1,
                64,
                8,
                7,
                emptyList,
                INFESTED_AND_EMERALD_BIOMES
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
