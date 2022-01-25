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

public class CopperOreTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "copper_ore";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:copper_ore",
                Collections.singletonList("STONE_ORE_REPLACEABLES"),
                Distribution.TRIANGLE,
                -16,
                112,
                10,
                16,
                0,
                new BiomeFilters(emptyList, Templates.OVERWORLD),
                true
        ));
        entries.add(new OreEntry(
                "minecraft:copper_ore",
                Collections.singletonList("STONE_ORE_REPLACEABLES"),
                Distribution.TRIANGLE,
                -16,
                112,
                20,
                16,
                0,
                new BiomeFilters(emptyList, Collections.singletonList("minecraft:dripstone_caves")),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
