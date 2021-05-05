package com.ewyboy.oretweaker.json.template.templates.remove.stones;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class RemoveAllStonesNether implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "remove_stones_nether";
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:soul_sand"
        ));
        entries.add(new OreEntry(
                "minecraft:blackstone"
        ));
        entries.add(new OreEntry(
                "minecraft:magma_block"
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
