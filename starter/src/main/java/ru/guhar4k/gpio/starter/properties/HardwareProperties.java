package ru.guhar4k.gpio.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import ru.guhar4k.gpio.hardware.GpioPlatform;

/**
 * Конфигурация подключенного GPIO-оборудования
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "hardware")
@EnableConfigurationProperties(ModulesProperties.class)
public class HardwareProperties {

    /**
     * Тип платформы для взаимодействия с GPIO
     */
    private GpioPlatform gpioPlatform;

    /**
     * GPIO-модули используемые в проекте
     */
    @NestedConfigurationProperty
    ModulesProperties modules = new ModulesProperties();
}
