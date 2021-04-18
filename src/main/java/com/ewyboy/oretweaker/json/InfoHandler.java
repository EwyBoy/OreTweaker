package com.ewyboy.oretweaker.json;

import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InfoHandler {

    public static final File INFO_FILE = new File(FMLPaths.CONFIGDIR.get() + "/oretweaker/Info.txt");

    public static void setup() {
        createInfoFile();
    }

    private static void createInfoFile() {
        try {
            if (InfoHandler.INFO_FILE.createNewFile()) {
                ModLogger.info("Creating Seed Drop information file: " + InfoHandler.INFO_FILE.getName());
                writeInfoFile();
            }
        } catch (IOException e) {
            ModLogger.error("Failed to create information file: " + InfoHandler.INFO_FILE.getName());
            e.printStackTrace();
        }
    }

    private static void writeInfoFile() {
        try {
            FileWriter writer = new FileWriter(INFO_FILE);

            writer.write("Seed Drop - Information");
            writer.write("\n");

            writer.write("Edit the OreTweaker.json file to add or remove entries from the grass loot table.");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
