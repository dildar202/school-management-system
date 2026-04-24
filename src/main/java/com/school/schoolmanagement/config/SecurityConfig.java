package com.school.schoolmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/access-denied", "/css/**", "/js/**", "/images/**").permitAll()

                .requestMatchers("/teachers/**", "/fees/**").hasRole("ADMIN")

                .requestMatchers("/", "/students/**", "/attendance/**")
                .hasAnyRole("ADMIN", "TEACHER")

                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .permitAll()
   )

            .exceptionHandling(ex -> ex
                .accessDeniedPage("/access-denied")
            );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager users(PasswordEncoder encoder) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("1234"))
                .roles("ADMIN")
                .build();

        UserDetails teacher = User.builder()
                .username("teacher")
                .password(encoder.encode("1234"))
                .roles("TEACHER")
                .build();

        return new InMemoryUserDetailsManager(admin, teacher);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}