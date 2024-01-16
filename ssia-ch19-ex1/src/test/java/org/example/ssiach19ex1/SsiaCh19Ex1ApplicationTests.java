package org.example.ssiach19ex1;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest
class SsiaCh19Ex1ApplicationTests {

    @Autowired
    private WebTestClient client;

    @WithMockUser
    @Test
    void testCallHelloWithValidUser() {
        client
                .get().uri("/hello")
                .exchange().expectStatus().isOk();
    }

    @WithMockUser
    @Test
    void testCallHelloWithValidUserWithMockUser() {
        client.mutateWith(mockUser())
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk();
    }

    //csrf 토큰 테스트
    @WithMockUser
    @Test
    void testCallHelloWithValidUserWithCSRF() {
        client.mutateWith(csrf())
                .post()
                .exchange()
                .expectStatus()
                .isOk();
    }

}
