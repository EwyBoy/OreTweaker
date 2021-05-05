package com.ewyboy.oretweaker.json;

import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.Templates;
import com.ewyboy.oretweaker.util.ModLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;

public class JSONHandler {

    private static final Gson gson = new Gson();
    private static final File JSON_FILE = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/OreTweaker.json");

    public static OreConfig oreConfig = new OreConfig(Templates.GeneratedTemplates.DEFAULT_TEMPLATE.getTemplate());

    public static void setup() {
        if (!JSON_FILE.exists()) {
            ModLogger.info("Creating Ore Tweaker JSON file");
            writeJson(JSON_FILE, oreConfig);
        }
        ModLogger.info("Reading Ore Tweaker JSON file");
        readJson(JSON_FILE);
        ModLogger.info(oreConfig.toString());
    }

    public static boolean containsEntry(OreEntry entry) {
        for (OreEntry ore : oreConfig.getOreConfig()) {
            if (ore.getOre().equals(entry.getOre())) {
                return true;
            }
        }
        return false;
    }

    public static void writeJson(File jsonFile, OreConfig config) {
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJson(File jsonFile) {
        try (Reader reader = new FileReader(jsonFile)) {
            oreConfig = gson.fromJson(reader, OreConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
