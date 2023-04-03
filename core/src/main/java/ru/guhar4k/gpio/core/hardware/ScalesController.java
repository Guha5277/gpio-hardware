package ru.guhar4k.gpio.core.hardware;

import java.util.Arrays;

/**
 * Базовая абстракция контроллера весов
 */
public interface ScalesController {

    /**
     * Обнуление текущего веса (тара)
     */
    void tare();

    /**
     * Измерение веса
     *
     * @return значение измерения веса
     */
    double weight();

    /**
     * Получение медианного значения нескольких измерений
     * @param readCounts требуемое количество чтений. Нечетное число, минимальное количество - 3,
     *                   приводится к требуемому значению, в случае несоответствия - если readsCount < 3 -> 3,
     *                   если readsCount нечетное значение -> readsCount = readsCount + 1
     * @return медианное значение веса
     */
    default double weightMedian(int readCounts) {
        readCounts = readCounts < 3 ? 3 :
                readCounts % 2 == 0 ? readCounts + 1 : readCounts;

        double[] samples = new double[readCounts];

        for (int i = 0; i < readCounts; i++) {
            samples[i] = weight();
        }

        return Arrays.stream(samples).sorted().skip(samples.length / 2).findFirst().getAsDouble();
    }
}
