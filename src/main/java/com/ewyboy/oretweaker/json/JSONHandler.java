package com.ewyboy.oretweaker.json;

import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.objects.OreEntry;
import com.ewyboy.oretweaker.json.template.Templates;
import com.ewyboy.oretweaker.util.ModLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.gson.stream.JsonToken.END_DOCUMENT;

public class JSONHandler {

    private static final Gson gson = new Gson();

    public static OreConfig oreConfig = new OreConfig(new ArrayList<>());

    public static void loadComplete() {
        if (Settings.SETTINGS.regenerateDefaultSettings.get()) {
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
                    try {
                        if (!isJsonValid(path.toFile(), path.getFileName().toString())) {
                            ModLogger.error("Invalid JSON: " + path.getFileName());
                            return;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ModLogger.info("Reading data: " + path.getFileName());
                    readJson(path.toFile());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModLogger.info(oreConfig.toString());
    }

    private static boolean isJsonValid(File json, String fileName) throws IOException {
        try (Reader reader = new FileReader(json)) {
            return isJsonValid(new JsonReader(reader), fileName);
        }
    }

    private static boolean isJsonValid(final JsonReader jsonReader, String fileName) throws IOException {
        try {
            JsonToken token;
            while ((token = jsonReader.peek()) != END_DOCUMENT && token != null) {
                switch (token) {
                    case BEGIN_ARRAY -> jsonReader.beginArray();
                    case END_ARRAY -> jsonReader.endArray();
                    case BEGIN_OBJECT -> jsonReader.beginObject();
                    case END_OBJECT -> jsonReader.endObject();
                    case NAME -> jsonReader.nextName();
                    case STRING, NUMBER, BOOLEAN, NULL -> jsonReader.skipValue();
                    default -> throw new AssertionError(token);
                }
            }
            return true;
        } catch ( final MalformedJsonException exception ) {
            throw new MalformedJsonException(
                    "Malformed JSON " + fileName + ":\n" +
                    "Make sure your JSON is formatted correctly!\n" +
                    "You can read more about JSON formatting here: https://github.com/EwyBoy/OreTweaker/wiki/Validating-JSON-File\n\n" +
                    "Error: " + exception.getLocalizedMessage() + " in " + fileName
            );
        }
    }

    public static boolean containsEntry(OreEntry entry) {
        for (OreEntry ore : oreConfig.getOreConfig()) {
            if (ore.getOre().equals(entry.getOre()) && ore.isReplace()) {
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
