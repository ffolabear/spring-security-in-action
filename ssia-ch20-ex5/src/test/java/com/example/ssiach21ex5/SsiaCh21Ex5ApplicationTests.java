package com.example.ssiach21ex5;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class SsiaCh21Ex5ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void loggingInWithWrongAuthority() throws Exception {
        mvc.perform(formLogin()
                        .user("mary").password("12345"))
                .andExpect(redirectedUrl("/error"))
                .andExpect(status().isFound())
                .andExpect(authenticated());

    }

    @Test
    void loggingInWithCorrectAuthority() throws Exception {
        mvc.perform(formLogin()
                        .user("bill").password("12345"))
                .andExpect(redirectedUrl("/home"))
                .andExpect(status().isFound())
                .andExpect(authenticated());

    }

}
