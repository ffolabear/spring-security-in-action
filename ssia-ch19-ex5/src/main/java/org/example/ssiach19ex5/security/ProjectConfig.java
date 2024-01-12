package org.example.ssiach19ex5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
@Configuration
public class ProjectConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailService() {

        var user1 = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .roles("REGULAR_USER")
                .build();

        var service = new MapReactiveUserDetailsService(user1, user2);
        return service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
