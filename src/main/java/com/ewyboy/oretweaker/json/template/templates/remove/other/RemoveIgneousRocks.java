package com.ewyboy.oretweaker.json.template.templates.remove.other;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class RemoveIgneousRocks implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "remove_igneous_rocks";
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:granite"
        ));
        entries.add(new OreEntry(
                "minecraft:diorite"
        ));
        entries.add(new OreEntry(
                "minecraft:andesite"
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }

}
