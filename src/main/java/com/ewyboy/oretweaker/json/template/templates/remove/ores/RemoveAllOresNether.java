package com.ewyboy.oretweaker.json.template.templates.remove.ores;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class RemoveAllOresNether implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "remove_ores_nether";
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:nether_quartz_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:nether_gold_ore"
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