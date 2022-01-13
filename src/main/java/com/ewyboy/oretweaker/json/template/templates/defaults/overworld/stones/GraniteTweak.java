package com.ewyboy.oretweaker.json.template.templates.defaults.overworld.stones;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GraniteTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "granite";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:granite",
                Collections.singletonList("base_stone_overworld"),
                Distribution.UNIFORM,
                64,
                128,
                64,
                0.1666666666666f,
                0,
                new BiomeFilters(emptyList, emptyList),
                true
        ));

        entries.add(new OreEntry(
                "minecraft:granite",
                Collections.singletonList("base_stone_overworld"),
                Distribution.UNIFORM,
                0,
                60,
                64,
                2,
                0,
                new BiomeFilters(emptyList, emptyList),
                true
        ));

    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
