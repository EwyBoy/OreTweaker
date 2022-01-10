package com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.objects.spawn.BiomeFilter;
import com.ewyboy.oretweaker.json.objects.spawn.DimensionFilter;
import com.ewyboy.oretweaker.json.objects.ore.Distribution;
import com.ewyboy.oretweaker.json.objects.spawn.SpawnFilter;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MagmaBlockTweak implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "magma_block";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.DEFAULTS_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:magma_block",
                Collections.singletonList("minecraft:netherrack"),
                Distribution.UNIFORM,
                26,
                36,
                10,
                15,
                0,
                new SpawnFilter(
                        new BiomeFilter(emptyList, emptyList),
                        new DimensionFilter(emptyList, emptyList)
                ),
                true
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
