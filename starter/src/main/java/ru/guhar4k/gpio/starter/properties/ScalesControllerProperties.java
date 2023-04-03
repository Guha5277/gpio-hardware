package ru.guhar4k.gpio.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.guhar4k.gpio.hardware.module.scale.Gain;
import ru.guhar4k.gpio.starter.properties.pinnames.ScalesPinNames;

import java.util.Map;

/**
 * Параметры модуля весов
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "hardware.modules.scales")
public class ScalesControllerProperties {

    /**
     * Название драйвера (шагового двигателя)
     */
    private String scalesName;

    /**
     * Разрядность (усиление) АЦП (см. даташит)
     */
    private Gain gain = Gain.GAIN_128;

    /**
     * Калибровочное значение (уникальное для каждого тензодатчика)
     */
    private int calibrationFactor = 1;

    /**
     * Таймаут ожидания ответа от АЦП
     */
    private int timeoutInMillis = 5000;

    /**
     * Параметры GPIO подключения АЦП тензодатчика
     */
    private Map<ScalesPinNames, Integer> pins;
}
