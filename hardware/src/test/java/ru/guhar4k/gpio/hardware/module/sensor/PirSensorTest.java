package ru.guhar4k.gpio.hardware.module.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.guhar4k.gpio.core.hardware.SensorController;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.property.SensorState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PirSensorTest {

    private SensorController instance;

    @Mock
    private DigitalInputGpio inputPinMock;

    @BeforeEach
    void setUp() {
        instance = new PirSensor(inputPinMock);
    }

    @Test
    void getStateHigh() {
        when(inputPinMock.isHigh()).thenReturn(true);
        SensorState state = instance.getState();
        assertEquals(SensorState.HIGH, state);
    }

    @Test
    void getStateLow() {
        when(inputPinMock.isHigh()).thenReturn(false);
        SensorState state = instance.getState();
        assertEquals(SensorState.LOW, state);
    }

    @Test
    void addLowToHighListener() {
        Runnable payloadMock = Mockito.mock(Runnable.class);
        instance.addLowToHighListener(payloadMock);

        verify(inputPinMock, only()).addRisingListener(payloadMock);
    }

    @Test
    void addHighToLowListener() {
        Runnable payloadMock = Mockito.mock(Runnable.class);
        instance.addHighToLowListener(payloadMock);

        verify(inputPinMock, only()).addFallingListener(payloadMock);
    }
}