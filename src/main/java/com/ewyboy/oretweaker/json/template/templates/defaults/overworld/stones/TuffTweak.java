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

public class TuffTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "tuff";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:tuff",
                Collections.singletonList("base_stone_overworld"),
                Distribution.UNIFORM,
                -64,
                0,
                32,
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
