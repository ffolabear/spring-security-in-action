package com.example.ssiach8.ex2.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class ProjectConfig {

//    @Bean
    public SecurityFilterChain filterChainEx2(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(HttpMethod.GET, "/a").authenticated();
            authorize.requestMatchers(HttpMethod.POST, "/a").permitAll();
            authorize.anyRequest().denyAll();
        });
        return http.build();
    }

//    @Bean
    public SecurityFilterChain filterChainEx3(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/a/b/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


//    @Bean
    public UserDetailsService userDetailsService() {

        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

//    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
