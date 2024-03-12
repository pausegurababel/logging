package com.example.demo.annotations.utils;

import com.example.demo.annotations.DemoApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger LOGGER = LogManager.getLogger(DemoApplication.class);

    public static void trace(String msg) {
        LOGGER.trace(msg);
    }

    public static void debug(String msg) {
        LOGGER.debug(msg);
    }

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void warn(String msg) {
        LOGGER.warn(msg);
    }

    public static void error(String msg) {
        LOGGER.error(msg);
    }

    public static void fatal(String msg) {
        LOGGER.fatal(msg);
    }

}