package ru.guhar4k.gpio.hardware.pin;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;

import java.util.concurrent.TimeUnit;

/**
 * Обёртка над GPIO из библиотеки PI4j
 */
public class Pi4JDigitalOutputGpio implements DigitalOutputGpio {
    private final GpioPinDigitalOutput gpioPinDigitalOutput;

    public Pi4JDigitalOutputGpio(GpioPinDigitalOutput gpioPinDigitalOutput) {
        this.gpioPinDigitalOutput = gpioPinDigitalOutput;
    }

    @Override
    public void high() {
        gpioPinDigitalOutput.high();
    }

    @Override
    public void low() {
        gpioPinDigitalOutput.low();
    }

    @Override
    public void pulse(int duration, TimeUnit timeUnit) {
        gpioPinDigitalOutput.pulse(duration, timeUnit);
    }
}
