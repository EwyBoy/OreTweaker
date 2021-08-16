package com.ewyboy.oretweaker.json.template.templates.remove.stones;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;
import com.ewyboy.oretweaker.json.template.Templates;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class RemoveAllStonesOverworld implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "remove_stones_overworld";
    }

    @Override
    public Path templateDirectory() {
        return Templates.Directories.REMOVE_PATH;
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:dirt"
        ));
        entries.add(new OreEntry(
                "minecraft:gravel"
        ));
        entries.add(new OreEntry(
                "minecraft:granite"
        ));
        entries.add(new OreEntry(
                "minecraft:diorite"
        ));
        entries.add(new OreEntry(
                "minecraft:andesite"
        ));
        entries.add(new OreEntry(
                "minecraft:tuff"
        ));
        entries.add(new OreEntry(
                "minecraft:deepslate"
        ));
        entries.add(new OreEntry(
                "minecraft:infested_stone"
        ));
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
