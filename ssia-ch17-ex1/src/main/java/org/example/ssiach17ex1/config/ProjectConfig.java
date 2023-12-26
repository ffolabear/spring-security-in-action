package org.example.ssiach17ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        var userDetailService = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("nikolai")
                .password("12345")
                .authorities("read")
                .build();

        var user2 = User.withUsername("julien")
                .password("12345")
                .authorities("write")
                .build();

        userDetailService.createUser(user1);
        userDetailService.createUser(user2);
        return userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
