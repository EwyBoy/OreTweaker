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

public class NetherQuartzOreTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "nether_quartz_ore";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:nether_quartz_ore",
                Collections.singletonList("minecraft:netherrack"),
                Distribution.UNIFORM,
                10,
                116,
                14,
                16,
                0,
                new BiomeFilters(emptyList, Templates.NETHER),
                true
        ));
        entries.add(new OreEntry(
                "minecraft:nether_quartz_ore",
                Collections.singletonList("minecraft:netherrack"),
                Distribution.UNIFORM,
                10,
                116,
                14,
                32,
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
