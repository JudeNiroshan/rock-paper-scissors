package org.game.util;

import org.jboss.logging.Logger;

public class GameLogger {
    private static Logger LOGGER = Logger.getLogger(GameLogger.class);

    private GameLogger() {
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void infof(String format, Object ...args) {
        LOGGER.infof(format, args);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void debugf(String format, Object ...args) {
        LOGGER.debugf(format, args);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void warnf(String format, Object ...args) {
        LOGGER.warnf(format, args);
    }

    public static void error(String errorMessage) {
        LOGGER.error(errorMessage);
    }

    public static void errorf(String format, Object ...args) {
        LOGGER.errorf(format, args);
    }

    public static void setLogger(Logger logger) {
        LOGGER = logger;
    }
}
