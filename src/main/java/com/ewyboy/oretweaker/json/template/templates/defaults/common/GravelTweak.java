package com.ewyboy.oretweaker.json.template.templates.defaults.common;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GravelTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "gravel";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:gravel",
                Collections.singletonList("BASE_STONE_OVERWORLD"),
                Distribution.UNIFORM,
                -64,
                256,
                33,
                14,
                0,
                new BiomeFilters(emptyList, Templates.OVERWORLD),
                true
        ));

        entries.add(new OreEntry(
                "minecraft:gravel",
                Collections.singletonList("minecraft:netherrack"),
                Distribution.UNIFORM,
                5,
                41,
                33,
                2,
                0,
                new BiomeFilters(emptyList, Templates.NETHER),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
