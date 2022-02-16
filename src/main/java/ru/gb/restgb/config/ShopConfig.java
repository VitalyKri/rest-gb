package ru.gb.restgb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
public class ShopConfig {


    // это функциональный интерфейс переопределем сразу тут его.
    @Bean
    public AuditorAware<String> auditorAwareBean() {

        return ()->Optional.of("user") ;
    }
}
