package com.ewyboy.oretweaker.json.template.templates.remove;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class RemoveEverythingNether implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "remove_everything_nether";
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:soul_sand"
        ));
        entries.add(new OreEntry(
                "minecraft:gravel"
        ));
        entries.add(new OreEntry(
                "minecraft:nether_quartz_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:nether_gold_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:blackstone"
        ));
        entries.add(new OreEntry(
                "minecraft:magma_block"
        ));
        entries.add(new OreEntry(
                "minecraft:ancient_debris"
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}

