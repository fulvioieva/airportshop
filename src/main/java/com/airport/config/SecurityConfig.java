package com.airport.config;

import com.airport.utilities.JwtAuthFilter;
import com.airport.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtility jwtUtility;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers(
                        "/airport/api/v1/auth/login",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated();

        //ADD JWT AUTHENTICATION FILTER
        http.addFilterBefore(new JwtAuthFilter(jwtUtility), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

