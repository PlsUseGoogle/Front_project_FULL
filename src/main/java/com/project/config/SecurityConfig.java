package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Создаем "Менеджера пользователей" в памяти
    // При запуске создаем одного админа: login=admin, pass=admin
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // 2. Настраиваем правила входа и выхода
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Разрешаем вход на главную, регистрацию и к стилям/картинкам без пароля
                        .requestMatchers("/", "/index", "/index.html", "/register", "/register.html", "/css/**", "/js/**", "/images/**").permitAll()
                        // Всё остальное (студенты, проекты) - только для вошедших
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/index")       // Наша страница логина - это index
                        .loginProcessingUrl("/login") // Куда отправлять данные формы
                        .defaultSuccessUrl("/projektList", true) // Куда перекинуть после успеха
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")      // Ссылка для выхода
                        .logoutSuccessUrl("/index") // Куда перекинуть после выхода
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Отключаем сложную защиту форм для простоты

        return http.build();
    }
}