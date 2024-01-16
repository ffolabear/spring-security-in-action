package com.example.ssiach10.ex3.repositories;

import com.example.ssiach10.ex3.entities.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

//@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

//    @Autowired
    private JpaTokenRepository jpaTokenRepository;

//    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        System.out.println("============================================");
        System.out.println("CustomCsrfTokenRepository.generateToken");
        System.out.println("uuid = " + uuid);
        System.out.println("============================================");
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

//    @Override
    public void saveToken(CsrfToken csrftoken, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("============================================");
        System.out.println("CustomCsrfTokenRepository.saveToken");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        System.out.println("============================================");

        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            System.out.println("Token exist : " + token);
            token.setToken(csrftoken.getToken());
        } else {
            Token token = new Token();
            System.out.println("Token not exist : " + token);
            token.setToken(csrftoken.getToken());
            token.setIdentifier(identifier);
            jpaTokenRepository.save(token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        System.out.println("============================================");
        System.out.println("CustomCsrfTokenRepository.loadToken");
        System.out.println("============================================");
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
        }
        return null;
    }

}
