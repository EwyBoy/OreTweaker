package com.ewyboy.oretweaker.json.template;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.DirectoryHandler;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.template.templates.defaults.common.GravelTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore.AncientDebrisTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore.NetherGoldOreTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore.NetherQuartzOreTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone.BlackstoneTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone.MagmaBlockTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone.SoulSandTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.overworld.ores.*;
import com.ewyboy.oretweaker.json.template.templates.defaults.overworld.stones.*;
import com.ewyboy.oretweaker.json.template.templates.remove.RemoveEverything;
import com.ewyboy.oretweaker.json.template.templates.remove.RemoveEverythingNether;
import com.ewyboy.oretweaker.json.template.templates.remove.RemoveEverythingOverworld;
import com.ewyboy.oretweaker.json.template.templates.remove.ores.RemoveAllOres;
import com.ewyboy.oretweaker.json.template.templates.remove.ores.RemoveAllOresNether;
import com.ewyboy.oretweaker.json.template.templates.remove.ores.RemoveAllOresOverworld;
import com.ewyboy.oretweaker.json.template.templates.remove.other.RemoveIgneousRocks;
import com.ewyboy.oretweaker.json.template.templates.remove.stones.RemoveAllStones;
import com.ewyboy.oretweaker.json.template.templates.remove.stones.RemoveAllStonesNether;
import com.ewyboy.oretweaker.json.template.templates.remove.stones.RemoveAllStonesOverworld;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Templates {

    public static final List<String> MOUNTAIN = Arrays.asList(
            "minecraft:windswept_hills",
            "minecraft:grove",
            "minecraft:meadow",
            "minecraft:snowy_slopes",
            "minecraft:frozen_peaks",
            "minecraft:jagged_peaks",
            "minecraft:stony_peaks"
    );

    public static final List<String> BADLANDS = Arrays.asList(
            "minecraft:badlands",
            "minecraft:eroded_badlands",
            "minecraft:wooded_badlands"
    );

    public static final class GeneratedTemplates {

        public static final RemoveAllOres REMOVE_ALL_ORES = new RemoveAllOres();
        public static final RemoveAllOresNether REMOVE_ALL_ORES_NETHER = new RemoveAllOresNether();
        public static final RemoveAllOresOverworld REMOVE_ALL_ORES_OVERWORLD = new RemoveAllOresOverworld();

        public static final RemoveAllStones REMOVE_ALL_STONES = new RemoveAllStones();
        public static final RemoveAllStonesNether REMOVE_ALL_STONES_NETHER = new RemoveAllStonesNether();
        public static final RemoveAllStonesOverworld REMOVE_ALL_STONES_OVERWORLD = new RemoveAllStonesOverworld();

        public static final RemoveEverything REMOVE_EVERYTHING = new RemoveEverything();
        public static final RemoveEverythingNether REMOVE_EVERYTHING_NETHER = new RemoveEverythingNether();
        public static final RemoveEverythingOverworld REMOVE_EVERYTHING_OVERWORLD = new RemoveEverythingOverworld();

        public static final RemoveIgneousRocks REMOVE_IGNEOUS_ROCKS = new RemoveIgneousRocks();

        public static final CoalOreTweak COAL_ORE_TWEAK = new CoalOreTweak();
        public static final CopperOreTweak COPPER_ORE_TWEAK = new CopperOreTweak();
        public static final DiamondOreTweak DIAMOND_ORE_TWEAK = new DiamondOreTweak();
        public static final EmeraldOreTweak EMERALD_ORE_TWEAK = new EmeraldOreTweak();
        public static final GoldOreTweak GOLD_ORE_TWEAK = new GoldOreTweak();
        public static final IronOreTweak IRON_ORE_TWEAK = new IronOreTweak();
        public static final LapisOreTweak LAPIS_ORE_TWEAK = new LapisOreTweak();
        public static final RedstoneOreTweak REDSTONE_ORE_TWEAK = new RedstoneOreTweak();

        public static final AndesiteTweak ANDESITE_TWEAK = new AndesiteTweak();
        public static final DioriteTweak DIORITE_TWEAK = new DioriteTweak();
        public static final DirtTweak DIRT_TWEAK = new DirtTweak();
        public static final ClayTweak CLAY_TWEAK = new ClayTweak();
        public static final GraniteTweak GRANITE_TWEAK = new GraniteTweak();
        public static final InfestedTweak INFESTED_STONE_TWEAK = new InfestedTweak();
        public static final TuffTweak TUFF_TWEAK = new TuffTweak();

        public static final AncientDebrisTweak ANCIENT_DEBRIS_TWEAK = new AncientDebrisTweak();
        public static final NetherGoldOreTweak NETHER_GOLD_ORE_TWEAK = new NetherGoldOreTweak();
        public static final NetherQuartzOreTweak NETHER_QUARTZ_ORE_TWEAK = new NetherQuartzOreTweak();

        public static final BlackstoneTweak BLACKSTONE_TWEAK = new BlackstoneTweak();
        public static final MagmaBlockTweak MAGMA_BLOCK_TWEAK = new MagmaBlockTweak();
        public static final SoulSandTweak SOUL_SAND_TWEAK = new SoulSandTweak();

        public static final GravelTweak GRAVEL_TWEAK = new GravelTweak();
    }

    public static final class DefaultData {
        public static final CoalOreTweak COAL_ORE_TWEAK = new CoalOreTweak();
        public static final CopperOreTweak COPPER_ORE_TWEAK = new CopperOreTweak();
        public static final DiamondOreTweak DIAMOND_ORE_TWEAK = new DiamondOreTweak();
        public static final EmeraldOreTweak EMERALD_ORE_TWEAK = new EmeraldOreTweak();
        public static final GoldOreTweak GOLD_ORE_TWEAK = new GoldOreTweak();
        public static final IronOreTweak IRON_ORE_TWEAK = new IronOreTweak();
        public static final LapisOreTweak LAPIS_ORE_TWEAK = new LapisOreTweak();
        public static final RedstoneOreTweak REDSTONE_ORE_TWEAK = new RedstoneOreTweak();

        public static final AndesiteTweak ANDESITE_TWEAK = new AndesiteTweak();
        public static final DioriteTweak DIORITE_TWEAK = new DioriteTweak();
        public static final DirtTweak DIRT_TWEAK = new DirtTweak();
        public static final ClayTweak CLAY_TWEAK = new ClayTweak();
        public static final GraniteTweak GRANITE_TWEAK = new GraniteTweak();
        public static final InfestedTweak INFESTED_STONE_TWEAK = new InfestedTweak();
        public static final TuffTweak TUFF_TWEAK = new TuffTweak();

        public static final AncientDebrisTweak ANCIENT_DEBRIS_TWEAK = new AncientDebrisTweak();
        public static final NetherGoldOreTweak NETHER_GOLD_ORE_TWEAK = new NetherGoldOreTweak();
        public static final NetherQuartzOreTweak NETHER_QUARTZ_ORE_TWEAK = new NetherQuartzOreTweak();

        public static final BlackstoneTweak BLACKSTONE_TWEAK = new BlackstoneTweak();
        public static final MagmaBlockTweak MAGMA_BLOCK_TWEAK = new MagmaBlockTweak();
        public static final SoulSandTweak SOUL_SAND_TWEAK = new SoulSandTweak();

        public static final GravelTweak GRAVEL_TWEAK = new GravelTweak();
    }

    private static final String Templates = FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + OreTweaker.MOD_ID + "/templates";

    public static final class Directories {
        public static final Path DEFAULTS_PATH = Paths.get(Templates, "defaults");
        public static final Path REMOVE_PATH = Paths.get(Templates, "remove");
    }

    public static void setupDirectories() {
        DirectoryHandler.createDirectories(Directories.DEFAULTS_PATH);
        DirectoryHandler.createDirectories(Directories.REMOVE_PATH);
    }


    public static void setup() {
        setupDirectories();
        registerTemplates();
    }

    public static void regenerateDefaultDataFromTemplate() {
        if (Settings.SETTINGS.regenerateDefaultSettings.get()) {
            try {
                for (Field field : DefaultData.class.getDeclaredFields()) {
                    Object object = field.get(null);
                    if (object instanceof ITemplate template) {
                        File templateFile = new File(DirectoryHandler.DATA_PATH + "/" +  template.templateName() + ".json");
                        generateTemplate(templateFile, template);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void registerTemplates() {
        try {
            for (Field field : GeneratedTemplates.class.getDeclaredFields()) {
                Object object = field.get(null);
                if (object instanceof ITemplate template) {
                    File templateFile = new File(template.templateDirectory() + "/" +  template.templateName() + ".json");
                    generateTemplate(templateFile, template);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateTemplate(File templateFile, ITemplate template) {
        if (!templateFile.exists()) {
            template.buildTemplateEntries();
            ModLogger.info("Creating Ore Tweaker Template file: " + template.templateName());
            JSONHandler.writeJson(templateFile, new OreConfig(template.getTemplate()));
        }
    }

}
