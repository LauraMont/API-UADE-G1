package com.uade.tpo.marketplace.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.uade.tpo.marketplace.entity.Rol;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JWTAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //configuro seguridad http
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                                                .requestMatchers(HttpMethod.DELETE, "/usuario/delete").hasAnyAuthority(Rol.ADMIN.name())
                                                .requestMatchers(HttpMethod.POST,"/compras").hasAnyAuthority(Rol.USER.name(), Rol.ADMIN.name())
                                                .requestMatchers("/eventos").permitAll()
                                                .requestMatchers("/eventos/disponibles").permitAll()
                                                .requestMatchers("/eventos/buscar").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/eventos/{id}").hasAnyAuthority(Rol.USER.name(), Rol.ADMIN.name())
                                                .requestMatchers(HttpMethod.POST, "/eventos/{id}").hasAnyAuthority(Rol.ADMIN.name())
                                                .requestMatchers(HttpMethod.PUT, "/eventos/{id}").hasAnyAuthority(Rol.ADMIN.name())
                                                .requestMatchers(HttpMethod.DELETE, "/eventos/{id}").hasAnyAuthority(Rol.ADMIN.name())
                                                .requestMatchers(HttpMethod.GET,"/categoria").permitAll()
                                                .requestMatchers("/nombre/{nombre}").hasAnyAuthority(Rol.USER.name(), Rol.ADMIN.name())
                                                .requestMatchers(HttpMethod.POST,"/categoria").hasAnyAuthority(Rol.ADMIN.name())
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}

