package com.ewyboy.oretweaker.util;

import com.ewyboy.oretweaker.OreTweaker;
import com.ewyboy.oretweaker.config.Settings;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLogger {

    private static final Logger logger = LogManager.getLogger(OreTweaker.MOD_ID);

    public static void debug(String message, Object... params) {
        if (Settings.SETTINGS.debugMode.get()) logger.log(Level.DEBUG, String.format(message, params));
    }

    public static void info(String message, Object... params) {
        logger.log(Level.INFO, String.format(message, params));
    }

    public static void error(String message, Object... params) {
        logger.log(Level.ERROR, String.format(message, params));
    }

    public static void warning(String message, Object... params) {
        logger.log(Level.WARN, String.format(message, params));
    }

}
