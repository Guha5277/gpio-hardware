package ru.guhar4k.gpio.core.pin;

/**
 * Абстракция входного GPIO
 */
public interface DigitalInputGpio {

    /**
     * Проверка низкого сигнала на входе пина
     *
     * @return true если значение сигнала равно логическому 0
     */
    boolean isLow();

    /**
     * Проверка высокого сигнала на входе пина
     *
     * @return true если значение сигнала равно логической 1
     */
    boolean isHigh();

    /**
     * Добавление слушателя восходящего (0 -> 1) сигнала
     *
     * @param payload выполняемое действие
     */
    void addRisingListener(Runnable payload);

    /**
     * Добавление слушателя нисходящего (1 -> 0) сигнала
     *
     * @param payload выполняемое действие
     */
    void addFallingListener(Runnable payload);
}
