package org.example.ssiach19ex4.security;

import java.time.LocalTime;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@Configuration
public class ProjectConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(auth ->
                        auth.anyExchange()
                                .access(this::getAuthorizationDecisionMono))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    private Mono<AuthorizationDecision> getAuthorizationDecisionMono(Mono<Authentication> auth, AuthorizationContext context) {
        String path = getRequestPath(context);
        boolean restrictedTime = LocalTime.now().isAfter(LocalTime.now());
        if (path.equals("/hello")) {
            return auth.map(isAdmin())
                    .map(authorize -> authorize && !restrictedTime)
                    .map(AuthorizationDecision::new);
        }
        return Mono.just(new AuthorizationDecision(false));
    }

    private Function<Authentication, Boolean> isAdmin() {
        return p -> p
                .getAuthorities()
                .stream()
                .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
    }

    private String getRequestPath(AuthorizationContext context) {
        return context.getExchange()
                .getRequest()
                .getPath()
                .toString();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailService() {
        var user = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .roles("ADMIN")
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
