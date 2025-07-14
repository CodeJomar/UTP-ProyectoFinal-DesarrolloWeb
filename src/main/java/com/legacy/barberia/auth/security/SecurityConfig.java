package com.legacy.barberia.auth.security;

import com.legacy.barberia.auth.service.CustomUDS;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final CustomUDS userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // Ignorar CSRF para H2 Console
            )
            .authorizeHttpRequests(auth -> auth
                // Recursos estáticos
                .requestMatchers("/", "/css/**", "/js/**", "/img/**", "/error", "/registro").permitAll()
                
                // Definición de roles
                .requestMatchers("/staff", "/citas", "/productos", "/perfil")
                .hasAnyAuthority("ROLE_USER", "ROLE_EMPLOYEE", "ROLE_ADMIN")
                
                .requestMatchers("/citas-listado", "/productos-registro")
                .hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_ADMIN")
                
                .requestMatchers("/usuario-registro", "/staff-registro")
                .hasAuthority("ROLE_ADMIN")
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/?auth", true)
                .failureUrl("/?error")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/acceso-denegado")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout=true")
                .permitAll()
            )
            .userDetailsService(userDetailsService)
            .build();
    }
}