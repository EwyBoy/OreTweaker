package com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
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
                "minecraft:netherrack",
                8,
                120,
                1,
                1,
                emptyList,
                emptyList
        ));
        entries.add(new OreEntry(
                "minecraft:ancient_debris",
                "minecraft:netherrack",
                8,
                24,
                2,
                1,
                emptyList,
                emptyList
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
