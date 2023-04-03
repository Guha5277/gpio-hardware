package ru.guhar4k.gpio.hardware.pin;

import lombok.extern.slf4j.Slf4j;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;

@Slf4j
public class MockDigitalInputGpio implements DigitalInputGpio {
    private final int pinNum;

    public MockDigitalInputGpio(int pinNum) {
        this.pinNum = pinNum;
    }

    @Override
    public boolean isLow() {
        log.debug(String.format("%s: GPIO %d запрос isLow()", getClass().getName(), pinNum));
        return false;
    }

    @Override
    public boolean isHigh() {
        log.debug(String.format("%s: GPIO %d запрос isHigh()", getClass().getName(), pinNum));
        return false;
    }

    @Override
    public void addRisingListener(Runnable payload) {
        log.debug(String.format("%s: GPIO %d запрос addRisingListener()", getClass().getName(), pinNum));
    }

    @Override
    public void addFallingListener(Runnable payload) {
        log.debug(String.format("%s: GPIO %d запрос addFallingListener()", getClass().getName(), pinNum));
    }
}
