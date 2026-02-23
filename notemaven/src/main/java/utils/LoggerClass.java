package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// classw wrapper per il log4j per istanziarlo solo una volta
// alla fine potrebbe non servire

public class LoggerClass {
    private static final Logger logger = LogManager.getLogger("NoteMavenLogger");

    private LoggerClass() {
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object... params) {
        logger.info(message, params);
    }

    public static void debug(String message, Object... params) {
        logger.debug(message, params);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable fullStackTrace) {
        logger.error(message, fullStackTrace);
    }

    public static void error(String message, Object... params) {
        logger.error(message, params);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void warn(String message, Object... params) {
        logger.warn(message, params);
    }
    
    
}