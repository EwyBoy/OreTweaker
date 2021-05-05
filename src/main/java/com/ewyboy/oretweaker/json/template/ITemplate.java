package com.ewyboy.oretweaker.json.template;

import com.ewyboy.oretweaker.json.objects.OreEntry;

import java.util.LinkedList;
import java.util.List;

public interface ITemplate {

    List<String> emptyList = new LinkedList<>();

    public String templateName();

    public void buildTemplateEntries();

    public List<OreEntry> getTemplate();

}
