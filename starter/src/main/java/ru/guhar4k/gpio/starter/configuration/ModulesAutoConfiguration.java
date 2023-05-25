package ru.guhar4k.gpio.starter.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.guhar4k.gpio.core.hardware.MotorDriver;
import ru.guhar4k.gpio.core.hardware.ScalesController;
import ru.guhar4k.gpio.core.hardware.SensorController;
import ru.guhar4k.gpio.core.pin.DigitalInputGpio;
import ru.guhar4k.gpio.core.pin.DigitalOutputGpio;
import ru.guhar4k.gpio.hardware.module.motordriver.StepDirMotorDriver;
import ru.guhar4k.gpio.hardware.module.provider.ModuleProvider;
import ru.guhar4k.gpio.hardware.module.scale.HX711;
import ru.guhar4k.gpio.hardware.module.sensor.PirSensor;
import ru.guhar4k.gpio.hardware.pin.provider.PinProvider;
import ru.guhar4k.gpio.starter.exception.ModuleNotFoundException;
import ru.guhar4k.gpio.starter.properties.*;
import ru.guhar4k.gpio.starter.properties.pinnames.MotorDriverPinNames;
import ru.guhar4k.gpio.starter.properties.pinnames.ScalesPinNames;
import ru.guhar4k.gpio.starter.properties.pinnames.SensorPinNames;

import java.util.Map;

@AutoConfiguration
@EnableConfigurationProperties(HardwareProperties.class)
@ComponentScan("ru.guhar4k.gpio")
@AllArgsConstructor
public class ModulesAutoConfiguration {

    public static final String MODULE_NOT_FOUND_FORMAT = "Модуль с именем '%s' не найден";
    private final HardwareProperties hardwareProperties;
    private final PinProvider pinProvider;

    @Bean
    public ModuleProvider<MotorDriver> getModuleProvider() {
        return this::getMotorDriver;
    }

    @Bean
    public ModuleProvider<ScalesController> getScalesControllerProvider() {
        return this::getScalesControllers;
    }

    @Bean
    public ModuleProvider<SensorController> getSensorController() {
        return this::getSensorController;
    }


    public MotorDriver getMotorDriver(String driverName) {
        MotorDriverProperties motorDriverProperties = hardwareProperties.getModules().getMotorDrivers()
                .stream()
                .filter(d -> d.getDriverName().equalsIgnoreCase(driverName))
                .findAny()
                .orElseThrow(() -> new ModuleNotFoundException(String.format(MODULE_NOT_FOUND_FORMAT, driverName)));

        Map<MotorDriverPinNames, Integer> motorPins = motorDriverProperties.getPins();

        return new StepDirMotorDriver(
                getOutputPin(motorPins.get(MotorDriverPinNames.EN)),
                getOutputPin(motorPins.get(MotorDriverPinNames.DIR)),
                getOutputPin(motorPins.get(MotorDriverPinNames.STEP)));
    }


    public ScalesController getScalesControllers(String scaleName) {
        ScalesControllerProperties scalesControllerProperties = hardwareProperties.getModules().getScales()
                .stream()
                .filter(d -> d.getScalesName().equalsIgnoreCase(scaleName))
                .findAny()
                .orElseThrow(() -> new ModuleNotFoundException(String.format(MODULE_NOT_FOUND_FORMAT, scaleName)));

        Map<ScalesPinNames, Integer> scalesPins = scalesControllerProperties.getPins();

        return new HX711(
                getInputPin(scalesPins.get(ScalesPinNames.DATA)),
                getOutputPin(scalesPins.get(ScalesPinNames.CLOCK)),
                scalesControllerProperties.getGain(),
                scalesControllerProperties.getTimeoutInMillis(),
                scalesControllerProperties.getCalibrationFactor());
    }


    public SensorController getSensorController(String sensorName) {
        SensorControllerProperties scalesControllerProperties = hardwareProperties.getModules().getSensors()
                .stream()
                .filter(d -> d.getSensorName().equalsIgnoreCase(sensorName))
                .findAny()
                .orElseThrow(() -> new ModuleNotFoundException(String.format(MODULE_NOT_FOUND_FORMAT, sensorName)));

        Map<SensorPinNames, Integer> sensorPins = scalesControllerProperties.getPins();

        return new PirSensor(getInputPin(sensorPins.get(SensorPinNames.IN)));
    }


    public DigitalInputGpio getInputPin(int pinNum) {
        return pinProvider.digitalInputGpio(pinNum);
    }


    public DigitalOutputGpio getOutputPin(int pinNum) {
        return pinProvider.digitalOutputGpio(pinNum);
    }
}
