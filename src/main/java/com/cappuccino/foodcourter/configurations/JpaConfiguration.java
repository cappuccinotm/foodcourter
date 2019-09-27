package com.cappuccino.foodcourter.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfiguration {

    /**
     * Определяет, кто внёс изменение в модель
     * @return объект аудитора
     */
    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || !authentication.isAuthenticated() || authentication.getName() == null)
                return Optional.of("NBU"); // Означает "NotByUser"
            return Optional.ofNullable(authentication.getName());
        };
    }
}
