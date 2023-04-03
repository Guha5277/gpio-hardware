package ru.guhar4k.gpio.hardware.exception;

/**
 * Исключение превышения времени обращения к модулю
 */
public class HardwareTimeoutException extends RuntimeException {

    public HardwareTimeoutException(String moduleName, int moduleTimeoutInMillis) {
        super(String.format("Истекло время обращения к модулю %s, таймаут %d мс", moduleName, moduleTimeoutInMillis));
    }
}
