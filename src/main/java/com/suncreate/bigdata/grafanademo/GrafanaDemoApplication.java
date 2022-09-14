package com.suncreate.bigdata.grafanademo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author admin
 */
@SpringBootApplication(scanBasePackages = {"com.suncreate.bigdata.grafanademo.controller"})
public class GrafanaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrafanaDemoApplication.class, args);
    }


    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }

}
