package ru.guhar4k.gpio.hardware.pin.provider;

import com.pi4j.io.gpio.*;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.hardware.pin.Pi4JDigitalInputGpio;
import ru.guhar4k.gpio.hardware.pin.Pi4JDigitalOutputGpio;

/**
 * Провайдер GPIO на базе библиотеки PI4J
 */
public class Pi4jPinProvider implements PinProvider {

    @Override
    public DigitalOutputGpio digitalOutputGpio(int pinNum) {
        GpioController gpioFactory = GpioFactory.getInstance();
        GpioPinDigitalOutput gpio = gpioFactory.provisionDigitalOutputPin(RaspiPin.getPinByAddress(pinNum));
        gpio.setMode(PinMode.DIGITAL_OUTPUT);
        return new Pi4JDigitalOutputGpio(gpio);
    }

    @Override
    public DigitalInputGpio digitalInputGpio(int pinNum) {
        GpioController gpioFactory = GpioFactory.getInstance();
        GpioPinDigitalInput gpio = gpioFactory.provisionDigitalInputPin(RaspiPin.getPinByAddress(pinNum));
        gpio.setMode(PinMode.DIGITAL_INPUT);
        return new Pi4JDigitalInputGpio(gpio);
    }
}
