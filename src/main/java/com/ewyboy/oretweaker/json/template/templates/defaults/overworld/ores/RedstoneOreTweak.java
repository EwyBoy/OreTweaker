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

public class RedstoneOreTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "redstone_ore";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:redstone_ore",
                Collections.singletonList("STONE_ORE_REPLACEABLES"),
                Distribution.UNIFORM,
                -64,
                15,
                4,
                8,
                0,
                new BiomeFilters(emptyList, Templates.OVERWORLD),
                true
        ));

        entries.add(new OreEntry(
                "minecraft:redstone_ore",
                Collections.singletonList("STONE_ORE_REPLACEABLES"),
                Distribution.TRIANGLE,
                -96,
                -32,
                8,
                8,
                0,
                new BiomeFilters(emptyList, Templates.OVERWORLD),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
