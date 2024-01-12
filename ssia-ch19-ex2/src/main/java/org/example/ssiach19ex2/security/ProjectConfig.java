package org.example.ssiach19ex2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailService() {
        var user = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();
        System.out.println(passwordEncoder().encode("12345"));
        var service = new MapReactiveUserDetailsService(user);
        return service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
