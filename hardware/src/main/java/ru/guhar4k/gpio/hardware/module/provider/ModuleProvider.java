package ru.guhar4k.gpio.hardware.module.provider;

public interface ModuleProvider<T> {
    T getModuleByName(String name);
}
