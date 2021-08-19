package com.ewyboy.oretweaker.json.template;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.DirectoryHandler;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.objects.OreConfig;
import com.ewyboy.oretweaker.json.template.templates.collectives.DefaultNetherTemplate;
import com.ewyboy.oretweaker.json.template.templates.collectives.DefaultOverworldTemplate;
import com.ewyboy.oretweaker.json.template.templates.collectives.DefaultTemplate;
import com.ewyboy.oretweaker.json.template.templates.defaults.common.GravelTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore.AncientDebrisTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore.NetherGoldOreTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.ore.NetherQuartzOreTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone.BlackstoneTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone.MagmaBlockTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.nether.stone.SoulSandTweak;
import com.ewyboy.oretweaker.json.template.templates.defaults.overworld.ores.*;
import com.ewyboy.oretweaker.json.template.templates.defaults.overworld.stones.*;
import com.ewyboy.oretweaker.json.template.templates.other.FuckSilverfishTemplate;
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

public class Templates {

    public static final class GeneratedTemplates {
        public static final DefaultTemplate DEFAULT_TEMPLATE = new DefaultTemplate();
        public static final DefaultNetherTemplate DEFAULT_NETHER_TEMPLATE = new DefaultNetherTemplate();
        public static final DefaultOverworldTemplate DEFAULT_OVERWORLD_TEMPLATE = new DefaultOverworldTemplate();

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
        public static final FuckSilverfishTemplate FUCK_SILVERFISH_TEMPLATE = new FuckSilverfishTemplate();

        public static final CoalOreTweak COAL_ORE_TWEAK = new CoalOreTweak();
        public static final DiamondOreTweak DIAMOND_ORE_TWEAK = new DiamondOreTweak();
        public static final EmeraldOreTweak EMERALD_ORE_TWEAK = new EmeraldOreTweak();
        public static final GoldOreTweak GOLD_ORE_TWEAK = new GoldOreTweak();
        public static final IronOreTweak IRON_ORE_TWEAK = new IronOreTweak();
        public static final LapisOreTweak LAPIS_ORE_TWEAK = new LapisOreTweak();
        public static final RedstoneOreTweak REDSTONE_ORE_TWEAK = new RedstoneOreTweak();

        public static final AndesiteTweak ANDESITE_TWEAK = new AndesiteTweak();
        public static final DioriteTweak DIORITE_TWEAK = new DioriteTweak();
        public static final DirtTweak DIRT_TWEAK = new DirtTweak();
        public static final GraniteTweak GRANITE_TWEAK = new GraniteTweak();
        public static final InfestedStoneTweak INFESTED_STONE_TWEAK = new InfestedStoneTweak();

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
        public static final DiamondOreTweak DIAMOND_ORE_TWEAK = new DiamondOreTweak();
        public static final EmeraldOreTweak EMERALD_ORE_TWEAK = new EmeraldOreTweak();
        public static final GoldOreTweak GOLD_ORE_TWEAK = new GoldOreTweak();
        public static final IronOreTweak IRON_ORE_TWEAK = new IronOreTweak();
        public static final LapisOreTweak LAPIS_ORE_TWEAK = new LapisOreTweak();
        public static final RedstoneOreTweak REDSTONE_ORE_TWEAK = new RedstoneOreTweak();

        public static final AndesiteTweak ANDESITE_TWEAK = new AndesiteTweak();
        public static final DioriteTweak DIORITE_TWEAK = new DioriteTweak();
        public static final DirtTweak DIRT_TWEAK = new DirtTweak();
        public static final GraniteTweak GRANITE_TWEAK = new GraniteTweak();
        public static final InfestedStoneTweak INFESTED_STONE_TWEAK = new InfestedStoneTweak();

        public static final AncientDebrisTweak ANCIENT_DEBRIS_TWEAK = new AncientDebrisTweak();
        public static final NetherGoldOreTweak NETHER_GOLD_ORE_TWEAK = new NetherGoldOreTweak();
        public static final NetherQuartzOreTweak NETHER_QUARTZ_ORE_TWEAK = new NetherQuartzOreTweak();

        public static final BlackstoneTweak BLACKSTONE_TWEAK = new BlackstoneTweak();
        public static final MagmaBlockTweak MAGMA_BLOCK_TWEAK = new MagmaBlockTweak();
        public static final SoulSandTweak SOUL_SAND_TWEAK = new SoulSandTweak();

        public static final GravelTweak GRAVEL_TWEAK = new GravelTweak();
    }

    public static final class Directories {
        public static final Path COLLECTIVES_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + OreTweaker.MOD_ID + "/templates", "collectives");
        public static final Path DEFAULTS_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + OreTweaker.MOD_ID + "/templates", "defaults");
        public static final Path REMOVE_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + OreTweaker.MOD_ID + "/templates", "remove");
        public static final Path OTHER_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + OreTweaker.MOD_ID + "/templates", "other");
    }

    public static void setupDirectories() {
        DirectoryHandler.createDirectories(Directories.COLLECTIVES_PATH);
        DirectoryHandler.createDirectories(Directories.DEFAULTS_PATH);
        DirectoryHandler.createDirectories(Directories.REMOVE_PATH);
        DirectoryHandler.createDirectories(Directories.OTHER_PATH);
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
                    if (object instanceof ITemplate) {
                        ITemplate template = (ITemplate) object;
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
                if (object instanceof ITemplate) {
                    ITemplate template = (ITemplate) object;
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
