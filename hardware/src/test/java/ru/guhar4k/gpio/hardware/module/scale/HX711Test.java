package ru.guhar4k.gpio.hardware.module.scale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.guhar4k.gpio.core.hardware.ScalesController;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.hardware.exception.HardwareTimeoutException;
import ru.guhar4k.gpio.hardware.exception.ValueOutOfRangeException;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HX711Test {

    public static final int TIMEOUT = 3000;

    private ScalesController instance;

    @Mock
    private DigitalInputGpio dataPinMock;

    @Mock
    private DigitalOutputGpio outputPinMock;


    @BeforeEach
    void setUp() {
        instance = new HX711(dataPinMock, outputPinMock, Gain.GAIN_128, TIMEOUT, 1);
    }

    @Test
    void tare() {
        when(dataPinMock.isLow()).thenReturn(true);
        Random random = new Random();
        when(dataPinMock.isHigh()).thenAnswer((Answer<Boolean>) invocation -> random.nextBoolean());
        assertDoesNotThrow(() -> instance.tare());
    }

    @Test
    void tareWithTimeoutException() {
        when(dataPinMock.isLow()).thenReturn(false);
        Assertions.assertThrows(HardwareTimeoutException.class, () -> instance.tare());
    }

    @Test
    void throwsExceptionCauseDataIsCorrupted() {
        when(dataPinMock.isLow()).thenReturn(true);
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> instance.weight());
    }

    @Test
    void weight() {
        Random random = new Random();
        when(dataPinMock.isLow()).thenReturn(true);
        when(dataPinMock.isHigh()).thenAnswer((Answer<Boolean>) invocation -> random.nextBoolean());
        assertDoesNotThrow(() -> instance.weight());
    }
}