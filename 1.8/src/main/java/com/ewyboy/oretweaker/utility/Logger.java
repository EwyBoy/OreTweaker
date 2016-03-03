package com.ewyboy.oretweaker.utility;

import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class Logger {

    public static void log(Level logLevel, Object object) {
        FMLLog.log(Reference.ModInfo.ModName, logLevel, String.valueOf(object));
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }
}
