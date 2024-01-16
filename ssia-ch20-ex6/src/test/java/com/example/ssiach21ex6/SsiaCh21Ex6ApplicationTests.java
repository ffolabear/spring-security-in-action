package com.example.ssiach21ex6;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
class SsiaCh21Ex6ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void helloAuthenticatingWithValidUser() throws Exception {
        mvc.perform(get("/hello")
                        .with(httpBasic("john", "12345")))
                .andExpect(status().isOk());

    }

    @Test
    void helloAuthenticatingWithInvalidUser() throws Exception {
        mvc.perform(
                        get("/hello")
                                .with(httpBasic("mary", "12345")))
                .andExpect(status().isUnauthorized());
        //.andExpect(header().exists("failed"))
        //붙일시 헤더 검증 가능
    }

}
