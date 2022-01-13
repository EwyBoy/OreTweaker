package com.ewyboy.oretweaker.json.template.templates.defaults.overworld.ores;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GoldOreTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "gold_ore";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:gold_ore",
                Collections.singletonList("stone_ore_replaceables"),
                Distribution.UNIFORM,
                1,
                32,
                9,
                2,
                0,
                new BiomeFilters(emptyList, emptyList),
                true
        ));

        entries.add(new OreEntry(
                "minecraft:gold_ore",
                Collections.singletonList("stone_ore_replaceables"),
                Distribution.UNIFORM,
                1,
                32,
                9,
                2,
                0.5f,
                new BiomeFilters(emptyList, emptyList),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
