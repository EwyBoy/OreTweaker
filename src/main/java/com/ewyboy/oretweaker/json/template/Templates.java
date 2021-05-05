package com.ewyboy.oretweaker.json.template;

import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.template.templates.defaults.DefaultOverworldTemplate;
import com.ewyboy.oretweaker.json.template.templates.defaults.DefaultTemplate;
import com.ewyboy.oretweaker.json.template.templates.other.FuckSilverfishTemplate;
import com.ewyboy.oretweaker.json.template.templates.defaults.DefaultNetherTemplate;
import com.ewyboy.oretweaker.json.template.templates.remove.RemoveEverything;
import com.ewyboy.oretweaker.json.template.templates.remove.RemoveEverythingNether;
import com.ewyboy.oretweaker.json.template.templates.remove.RemoveEverythingOverworld;
import com.ewyboy.oretweaker.json.template.templates.remove.ores.RemoveAllOres;
import com.ewyboy.oretweaker.json.template.templates.remove.ores.RemoveAllOresNether;
import com.ewyboy.oretweaker.json.template.templates.remove.ores.RemoveAllOresOverworld;
import com.ewyboy.oretweaker.json.template.templates.remove.other.RemoveIgneousRocks;
import com.ewyboy.oretweaker.json.template.templates.remove.stones.RemoveAllStones;
import com.ewyboy.oretweaker.json.template.templates.remove.stones.RemoveAllStonesNether;
import com.ewyboy.oretweaker.json.template.templates.remove.stones.RemoveAllStonesOverworld;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.lang.reflect.Field;

public class Templates {

    public static final class GeneratedTemplates {
        public static final DefaultTemplate DEFAULT_TEMPLATE = new DefaultTemplate();
        public static final DefaultNetherTemplate DEFAULT_NETHER_TEMPLATE = new DefaultNetherTemplate();
        public static final DefaultOverworldTemplate DEFAULT_OVERWORLD_TEMPLATE = new DefaultOverworldTemplate();

        public static final RemoveAllOres REMOVE_ALL_ORES = new RemoveAllOres();
        public static final RemoveAllOresNether REMOVE_ALL_ORES_NETHER = new RemoveAllOresNether();
        public static final RemoveAllOresOverworld REMOVE_ALL_ORES_OVERWORLD = new RemoveAllOresOverworld();

        public static final RemoveAllStones REMOVE_ALL_STONES = new RemoveAllStones();
        public static final RemoveAllStonesNether REMOVE_ALL_STONES_NETHER = new RemoveAllStonesNether();
        public static final RemoveAllStonesOverworld REMOVE_ALL_STONES_OVERWORLD = new RemoveAllStonesOverworld();

        public static final RemoveEverything REMOVE_EVERYTHING = new RemoveEverything();
        public static final RemoveEverythingNether REMOVE_EVERYTHING_NETHER = new RemoveEverythingNether();
        public static final RemoveEverythingOverworld REMOVE_EVERYTHING_OVERWORLD = new RemoveEverythingOverworld();

        public static final RemoveIgneousRocks REMOVE_IGNEOUS_ROCKS = new RemoveIgneousRocks();
        public static final FuckSilverfishTemplate FUCK_SILVERFISH_TEMPLATE = new FuckSilverfishTemplate();
    }

    public static void setup() {
        registerTemplates();
    }

    private static void registerTemplates() {
        try {
            for (Field field : Templates.GeneratedTemplates.class.getDeclaredFields()) {
                Object object = field.get(null);
                if (object instanceof ITemplate) {
                    ITemplate template = (ITemplate) object;
                    generateTemplate(template);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateTemplate(ITemplate template) {
        File templateFile = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/templates/" + template.templateName() + ".json");
        if (!templateFile.exists()) {
            template.buildTemplateEntries();
            ModLogger.info("Creating Ore Tweaker Template file: " + template.templateName());
            JSONHandler.writeJson(templateFile, new OreConfig(template.getTemplate()));
        } else if (template instanceof DefaultTemplate) {
            template.buildTemplateEntries();
        }
    }

}
