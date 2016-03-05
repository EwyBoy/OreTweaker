package com.ewyboy.oretweaker.loaders;

import net.minecraftforge.common.config.Configuration;
import java.io.File;
import static com.ewyboy.oretweaker.utility.Reference.OreNames.OreNames;

public class ConfigLoader {

    public static boolean disableCoal, disableIron, disableGold, disableDiamond, disableRedstone, disableLapis, disableDirt, disableGravel, disableEmerald;
    public static boolean disableOres[] = {
            disableCoal,
            disableIron,
            disableGold,
            disableDiamond,
            disableRedstone,
            disableLapis,
            disableDirt,
            disableGravel,
            disableEmerald
    };

    public static boolean enableCustomOreGenCoal, enableCustomOreGenIron, enableCustomOreGenGold, enableCustomOreGenDiamond, enableCustomOreGenRedstone, enableCustomOreGenLapis, enableCustomOreGenDirt, enableCustomOreGenGravel, enableCustomOreGenEmerald;
    public static boolean enableCustomOreGeneration[] = {
            enableCustomOreGenCoal,
            enableCustomOreGenIron,
            enableCustomOreGenGold,
            enableCustomOreGenDiamond,
            enableCustomOreGenRedstone,
            enableCustomOreGenLapis,
            enableCustomOreGenDirt,
            enableCustomOreGenGravel,
            enableCustomOreGenEmerald
    };

    public static int minVeinSizeCoal, minVeinSizeIron, minVeinSizeGold, minVeinSizeDiamond, minVeinSizeRedstone, minVeinSizeLapis, minVeinSizeDirt, minVeinSizeGravel, minVeinSizeEmerald;
    public static int minVeinSizes[] = {
            minVeinSizeCoal,
            minVeinSizeIron,
            minVeinSizeGold,
            minVeinSizeDiamond,
            minVeinSizeRedstone,
            minVeinSizeLapis,
            minVeinSizeDirt,
            minVeinSizeGravel,
            minVeinSizeEmerald
    };

    public static int maxVeinSizeCoal, maxVeinSizeIron, maxVeinSizeGold, maxVeinSizeDiamond, maxVeinSizeRedstone, maxVeinSizeLapis, maxVeinSizeDirt, maxVeinSizeGravel, maxVeinSizeEmerald;
    public static int maxVeinSizes[] = {
            maxVeinSizeCoal,
            maxVeinSizeIron,
            maxVeinSizeGold,
            maxVeinSizeDiamond,
            maxVeinSizeRedstone,
            maxVeinSizeLapis,
            maxVeinSizeDirt,
            maxVeinSizeGravel,
            maxVeinSizeEmerald
    };

    public static int minLevelCoal, minLevelIron, minLevelGold, minLevelDiamond, minLevelRedstone, minLevelLapis, minLevelDirt, minLevelGravel, minLevelEmerald;
    public static int minVeinLevels[] = {
            minLevelCoal,
            minLevelIron,
            minLevelGold,
            minLevelDiamond,
            minLevelRedstone,
            minLevelLapis,
            minLevelDirt,
            minLevelGravel,
            minLevelEmerald
    };

    public static int maxLevelCoal, maxLevelIron, maxLevelGold, maxLevelDiamond, maxLevelRedstone, maxLevelLapis, maxLevelDirt, maxLevelGravel, maxLevelEmerald;
    public static int maxVeinLevels[] = {
            maxLevelCoal,
            maxLevelIron,
            maxLevelGold,
            maxLevelDiamond,
            maxLevelRedstone,
            maxLevelLapis,
            maxLevelDirt,
            maxLevelGravel,
            maxLevelEmerald
    };

    public static int spawnRateCoal, spawnRateIron, spawnRateGold, spawnRateDiamond, spawnRateRedstone, spawnRateLapis, spawnRateDirt, spawnRateGravel, spawnRateEmerald;
    public static int spawnRates[] = {
            spawnRateCoal,
            spawnRateIron,
            spawnRateGold,
            spawnRateDiamond,
            spawnRateRedstone,
            spawnRateLapis,
            spawnRateDirt,
            spawnRateGravel,
            spawnRateEmerald
    };


    public static void init (File file) {
        Configuration config = new Configuration(file);

        config.load();
            for (int i=0; i < disableOres.length; i++) {
                disableOres[i] = config.getBoolean("Disable " + OreNames[i], OreNames[i] + " Tweaks", false, "Set to true to disable vanilla generation of " + OreNames[i]);
                enableCustomOreGeneration[i] = config.getBoolean("Custom OreGen: " + OreNames[i], OreNames[i] + " Tweaks", false, "Set to true to enable custom ore generation for " + OreNames[i]);

                minVeinSizes[i] = config.getInt("Minimum Vein Size: " + OreNames[i], OreNames[i] + " Tweaks", 0, 0, Integer.MAX_VALUE, "Sets the minimum vein size value for " + OreNames[i]);
                maxVeinSizes[i] = config.getInt("Maximum Vein Size: " + OreNames[i], OreNames[i] + " Tweaks", 0, 0, Integer.MAX_VALUE, "Sets the maximum vein size value for " + OreNames[i]);

                minVeinLevels[i] = config.getInt("Minimum Spawn Level: " + OreNames[i], OreNames[i] + " Tweaks", 0, 1, 256, "Sets the minimum vein spawn level [Y-level] for " + OreNames[i]);
                maxVeinLevels[i] = config.getInt("Maximum Spawn Level: " + OreNames[i], OreNames[i] + " Tweaks", 0, 1, 256, "Sets the maximum vein spawn level [Y-level] for " + OreNames[i]);

                spawnRates[i] = config.getInt("Spawn Rate: " + OreNames[i], OreNames[i] + " Tweaks", 0, 0, Integer.MAX_VALUE, "Sets the spawn rate of the ore vein [Amount of veins per chunk] for " + OreNames[i]);
            }
        config.save();
    }
}
