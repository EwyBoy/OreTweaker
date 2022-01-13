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

public class InfestedTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

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
                Collections.singletonList("stone_ore_replaceables"),
                Distribution.UNIFORM,
                -64,
                63,
                9,
                7,
                0,
                new BiomeFilters(emptyList, Templates.BADLANDS),
                true
        ));
        entries.add(new OreEntry(
                "minecraft:infested_deepslate",
                Collections.singletonList("deepslate_ore_replaceables"),
                Distribution.UNIFORM,
                -64,
                63,
                9,
                7,
                0,
                new BiomeFilters(emptyList, Templates.BADLANDS),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
