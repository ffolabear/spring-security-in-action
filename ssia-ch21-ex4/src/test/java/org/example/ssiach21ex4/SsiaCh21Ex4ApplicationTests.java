package org.example.ssiach21ex4;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.ssiach21ex4.annotation.WithCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
class SsiaCh21Ex4ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithCustomUser(username = "mary")
    public void helloAuthenticated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isOk());
    }

}
