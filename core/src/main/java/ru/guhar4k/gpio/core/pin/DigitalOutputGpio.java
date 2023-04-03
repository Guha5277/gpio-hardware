package ru.guhar4k.gpio.core.pin;

import java.util.concurrent.TimeUnit;

/**
 * Абстракция выходного GPIO
 */
public interface DigitalOutputGpio {

    /**
     * Установка логической 1 на выходе
     */
    void high();

    /**
     * Установка логического 0 на выходе
     */
    void low();

    /**
     * Однократная пульсация на выходе (0 -> 1 -> 0) с заданным промежутком удержания единицы
     *
     * @param duration   количество времени, в течении которого необходимо удерживать высокий выходной сигнал
     * @param timeUnit единица измерения времени
     */
    void pulse(int duration, TimeUnit timeUnit);
}
