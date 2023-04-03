package ru.guhar4k.gpio.hardware.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static void sleepMicro(long microsecondsCount) {
        long delay = microsecondsCount * 1000;
        long start = System.nanoTime();
        while (System.nanoTime() - start < delay) ;
    }

    public static void sleepSecond(long seconds) {
        long delay = seconds * 1000;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < delay) ;
    }
}
