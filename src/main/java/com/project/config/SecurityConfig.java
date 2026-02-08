package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 2. Настраиваем правила входа и выхода
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   ApiAuthenticationProvider apiAuthenticationProvider,
                                                   AuthenticationManager authenticationManager) throws Exception {
        http
                .authenticationManager(authenticationManager)
                .authorizeHttpRequests(auth -> auth
                        // Разрешаем вход на главную, регистрацию и к стилям/картинкам без пароля
                        .requestMatchers("/", "/index", "/index.html", "/register", "/register.html", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/studentAdd", "/studentSave", "/studentEdit", "/studentList").hasRole("ADMIN")
                        .requestMatchers("/studentProfile", "/studentProfileSave").hasAnyRole("USER", "ADMIN")
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

    @Bean
    public AuthenticationManager authenticationManager(ApiAuthenticationProvider apiAuthenticationProvider) {
        ProviderManager manager = new ProviderManager(apiAuthenticationProvider);
        manager.setEraseCredentialsAfterAuthentication(false);
        return manager;
    }
}
