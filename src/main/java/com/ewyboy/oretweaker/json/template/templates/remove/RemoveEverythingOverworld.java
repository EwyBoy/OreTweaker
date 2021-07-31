package com.ewyboy.oretweaker.json.template.templates.remove;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class RemoveEverythingOverworld implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "remove_everything_overworld";
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
                "minecraft:coal_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:iron_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:copper_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:gold_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:redstone_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:lapis_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:diamond_ore"
        ));
        entries.add(new OreEntry(
                "minecraft:emerald_ore"
        ));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }

}
