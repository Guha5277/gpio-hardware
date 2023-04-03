package ru.guhar4k.gpio.hardware.module.motordriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.guhar4k.gpio.core.hardware.MotorDriver;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.core.property.Direction;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StepDirMotorDriverTest {

    private MotorDriver instance;

    @Mock
    private DigitalOutputGpio enablePinMock;

    @Mock
    private DigitalOutputGpio directionPinMock;

    @Mock
    private DigitalOutputGpio stepPinMock;

    @BeforeEach
    void setUp() {
        instance = new StepDirMotorDriver(enablePinMock, directionPinMock, stepPinMock);
    }

    @Test
    void hold() {
        instance.hold();
        verify(enablePinMock, only()).low();
    }

    @Test
    void release() {
        instance.release();
        verify(enablePinMock, only()).high();
    }

    @Test
    void step() {
        instance.step();
        verify(stepPinMock, only()).pulse(10, TimeUnit.MICROSECONDS);
    }

    @Test
    void setClockwiseDirection() {
        instance.setDirection(Direction.CLOCKWISE);
        verify(directionPinMock, only()).low();

    }

    @Test
    void setCounterClockwiseDirection() {
        instance.setDirection(Direction.COUNTERCLOCKWISE);
        verify(directionPinMock, only()).high();
    }
}