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

public class DiamondOreTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "diamond_ore";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:diamond_ore",
                Collections.singletonList("stone_ore_replaceables"),
                Distribution.TRIANGLE,
                -144,
                16,
                4,
                7,
                0.5f,
                new BiomeFilters(emptyList, emptyList),
                true
        ));
        entries.add(new OreEntry(
                "minecraft:diamond_ore",
                Collections.singletonList("stone_ore_replaceables"),
                Distribution.TRIANGLE,
                -144,
                16,
                12,
                0.1111111111111f,
                0.7f,
                new BiomeFilters(emptyList, emptyList),
                true
        ));
        entries.add(new OreEntry(
                "minecraft:diamond_ore",
                Collections.singletonList("stone_ore_replaceables"),
                Distribution.TRIANGLE,
                -144,
                16,
                8,
                4,
                1.0f,
                new BiomeFilters(emptyList, emptyList),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
