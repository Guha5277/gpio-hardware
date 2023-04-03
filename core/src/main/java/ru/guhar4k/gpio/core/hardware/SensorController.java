package ru.guhar4k.gpio.core.hardware;

import ru.guhar4k.gpio.core.property.SensorState;

/**
 * Базовая абстракция простого сенсора, выдающего на вход логический 0 или 1 в зависимости от состояния
 */
public interface SensorController {

    /**
     * Возвращает текущее состояние статуса
     */
    SensorState getState();

    /**
     * Добавление слушателя изменения состояния с логического 0 в 1
     *
     * @param payload выполняемая операция при наступлении соответствующего состояния
     */
    void addLowToHighListener(Runnable payload);

    /**
     * Добавление слушателя изменения состояния с логического 1 в 0
     *
     * @param payload выполняемая операция при наступлении соответствующего состояния
     */
    void addHighToLowListener(Runnable payload);
}
