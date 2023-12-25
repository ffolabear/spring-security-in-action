package org.example.ssiach16ex4.config;

import org.example.ssiach16ex4.security.DocumentPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ProjectConfig {

    @Autowired
    private DocumentPermissionEvaluator documentPermissionEvaluator;

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(documentPermissionEvaluator);
        return expressionHandler;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var service = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("natalie")
                .password("12345")
                .roles("admin")
                .build();

        var user2 = User.withUsername("emma")
                .password("12345")
                .roles("manager")
                .build();

        service.createUser(user1);
        service.createUser(user2);

        return service;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
