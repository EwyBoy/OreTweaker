package com.ewyboy.oretweaker;

import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.DirectoryHandler;
import com.ewyboy.oretweaker.json.InfoHandler;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.template.Templates;
import com.ewyboy.oretweaker.config.tweaking.OreManager;
import com.ewyboy.oretweaker.util.ModLogger;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;

import static com.ewyboy.oretweaker.OreTweaker.MOD_ID;

@Mod(MOD_ID)
public class OreTweaker {

    public static final String MOD_ID = "oretweaker";
    public static boolean initSetup = false;

    public OreTweaker() {
        isFirstTimeSetup();
        ignoreServerOnly();
        DirectoryHandler.setup();
        Settings.setup();
        Templates.setup();
        JSONHandler.setup();
        InfoHandler.setup();
        OreManager.setup();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this :: loadComplete);
    }

    private static void isFirstTimeSetup() {
        if (!Files.exists(DirectoryHandler.ORE_TWEAKER_PATH)) {
            initSetup = true;
            ModLogger.info("Ore Tweaker detected time setup!");
        }
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {
        JSONHandler.loadComplete();
    }

    // Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
    private void ignoreServerOnly() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () ->
                new IExtensionPoint.DisplayTest(() -> "You can write whatever the fuck you want here", (addMeOnRunescape, jamflx) -> jamflx)
        );
    }

}