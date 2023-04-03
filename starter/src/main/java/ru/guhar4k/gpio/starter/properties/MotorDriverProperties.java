package ru.guhar4k.gpio.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.guhar4k.gpio.starter.properties.pinnames.MotorDriverPinNames;

import java.util.Map;

/**
 * Параметры шагового двигателя
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "hardware.modules.motor-drivers")
public class MotorDriverProperties {

    /**
     * Название драйвера (шагового двигателя)
     */
    private String driverName;

    /**
     * Параметры GPIO подключения драйвера ШД
     */
    private Map<MotorDriverPinNames, Integer> pins;
}
