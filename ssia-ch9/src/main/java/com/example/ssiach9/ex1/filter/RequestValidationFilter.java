package com.example.ssiach9.ex1.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse)response;

        String requestId = httpRequest.getHeader("Request-id");
        if (requestId == null || requestId.isBlank()) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;         //헤더가 없다면 http 상태가 '400 잘못된 요청' 으로 바뀌고 요청이 다음 필터로 전달되지 않음
        }

        //헤더가 있다면 요청을 다음 필터로 전달
        chain.doFilter(request, response);
    }
}
