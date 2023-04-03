package ru.guhar4k.gpio.hardware;

/**
 * Используемая платформа
 */
public enum GpioPlatform {
    /**
     * PI4J для запуска на одполатных компьютерах по типу RaspberryPi
     */
    PI4j,

    /**
     * Работа с GPIO напрямую с ПК с помощью GPIO_EXTENDER-а
     */
    GPIO_EXTENDER,

    /**
     * Мок-платформа - для тестов и отладки логики работы приложения
     */
    MOCK
}
