package com.ewyboy.oretweaker;

import com.ewyboy.oretweaker.handler.OreGenEventHandler;
import com.ewyboy.oretweaker.loaders.ConfigLoader;
import com.ewyboy.oretweaker.utility.Logger;
import com.ewyboy.oretweaker.utility.Reference;
import com.ewyboy.oretweaker.worldGen.WorldGenerator;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import java.util.concurrent.TimeUnit;

@Mod(modid = Reference.ModInfo.ModID, name = Reference.ModInfo.ModName, version = Reference.ModInfo.BuildVersion, acceptedMinecraftVersions = "["+ Reference.ModInfo.MinecraftVersion+"]")
public class OreTweaker {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
                ConfigLoader.init(event.getSuggestedConfigurationFile());
                MinecraftForge.ORE_GEN_BUS.register(new OreGenEventHandler());
                GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
            Logger.info("Pre-Initialization process successfully done");
        Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }
}
