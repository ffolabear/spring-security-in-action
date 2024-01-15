package org.example.ssiach21ex2;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class SsiaCh21Ex2ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "mary")
    public void helloAuthenticated1() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(content().string("Hello, mary!"))
                .andExpect(status().isOk());
    }

    //통과하지 못하는 테스트
    @Test
    public void helloAuthenticated2() throws Exception {
        mvc.perform(get("/hello")
                        .with(user("mary")))
                .andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }

}
