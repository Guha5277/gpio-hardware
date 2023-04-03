package ru.guhar4k.gpio.hardware.module.scale;

import ru.guhar4k.gpio.core.hardware.ScalesController;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.hardware.exception.HardwareTimeoutException;
import ru.guhar4k.gpio.hardware.exception.ValueOutOfRangeException;

/**
 * Реализация работы с АЦП HX711 - модулем измерения веса с тензодатчика
 */
public class HX711 implements ScalesController {
    private static final int BIT_READS_COUNT = 24;
    private static final int OUT_OF_RANGE_MIN = 0x0;
    private static final int OUT_OF_RANGE_MAX = 0xFFFFFF;

    private final DigitalInputGpio dataPin;
    private final DigitalOutputGpio clockPin;
    private final Gain gain;
    private final int moduleTimeoutInMillis;
    private final int calibrationFactor;

    private int tareWeight;

    /**
     * @param dataPin               GPIO отвечающий за считывание данных
     * @param clockPin              GPIO для отправки управляющих сигналов на модуль HX711
     * @param gain                  коэффициент усиления сигнала (см. даташит)
     * @param moduleTimeoutInMillis таймаут попыток обращения к HX711
     *                              используется для прерывания процесса опроса в случае превышения таймаута
     * @param calibrationFactor     калибровочное значение, уникальное для каждого тензодатчика
     */
    public HX711(DigitalInputGpio dataPin, DigitalOutputGpio clockPin, Gain gain, int moduleTimeoutInMillis, int calibrationFactor) {
        this.dataPin = dataPin;
        this.clockPin = clockPin;
        this.gain = gain;
        this.moduleTimeoutInMillis = moduleTimeoutInMillis;
        this.calibrationFactor = calibrationFactor;
        clockPin.high();
    }

    @Override
    public void tare() {
        tareWeight = readValue();
    }

    @Override
    public double weight() {
        double value = readValue();
        return (value - tareWeight) / calibrationFactor;
    }

    private int readValue() {
        wakeUpAndWaitModuleForReady();
        return readBitsValue();
    }

    private void wakeUpAndWaitModuleForReady() {
        clockPin.low();
        long start = System.currentTimeMillis();

        while (!moduleReady()) {
            if (System.currentTimeMillis() - start > moduleTimeoutInMillis) {
                throw new HardwareTimeoutException(getClass().getName(), moduleTimeoutInMillis);
            }
        }
    }

    private boolean moduleReady() {
        return dataPin.isLow();
    }

    private int readBitsValue() {
        int result = 0;

        for (int i = 0; i < BIT_READS_COUNT; i++) {
            clockPin.high();
            result = result << 1;
            clockPin.low();

            if (dataPin.isHigh()) {
                result++;
            }
        }

        setGainForNextMeasure();

        clockPin.high();

        if (result == OUT_OF_RANGE_MAX || result == OUT_OF_RANGE_MIN) {
            throw new ValueOutOfRangeException();
        }

        return -(result | 0xFF000000);
    }

    private void setGainForNextMeasure() {
        switch (gain) {
            case GAIN_64:
                pulse();
            case GAIN_32:
                pulse();
            case GAIN_128:
                pulse();
        }
    }

    private void pulse() {
        clockPin.high();
        clockPin.low();
    }
}
