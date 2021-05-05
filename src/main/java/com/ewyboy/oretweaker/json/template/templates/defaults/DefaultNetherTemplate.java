package com.ewyboy.oretweaker.json.template.templates.defaults;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class DefaultNetherTemplate implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "default_nether";
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry(
                "minecraft:soul_sand",
                "minecraft:netherrack",
                1,
                32,
                12,
                12,
                emptyList,
                emptyList
        ));
        entries.add(new OreEntry(
                "minecraft:gravel",
                "minecraft:netherrack",
                5,
                37,
                32,
                2,
                emptyList,
                emptyList
        ));
        entries.add(new OreEntry(
                "minecraft:nether_quartz_ore",
                "minecraft:netherrack",
                10,
                246,
                14,
                10,
                emptyList,
                emptyList
        ));

        entries.add(new OreEntry(
                "minecraft:nether_quartz_ore",
                "minecraft:netherrack",
                10,
                246,
                14,
                32,
                emptyList,
                emptyList
        ));

        entries.add(new OreEntry(
                "minecraft:nether_gold_ore",
                "minecraft:netherrack",
                10,
                118,
                10,
                10,
                emptyList,
                emptyList
        ));

        entries.add(new OreEntry(
                "minecraft:nether_gold_ore",
                "minecraft:netherrack",
                10,
                118,
                10,
                20,
                emptyList,
                emptyList
        ));

        entries.add(new OreEntry(
                "minecraft:blackstone",
                "minecraft:netherrack",
                5,
                31,
                10,
                2,
                emptyList,
                emptyList
        ));

        entries.add(new OreEntry(
                "minecraft:magma_block",
                "minecraft:netherrack",
                26,
                36,
                10,
                15,
                emptyList,
                emptyList
        ));

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
