package ru.guhar4k.gpio.hardware.module.scale;

public enum Gain {
    GAIN_128(128),
    GAIN_64(64),
    GAIN_32(32);

    Gain(int gainValue) {
        this.gainValue = gainValue;
    }

    private final int gainValue;

    public int getValue() {
        return gainValue;
    }

    public Gain getDefault() {
        return Gain.GAIN_128;
    }
}
