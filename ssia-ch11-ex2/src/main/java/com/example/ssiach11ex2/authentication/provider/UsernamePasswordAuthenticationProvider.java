package com.example.ssiach11ex2.authentication.provider;

import com.example.ssiach11ex2.authentication.UsernamePasswordAuthentication;
import com.example.ssiach11ex2.authentication.proxy.AuthenticationServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        proxy.sendAuth(username, password);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //Authentication 이 UsernamePasswordAuthentication 을 지원할 이 AuthenticationProvider 를 설계
        return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }
}
