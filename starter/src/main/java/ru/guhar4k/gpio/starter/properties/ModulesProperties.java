package ru.guhar4k.gpio.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметры подключаемых модулей - сенсоров, датчиков, преобразователей и т.п
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "hardware.modules")
public class ModulesProperties {

    private List<MotorDriverProperties> motorDrivers = new ArrayList<>();

    private List<ScalesControllerProperties> scales = new ArrayList<>();

    private List<SensorControllerProperties> sensors = new ArrayList<>();
}
