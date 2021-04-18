package com.ewyboy.oretweaker.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class OreConfig {

    // Fuck configs, I use JSON

    public static final ForgeConfigSpec settingSpec;
    public static final ServerConfig SERVER_CONFIG;

    static {
        Pair<ServerConfig, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(ServerConfig :: new);
        settingSpec = specPair.getRight();
        SERVER_CONFIG = specPair.getLeft();
    }

    public static class ServerConfig {

        // public final ForgeConfigSpec.ConfigValue<Integer> placeholderValue;

        ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Seed Drop - Config File");
                builder.push("SETTINGS");
                    // Config Content Here
                builder.pop();
            builder.build();
        }

    }

    public static void setup() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, OreConfig.settingSpec, "OreTweaker.toml");
    }

}
