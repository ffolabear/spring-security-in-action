package com.example.ssiach10.ex3.config;

import com.example.ssiach10.ex3.repositories.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

//@Configuration
public class ProjectConfig {

//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> {
                    csrf.csrfTokenRepository(customTokenRepository());
                    csrf.ignoringRequestMatchers("/ciao");
                });
        http
                .authorizeHttpRequests(
                        authorize -> authorize.anyRequest().permitAll()
                );
        return http.build();
    }

//    @Bean
    public CsrfTokenRepository customTokenRepository() {
        CustomCsrfTokenRepository csrfTokenRepository = new CustomCsrfTokenRepository();
        return csrfTokenRepository;
    }
}
