package ru.guhar4k.gpio.hardware.exception;

public class ValueOutOfRangeException extends RuntimeException {
    public ValueOutOfRangeException() {
        super("Считаны некорректные данные. Возможно отсутствие/неправильное подключение к модулю");
    }
}
