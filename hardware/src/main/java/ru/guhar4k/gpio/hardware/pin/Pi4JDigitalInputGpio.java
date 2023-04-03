package ru.guhar4k.gpio.hardware.pin;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinEdge;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;

/**
 * Обёртка над GPIO из библиотеки PI4j
 */
public class Pi4JDigitalInputGpio implements DigitalInputGpio {
    private final GpioPinDigitalInput gpioPinDigitalInput;

    public Pi4JDigitalInputGpio(GpioPinDigitalInput gpioPinDigitalInput) {
        this.gpioPinDigitalInput = gpioPinDigitalInput;
    }

    @Override
    public boolean isLow() {
        return gpioPinDigitalInput.isLow();
    }

    @Override
    public boolean isHigh() {
        return gpioPinDigitalInput.isHigh();
    }

    @Override
    public void addRisingListener(Runnable payload) {
        gpioPinDigitalInput.addListener(risingListener(payload));
    }

    @Override
    public void addFallingListener(Runnable payload) {
        gpioPinDigitalInput.addListener(fallingListener(payload));
    }

    private GpioPinListenerDigital risingListener(Runnable payload) {
        return event -> {
            if (PinEdge.RISING.equals(event.getEdge())) {
                payload.run();
            }
        };
    }

    private GpioPinListenerDigital fallingListener(Runnable payload) {
        return event -> {
            if (PinEdge.FALLING.equals(event.getEdge())) {
                payload.run();
            }
        };
    }
}
