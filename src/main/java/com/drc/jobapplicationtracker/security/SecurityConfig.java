package com.drc.jobapplicationtracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/job-applications").hasRole("USER")
                        .requestMatchers("/job-applications/**").hasRole("USER")
                        .requestMatchers("/job-posts").hasRole("USER")
                        .requestMatchers("/job-posts/**").hasRole("USER")
                        .requestMatchers("/company-careers").hasRole("USER")
                        .requestMatchers("/company-careers/**").hasRole("USER")
                        .requestMatchers("/job-portals").hasRole("USER")
                        .requestMatchers("/job-portals/**").hasRole("USER")
                        .requestMatchers("/static").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/static/css/**", "/static/js/**", "/static/images/**", "/static/favicon.ico").permitAll()
                        .requestMatchers("/css/**", "/css/styles.css",  "/js/**", "/images/**", "/favicon.ico").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/login").permitAll())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/job-applications", true))
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
