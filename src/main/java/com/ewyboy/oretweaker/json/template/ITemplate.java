package com.ewyboy.oretweaker.json.template;

import com.ewyboy.oretweaker.json.objects.OreEntry;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface ITemplate {

    List<String> emptyList = Collections.emptyList();

    public String templateName();

    public Path templateDirectory();

    public void buildTemplateEntries();

    public List<OreEntry> getTemplate();

}
