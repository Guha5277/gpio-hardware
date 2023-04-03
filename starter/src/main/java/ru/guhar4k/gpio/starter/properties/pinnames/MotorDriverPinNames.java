package ru.guhar4k.gpio.starter.properties.pinnames;

/**
 * Перечисление GPIO для подключения драйвера ШД
 */
public enum MotorDriverPinNames {

    /**
     * GPIO включения (удержания) ШД
     */
    EN,

    /**
     * GPIO направления вращения ШД
     */
    DIR,

    /**
     * GPIO шага ШД
     */
    STEP
}
