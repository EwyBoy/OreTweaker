package com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.objects.biome.BiomeFilters;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AncientDebrisTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "ancient_debris";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:ancient_debris",
                Collections.singletonList("BASE_STONE_NETHER"),
                Distribution.TRIANGLE,
                8,
                24,
                3,
                1,
                1.0F,
                new BiomeFilters(emptyList, Templates.NETHER),
                true
        ));
        entries.add(new OreEntry(
                "minecraft:ancient_debris",
                Collections.singletonList("BASE_STONE_NETHER"),
                Distribution.UNIFORM,
                8,
                118,
                2,
                1,
                1.0F,
                new BiomeFilters(emptyList, Templates.NETHER),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
