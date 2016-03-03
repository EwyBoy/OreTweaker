package com.ewyboy.oretweaker.loaders;

import net.minecraftforge.common.config.Configuration;
import java.io.File;
import static com.ewyboy.oretweaker.utility.Reference.OreNames.OreNames;

public class ConfigLoader {

    public static boolean disableCoal, disableIron, disableGold, disableDiamond, disableRedstone, disableLapis, disableDirt, disableGravel;
    public static boolean disableOres[] = {disableCoal, disableIron, disableGold, disableDiamond, disableRedstone, disableLapis, disableDirt, disableGravel};

    public static boolean enableCustomOreGenCoal, enableCustomOreGenIron, enableCustomOreGenGold, enableCustomOreGenDiamond, enableCustomOreGenRedstone, enableCustomOreGenLapis, enableCustomOreGenDirt, enableCustomOreGenGravel;
    public static boolean enableCustomOreGeneration[] = {enableCustomOreGenCoal, enableCustomOreGenIron, enableCustomOreGenGold, enableCustomOreGenDiamond, enableCustomOreGenRedstone, enableCustomOreGenLapis, enableCustomOreGenDirt, enableCustomOreGenGravel};

    public static int minCoalVeinSize, minIronVeinSize, minGoldVeinSize, minDiamondVeinSize, minRedstoneVeinSize, minLapisVeinSize, minDirtVeinSize, minGravelVeinSize;
    public static int minVeinSizes[] = {minCoalVeinSize, minIronVeinSize, minGoldVeinSize, minDiamondVeinSize, minRedstoneVeinSize, minLapisVeinSize, minDirtVeinSize, minGravelVeinSize};

    public static int maxCoalVeinSize, maxIronVeinSize, maxGoldVeinSize, maxDiamondVeinSize, maxRedstoneVeinSize, maxLapisVeinSize, maxDirtVeinSize, maxGravelVeinSize;
    public static int maxVeinSizes[] = {maxCoalVeinSize, maxIronVeinSize, maxGoldVeinSize, maxDiamondVeinSize, maxRedstoneVeinSize, maxLapisVeinSize, maxDirtVeinSize, maxGravelVeinSize};

    public static int minCoalYLevel, minIronYLevel, minGoldYLevel, minDiamondYLevel, minRedstoneYLevel, minLapisYLevel, minDirtYLevel, minGravelYLevel;
    public static int minVeinYLevels[] = {minCoalYLevel, minIronYLevel, minGoldYLevel, minDiamondYLevel, minRedstoneYLevel, minLapisYLevel, minDirtYLevel, minGravelYLevel};

    public static int maxCoalYLevel, maxIronYLevel, maxGoldYLevel, maxDiamondYLevel, maxRedstoneYLevel, maxLapisYLevel, maxDirtYLevel, maxGravelYLevel;
    public static int maxVeinYLevels[] = {maxCoalYLevel, maxIronYLevel, maxGoldYLevel, maxDiamondYLevel, maxRedstoneYLevel, maxLapisYLevel, maxDirtYLevel, maxGravelYLevel};

    public static int spawnRateCoal, spawnRateIron, spawnRateGold, spawnRateDiamond, spawnRateRedstone, spawnRateLapis, spawnRateDirt, spawnRateGravel;
    public static int spawnRates[] = {spawnRateCoal, spawnRateIron, spawnRateGold, spawnRateDiamond, spawnRateRedstone, spawnRateLapis, spawnRateDirt, spawnRateGravel};


    public static void init (File file) {
        Configuration config = new Configuration(file);

        config.load();
            for (int i=0; i < disableOres.length; i++) {
                disableOres[i] = config.getBoolean("Disable " + OreNames[i], OreNames[i] + " Tweaks", false, "Set to true to disable vanilla generation of " + OreNames[i]);
                enableCustomOreGeneration[i] = config.getBoolean("Custom OreGen: " + OreNames[i], OreNames[i] + " Tweaks", false, "Set to true to enable custom ore generation for " + OreNames[i]);

                minVeinSizes[i] = config.getInt("Minimum Vein Size: " + OreNames[i], OreNames[i] + " Tweaks", 0, 0, Integer.MAX_VALUE, "Sets the minimum vein size value for " + OreNames[i]);
                maxVeinSizes[i] = config.getInt("Maximum Vein Size: " + OreNames[i], OreNames[i] + " Tweaks", 0, 0, Integer.MAX_VALUE, "Sets the maximum vein size value for " + OreNames[i]);

                minVeinYLevels[i] = config.getInt("Minimum Spawn Level: " + OreNames[i], OreNames[i] + " Tweaks", 0, 1, 256, "Sets the minimum vein spawn level [Y-level] for " + OreNames[i]);
                maxVeinYLevels[i] = config.getInt("Maximum Spawn Level: " + OreNames[i], OreNames[i] + " Tweaks", 0, 1, 256, "Sets the maximum vein spawn level [Y-level] for " + OreNames[i]);

                spawnRates[i] = config.getInt("Spawn Rate: " + OreNames[i], OreNames[i] + " Tweaks", 0, 0, Integer.MAX_VALUE, "Sets the spawn rate of the ore vein [Amount of veins per chunk] for " + OreNames[i]);
            }
        config.save();
    }
}
