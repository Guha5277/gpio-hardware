package ru.guhar4k.gpio.hardware.pin;

import lombok.extern.slf4j.Slf4j;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MockDigitalOutputGpio implements DigitalOutputGpio {
    private final int pinNum;

    public MockDigitalOutputGpio(int pinNum) {
        this.pinNum = pinNum;
    }

    @Override
    public void high() {
        log.debug(String.format("%s: GPIO %d установлен в HIGH", getClass().getName(), pinNum));
    }

    @Override
    public void low() {
        log.debug(String.format("%s: GPIO %d установлен в LOW", getClass().getName(), pinNum));
    }

    @Override
    public void pulse(int duration, TimeUnit timeUnit) {
        log.debug(String.format("%s: GPIO %d pulse. Duration: %d, TimeUnit: %s", getClass().getName(), pinNum, duration, timeUnit.toString()));
    }
}
