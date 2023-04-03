package ru.guhar4k.gpio.hardware.module.motordriver;


import ru.guhar4k.gpio.core.hardware.MotorDriver;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.core.property.Direction;

import java.util.concurrent.TimeUnit;

/**
 * Представление драйвера шагового двигателя (таких как A4988, TMC2208 и подобных)
 */
public class StepDirMotorDriver implements MotorDriver {
    private final DigitalOutputGpio enablePin;
    private final DigitalOutputGpio directionPin;
    private final DigitalOutputGpio stepPin;

    /**
     * @param enablePin    GPIO отвечающий за удержание/выключение ШД
     * @param directionPin GPIO отвечающий за направление вращения ШД
     * @param stepPin      GPIO отвечающий за совершение шага ШД
     */
    public StepDirMotorDriver(DigitalOutputGpio enablePin, DigitalOutputGpio directionPin, DigitalOutputGpio stepPin) {
        this.enablePin = enablePin;
        this.directionPin = directionPin;
        this.stepPin = stepPin;
    }

    @Override
    public void hold() {
        enablePin.low();
    }

    @Override
    public void release() {
        enablePin.high();
    }

    @Override
    public void step() {
        stepPin.pulse(10, TimeUnit.MICROSECONDS);
    }

    @Override
    public void setDirection(Direction direction) {
        switch (direction) {
            case CLOCKWISE -> directionPin.low();
            case COUNTERCLOCKWISE -> directionPin.high();
        }
    }
}
