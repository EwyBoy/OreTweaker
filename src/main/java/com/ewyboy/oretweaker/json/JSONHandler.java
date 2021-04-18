package com.ewyboy.oretweaker.json;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.util.ModLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {

    private static final Gson gson = new Gson();
    public static final File JSON_FILE = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/OreTweaker.json");

    private static final List<OreEntry> ORE_ENTRIES = new ArrayList<>(); static {
        ORE_ENTRIES.add(new OreEntry(
                "minecraft:coal_ore",
                "minecraft:stone",
                1,
                128,
                16,
                20
        ));
        ORE_ENTRIES.add(new OreEntry(
                "minecraft:iron_ore",
                "minecraft:stone",
                1,
                64,
                8,
                20
        ));
        ORE_ENTRIES.add(new OreEntry(
                "minecraft:gold_ore",
                "minecraft:stone",
                1,
                32,
                8,
                2
        ));
        ORE_ENTRIES.add(new OreEntry(
                "minecraft:redstone_ore",
                "minecraft:stone",
                1,
                16,
                7,
                8
        ));
        ORE_ENTRIES.add(new OreEntry(
                "minecraft:lapis_ore",
                "minecraft:stone",
                1,
                30,
                10,
                4

        ));
        ORE_ENTRIES.add(new OreEntry(
                "minecraft:diamond_ore",
                "minecraft:stone",
                1,
                16,
                7,
                1
        ));
    }

    public static OreConfig oreConfig = new OreConfig(ORE_ENTRIES);

    public static void setup() {
        createDirectory();
        if(!JSON_FILE.exists()) {
            ModLogger.info("Creating Seed Drop config JSON file");
            writeJson(JSON_FILE);
        }
        ModLogger.info("Reading Seed Drop config JSON file");
        readJson(JSON_FILE);
    }

    public static void reload() {
        writeJson(JSON_FILE);
        readJson(JSON_FILE);
    }

    public static boolean containsEntry(OreEntry entry) {
        for (OreEntry ore : oreConfig.getOreConfig()) {
            if (ore.getOre().equals(entry.getOre())) {
                return true;
            }
        }
        return false;
    }

    public static boolean addEntry(OreEntry entry) {
        if (!containsEntry(entry)) {
            oreConfig.getOreConfig().add(entry);
            reload();
            return true;
        }
        return false;
    }

    public static boolean removeEntry(String oreName) {
        if (containsEntry(new OreEntry(oreName, null, null, null, null, null))) {
            oreConfig.getOreConfig().removeIf(drop -> drop.getOre().equals(oreName));
            reload();
            return true;
        }
        return false;
    }

    public static void writeJson(File jsonFile) {
        try(Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(oreConfig, writer);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJson(File jsonFile) {
        try(Reader reader = new FileReader(jsonFile)) {
            oreConfig = gson.fromJson(reader, OreConfig.class);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path seedDropConfigPath = Paths.get(configPath.toAbsolutePath().toString(), OreTweaker.MOD_ID);
        try {
            ModLogger.info("Creating Ore Tweaker config directory");
            Files.createDirectory(seedDropConfigPath);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            ModLogger.error("Failed to create Ore Tweaker config directory", e);
        }
    }

}
