package com.balmore.apirest.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.balmore.apirest.apirest.services.UserDetailsServiceImpl;
import com.balmore.apirest.apirest.utils.JwtAuthenticationFilter;
import com.balmore.apirest.apirest.utils.PlainTextPasswordEncoder;

@Configuration
public class SecurityConfig {
  private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/cotizacion/detalle/**").authenticated() 
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/cliente/**").permitAll()
                .requestMatchers("/producto/**").permitAll() 
                .requestMatchers("/proveedor/**").permitAll() 
                .requestMatchers("/categoria/**").permitAll() 
                .requestMatchers("/cotizacion/**").permitAll() 
                .requestMatchers("/tipo/producto/**").permitAll()
                .requestMatchers("/usuario/admin/**").permitAll()
                .anyRequest().permitAll()
            )
            .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PlainTextPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
