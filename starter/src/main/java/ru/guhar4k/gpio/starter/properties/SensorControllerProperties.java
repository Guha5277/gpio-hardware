package ru.guhar4k.gpio.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.guhar4k.gpio.starter.properties.pinnames.SensorPinNames;

import java.util.Map;

/**
 * Параметры сенсора
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "hardware.modules.sensors")
public class SensorControllerProperties {

    /**
     * Название сенсора
     */
    private String sensorName;

    /**
     * Параметры GPIO подключения сенсора
     */
    private Map<SensorPinNames, Integer> pins;
}
