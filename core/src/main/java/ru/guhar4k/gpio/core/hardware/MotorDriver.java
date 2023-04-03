package ru.guhar4k.gpio.core.hardware;

import ru.guhar4k.gpio.core.property.Direction;

/**
 * Базовая абстракция драйвера шагового двигателя
 */
public interface MotorDriver {
    /**
     * Удержание шагового двигателя
     */
    void hold();

    /**
     * Снятие с удержания шагового двигателя
     */
    void release();

    /**
     * Выполнение шага
     */
    void step();

    /**
     * Установка направления движения
     *
     * @param direction направление
     */
    void setDirection(Direction direction);
}
