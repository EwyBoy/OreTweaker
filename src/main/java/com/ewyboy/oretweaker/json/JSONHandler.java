package com.ewyboy.oretweaker.json;

import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.Templates;
import com.ewyboy.oretweaker.util.ModLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONHandler {

    public static boolean isAutoUpdating = false;

    private static final Gson gson = new Gson();
    public static final File OLD_JSON = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/OreTweaker.json");

    public static OreConfig oreConfig = new OreConfig(new ArrayList<>());

    public static void loadComplete() {
        if (Settings.SETTINGS.regenerateDefaultSettings.get() && !isAutoUpdating) {
            Templates.regenerateDefaultDataFromTemplate();
        }

        Settings.ServerConfig.setFalse(Settings.SETTINGS.regenerateTemplates);
        Settings.ServerConfig.setFalse(Settings.SETTINGS.regenerateDefaultSettings);

        JSONHandler.readAllFiles();
    }

    public static void readAllFiles() {
        try (Stream<Path> paths = Files.walk(Paths.get(FMLPaths.CONFIGDIR.get() + "/oretweaker/data"))) {
            paths.filter(Files :: isRegularFile).forEach(path -> {
                if (path.toString().endsWith(".json")) {
                    ModLogger.info("Reading data: " + path.getFileName());
                    readJson(path.toFile());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModLogger.info(oreConfig.toString());
    }

    private static void autoUpdater() {
        if (OLD_JSON.exists()) {
            isAutoUpdating = true;
            ModLogger.info("Auto updating OreTweaker to v2 data structure!");
            try (Reader reader = new FileReader(OLD_JSON)) {
                OreConfig oldConfig = gson.fromJson(reader, OreConfig.class);
                oldConfig.getOreConfig().forEach(JSONHandler :: generateJSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
            DirectoryHandler.createDirectories(DirectoryHandler.BACKUP_PATH);
            OLD_JSON.renameTo(new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/backup/OreTweaker.json"));
        }
    }

    public static void generateJSON(OreEntry entry) {
        String fileName = entry.getOre().split(":")[1].toLowerCase(Locale.ROOT);
        File file = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/data/" + fileName + ".json");
        List<OreEntry> oreList = new ArrayList<>();

        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                OreConfig config = gson.fromJson(reader, OreConfig.class);
                oreList.addAll(config.getOreConfig());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ModLogger.info("Creating Ore Tweaker Data File: " + fileName);
        oreList.add(entry);
        JSONHandler.writeJson(file, new OreConfig(oreList));
    }

    public static void setup() {
        autoUpdater();
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
            List<OreEntry> newConfig = Stream.concat(oreConfig.getOreConfig().stream(), gson.fromJson(reader, OreConfig.class).getOreConfig().stream()).collect(Collectors.toList());
            oreConfig.setOreConfig(newConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
