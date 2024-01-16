package com.example.ssiach10.ex1.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.security.web.csrf.CsrfToken;

public class CsrfTokenLogger implements Filter {

    private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Object object = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) object;
        logger.info("CSRF Token : " + token.getToken());
        chain.doFilter(request, response);
    }
}
