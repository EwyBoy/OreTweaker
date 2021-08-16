package com.ewyboy.oretweaker.json;

import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.objects.OreEntry;
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

    private static final Gson gson = new Gson();
    public static final File OLD_JSON = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/OreTweaker.json");

    public static OreConfig oreConfig = new OreConfig(new ArrayList<>());

    private static void readAllFiles() {
        try (Stream<Path> paths = Files.walk(Paths.get(FMLPaths.CONFIGDIR.get() + "/oretweaker/data"))) {
            paths.filter(Files :: isRegularFile).forEach(path -> {
                ModLogger.info("Reading data: " + path.getFileName());
                readJson(path.toFile());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void autoUpdater() {
        if (OLD_JSON.exists()) {
            try (Reader reader = new FileReader(OLD_JSON)) {
                OreConfig oldConfig = gson.fromJson(reader, OreConfig.class);
                oldConfig.getOreConfig().forEach(JSONHandler :: generateJSON);
                // TODO See if we can delete right here
                OLD_JSON.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        ModLogger.info("Creating Ore Tweaker Template file: " + fileName);
        oreList.add(entry);
        JSONHandler.writeJson(file, new OreConfig(oreList));
    }

    public static void setup() {
        ModLogger.info("Reading Ore Tweaker JSON file");
        autoUpdater();
        readAllFiles();
        ModLogger.info(oreConfig.toString());
    }

    public static void reload() {
        setup();
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
