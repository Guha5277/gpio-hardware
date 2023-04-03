package ru.guhar4k.gpio.hardware.pin.provider;

import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;

/**
 * Абстракция провайдер GPIO
 */
public interface PinProvider {

    /**
     * Получение цифрового выходного GPIO
     * @param pinNum номер GPIO
     */
    DigitalOutputGpio digitalOutputGpio(int pinNum);

    /**
     * Получение цифрового входного GPIO
     * @param pinNum номер GPIO
     */
    DigitalInputGpio digitalInputGpio(int pinNum);
}
