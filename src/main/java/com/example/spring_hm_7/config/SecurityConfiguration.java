package com.example.spring_hm_7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  /**
   * Настройка пользовательского сервиса для аутентификации.
   *
   * @return UserDetailsService с предопределенными пользователями.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    User.UserBuilder users = User.withDefaultPasswordEncoder();
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(users
            .username("user")
            .password("password")
            .roles("USER")
            .build());
    manager.createUser(users
            .username("admin")
            .password("password")
            .roles("USER", "ADMIN")
            .build());
    return manager;
  }

  /**
   * Конфигурация фильтра безопасности для обработки HTTP-запросов.   *
   *
   * @param http Конфигурация безопасности HTTP.
   * @return Фильтр безопасности для цепочки фильтров.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeRequests((a) -> a
                    .requestMatchers("/public-data").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/private-data").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                    .defaultSuccessUrl("/")
                    .permitAll()
            );

    return http.build();
  }

}
