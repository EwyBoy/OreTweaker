package com.ewyboy.oretweaker;

import com.ewyboy.oretweaker.commands.CommandCenter;
import com.ewyboy.oretweaker.config.Settings;
import com.ewyboy.oretweaker.json.DirectoryHandler;
import com.ewyboy.oretweaker.json.InfoHandler;
import com.ewyboy.oretweaker.json.JSONHandler;
import com.ewyboy.oretweaker.json.template.Templates;
import com.ewyboy.oretweaker.tweaking.OreManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

import static com.ewyboy.oretweaker.OreTweaker.MOD_ID;

@Mod(MOD_ID)
public class OreTweaker {

    public static final String MOD_ID = "oretweaker";

    public OreTweaker() {
        ignoreServerOnly();
        DirectoryHandler.setup();
        Templates.setup();
        JSONHandler.setup();
        InfoHandler.setup();
        Settings.setup();
        OreManager.setup();
        MinecraftForge.EVENT_BUS.addListener(this :: onServerStart);
    }

    // Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
    private void ignoreServerOnly() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(
                () -> FMLNetworkConstants.IGNORESERVERONLY,
                (YouCanWriteWhatEverTheFuckYouWantHere, ICreatedSlimeBlocks2YearsBeforeMojangDid) -> true)
        );
    }

    public void onServerStart(FMLServerStartingEvent event) {
        new CommandCenter(event.getServer().getCommands().getDispatcher());
    }

}