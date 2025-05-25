package com.uade.tpo.marketplace.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uade.tpo.marketplace.entity.Rol;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> req
                // Auth endpoints
                .requestMatchers("/api/v1/auth/**").permitAll()
                
                // Event endpoints
                .requestMatchers("/eventos/**").permitAll()  // Allow all /eventos paths by default
                .requestMatchers(HttpMethod.GET, "/eventos/{id}").hasAnyAuthority(Rol.USER.name(), Rol.ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/eventos").hasAnyAuthority(Rol.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/eventos/{id}").hasAnyAuthority(Rol.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/eventos/{id}").hasAnyAuthority(Rol.ADMIN.name())
                
                // Category endpoints
                .requestMatchers(HttpMethod.GET, "/categoria/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/categoria").hasAnyAuthority(Rol.ADMIN.name())
                
                // Other endpoints
                .requestMatchers(HttpMethod.DELETE, "/usuario/delete").hasAnyAuthority(Rol.ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/compras").hasAnyAuthority(Rol.USER.name(), Rol.ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/artista").hasAnyAuthority(Rol.ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/locacion").hasAnyAuthority(Rol.ADMIN.name())
                .requestMatchers("/nombre/{nombre}").hasAnyAuthority(Rol.USER.name(), Rol.ADMIN.name())
                
                // Default
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

