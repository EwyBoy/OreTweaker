package com.ewyboy.oretweaker;

import com.ewyboy.oretweaker.config.OreConfig;
import com.ewyboy.oretweaker.json.InfoHandler;
import com.ewyboy.oretweaker.json.JSONHandler;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

import static com.ewyboy.oretweaker.OreTweaker.MOD_ID;

@Mod(MOD_ID)
public class OreTweaker {

    public static final String MOD_ID = "oretweaker";

    public OreTweaker() {
        makeServerSide();
        OreConfig.setup();
        JSONHandler.setup();
        InfoHandler.setup();
    }

    //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
    private void makeServerSide() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(
                () -> FMLNetworkConstants.IGNORESERVERONLY,
                (YouCanWriteWhatEverTheFuckYouWantHere, ICreatedSlimeBlocks2YearsBeforeMojangDid) -> true)
        );
    }

}
