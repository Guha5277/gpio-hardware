package ru.guhar4k.gpio.hardware.pin.provider;

import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.hardware.pin.MockDigitalInputGpio;
import ru.guhar4k.gpio.hardware.pin.MockDigitalOutputGpio;

/**
 * Заглушка провайдера GPIO
 */
public class MockPinProvider implements PinProvider {

    @Override
    public DigitalOutputGpio digitalOutputGpio(int pinNum) {
        return new MockDigitalOutputGpio(pinNum);
    }

    @Override
    public DigitalInputGpio digitalInputGpio(int pinNum) {
        return new MockDigitalInputGpio(pinNum);
    }
}
