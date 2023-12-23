package com.example.ssiach7ex1.config;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        //모든 요청에 대해 엑세스를 허용
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

        //WRITE 권한만 허용
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().hasAuthority("WRITE"));

        //access() 메서드 사용
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().access(hasAuthority("WRITE")));

        //WRITE 혹은 READ 권한만 허용
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().hasAnyAuthority("WRITE", "READ"));

        //DELETE 권한만 허용
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().hasAnyAuthority("WRITE", "DELETE"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .authorities("READ")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .authorities("WRITE")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
