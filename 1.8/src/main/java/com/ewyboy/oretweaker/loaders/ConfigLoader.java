package com.ewyboy.oretweaker.loaders;

import net.minecraftforge.common.config.Configuration;
import java.io.File;
import static com.ewyboy.oretweaker.utility.Reference.OreNames.OreNames;

public class ConfigLoader {

    private static boolean disableCoal, disableIron, disableGold, disableDiamond, disableRedstone, disableLapis, disableEmerald, disableDirt, disableGravel, disableAndesite, disableGranite, disableDiorite;
    public static boolean disableOres[] = {
            disableCoal,
            disableIron,
            disableGold,
            disableDiamond,
            disableRedstone,
            disableLapis,
            disableEmerald,
            disableDirt,
            disableGravel,
            disableAndesite,
            disableGranite,
            disableDiorite
    };

    private static boolean enableCustomOreGenCoal, enableCustomOreGenIron, enableCustomOreGenGold, enableCustomOreGenDiamond, enableCustomOreGenRedstone, enableCustomOreGenLapis, enableCustomOreGenEmerald, enableCustomOreGenDirt, enableCustomOreGenGravel, enableCustomOreGenAndesite, enableCustomOreGenGranite, enableCustomOreGenDiorite;
    public static boolean enableCustomOreGeneration[] = {
            enableCustomOreGenCoal,
            enableCustomOreGenIron,
            enableCustomOreGenGold,
            enableCustomOreGenDiamond,
            enableCustomOreGenRedstone,
            enableCustomOreGenLapis,
            enableCustomOreGenEmerald,
            enableCustomOreGenDirt,
            enableCustomOreGenGravel,
            enableCustomOreGenAndesite,
            enableCustomOreGenGranite,
            enableCustomOreGenDiorite
    };

    private static int minVeinSizeCoal, minVeinSizeIron, minVeinSizeGold, minVeinSizeDiamond, minVeinSizeRedstone, minVeinSizeLapis, minVeinSizeEmerald, minVeinSizeDirt, minVeinSizeGravel, minVeinSizeAndesite, minVeinSizeGranite, minVeinSizeDiorite;
    public static int minVeinSizes[] = {
            minVeinSizeCoal,
            minVeinSizeIron,
            minVeinSizeGold,
            minVeinSizeDiamond,
            minVeinSizeRedstone,
            minVeinSizeLapis,
            minVeinSizeEmerald,
            minVeinSizeDirt,
            minVeinSizeGravel,
            minVeinSizeAndesite,
            minVeinSizeGranite,
            minVeinSizeDiorite
    };

    private static int maxVeinSizeCoal, maxVeinSizeIron, maxVeinSizeGold, maxVeinSizeDiamond, maxVeinSizeRedstone, maxVeinSizeLapis, maxVeinSizeEmerald, maxVeinSizeDirt, maxVeinSizeGravel, maxVeinSizeAndesite, maxVeinSizeGranite, maxVeinSizeDiorite;
    public static int maxVeinSizes[] = {
            maxVeinSizeCoal,
            maxVeinSizeIron,
            maxVeinSizeGold,
            maxVeinSizeDiamond,
            maxVeinSizeRedstone,
            maxVeinSizeLapis,
            maxVeinSizeEmerald,
            maxVeinSizeDirt,
            maxVeinSizeGravel,
            maxVeinSizeAndesite,
            maxVeinSizeGranite,
            maxVeinSizeDiorite
    };

    private static int minLevelCoal, minLevelIron, minLevelGold, minLevelDiamond, minLevelRedstone, minLevelLapis, minLevelEmerald, minLevelDirt, minLevelGravel, minLevelAndesite, minLevelGranite, minLevelDiorite;
    public static int minVeinLevels[] = {
            minLevelCoal,
            minLevelIron,
            minLevelGold,
            minLevelDiamond,
            minLevelRedstone,
            minLevelLapis,
            minLevelEmerald,
            minLevelDirt,
            minLevelGravel,
            minLevelAndesite,
            minLevelGranite,
            minLevelDiorite
    };

    private static int maxLevelCoal, maxLevelIron, maxLevelGold, maxLevelDiamond, maxLevelRedstone, maxLevelLapis, maxLevelEmerald, maxLevelDirt, maxLevelGravel, maxLevelAndesite, maxLevelGranite, maxLevelDiorite;
    public static int maxVeinLevels[] = {
            maxLevelCoal,
            maxLevelIron,
            maxLevelGold,
            maxLevelDiamond,
            maxLevelRedstone,
            maxLevelLapis,
            maxLevelEmerald,
            maxLevelDirt,
            maxLevelGravel,
            maxLevelAndesite,
            maxLevelGranite,
            maxLevelDiorite
    };

    private static int spawnRateCoal, spawnRateIron, spawnRateGold, spawnRateDiamond, spawnRateRedstone, spawnRateLapis, spawnRateEmerald, spawnRateDirt, spawnRateGravel, spawnRateAndesite, spawnRateGranite, spawnRateDiorite;
    public static int spawnRates[] = {
            spawnRateCoal,
            spawnRateIron,
            spawnRateGold,
            spawnRateDiamond,
            spawnRateRedstone,
            spawnRateLapis,
            spawnRateEmerald,
            spawnRateDirt,
            spawnRateGravel,
            spawnRateAndesite,
            spawnRateGranite,
            spawnRateDiorite
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
