package com.ewyboy.oretweaker.tweaking;

import com.ewyboy.oretweaker.tweaking.construction.OreDeconstruction;
import com.ewyboy.oretweaker.tweaking.construction.OreReconstruction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

/**                              ___
 *                      /======/
 *             ____    //      \___
 *              | \\  //           :,
 *      |_______|__|_//            ;:;
 *     _L_____________\o           ;;;
 * ____(CCCCCCCCCCCCCC)_________________*/
public class OreManager {

    public static void setup() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        OreDeconstruction.deconstruct(forgeBus);
        //OreReconstruction.reconstruct(forgeBus);
    }

}
