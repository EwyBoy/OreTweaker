package com.ewyboy.oretweaker.json.template.templates.other;

import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.ITemplate;

import java.util.LinkedList;
import java.util.List;

public class FuckSilverfishTemplate implements ITemplate {

    private final List<OreEntry> entries = new LinkedList<>();

    @Override
    public String templateName() {
        return "fuck_silverfish";
    }

    @Override
    public void buildTemplateEntries() {
        entries.add(new OreEntry("minecraft:infested"));
    }

    @Override
    public List<OreEntry> getTemplate() {
        return entries;
    }
}
