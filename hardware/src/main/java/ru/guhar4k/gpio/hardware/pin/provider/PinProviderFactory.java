package ru.guhar4k.gpio.hardware.pin.provider;

import ru.guhar4k.gpio.hardware.GpioPlatform;

public class PinProviderFactory {
    public static PinProvider getPinProvider(GpioPlatform platform) {
        return switch (platform) {
            case PI4j -> new Pi4jPinProvider();
            case MOCK, GPIO_EXTENDER -> new MockPinProvider();
        };
    }
}
