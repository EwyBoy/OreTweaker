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
                ModLogger.info("Creating Ore Tweaker information file: " + InfoHandler.INFO_FILE.getName());
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
            writer.write("##    Ore Tweaker - Information   ##");
            writer.write("\n");
            writer.write("\nEdit the OreTweaker.json file generated on launch to tweak ore generation");
            writer.write("\nBy default the file mimics vanilla world generation settings.");
            writer.write("\nCheck out more info on the wiki at https://github.com/EwyBoy/OreTweaker/wiki");
            writer.write("\n");
            writer.write("\nBugs can be reported here at https://github.com/EwyBoy/OreTweaker/issues");
            writer.write("\n");
            writer.write("\n-Ewy");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
