package ru.guhar4k.gpio.starter.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.guhar4k.gpio.hardware.pin.provider.PinProvider;
import ru.guhar4k.gpio.hardware.pin.provider.PinProviderFactory;
import ru.guhar4k.gpio.starter.properties.HardwareProperties;
import java.util.Objects;

@AutoConfiguration(before = ModulesAutoConfiguration.class)
@ConditionalOnMissingBean(PinProvider.class)
@EnableConfigurationProperties(HardwareProperties.class)
@AllArgsConstructor
public class PinProviderAutoConfiguration {

    private final HardwareProperties hardwareProperties;

    @PostConstruct
    public void afterPropertiesSet() {
        if (Objects.isNull(hardwareProperties.getGpioPlatform())) {
            throw new IllegalArgumentException("Необходимо явное указание GPIO-платформы. Параметр 'hardware.gpio-platform'");
        }
    }

    @Bean
    public PinProvider getPinProvider() {
        return PinProviderFactory.getPinProvider(hardwareProperties.getGpioPlatform());
    }

}
