package ru.guhar4k.gpio.hardware.module.sensor;

import ru.guhar4k.gpio.core.hardware.SensorController;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.property.SensorState;

/**
 * Базовая реализация работы с PIR датчиком движения
 */
public class PirSensor implements SensorController {
    private final DigitalInputGpio signalPin;

    /**
     * @param signalPin GPIO к которому подключен датчик
     */
    public PirSensor(DigitalInputGpio signalPin) {
        this.signalPin = signalPin;
    }

    @Override
    public SensorState getState() {
        return signalPin.isHigh() ? SensorState.HIGH : SensorState.LOW;
    }

    @Override
    public void addLowToHighListener(Runnable payload) {
        signalPin.addRisingListener(payload);
    }

    @Override
    public void addHighToLowListener(Runnable payload) {
        signalPin.addFallingListener(payload);
    }
}
