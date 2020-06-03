package is.symphony.collegeinternship.olympicgames.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UploadControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void uploadFile() {
        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", new ClassPathResource("athletes.txt"), MediaType.APPLICATION_JSON);

        webTestClient.post().uri("/api/upload")
                .bodyValue(multipartBodyBuilder.build())
                .exchange()
                .expectStatus().isOk();
    }
}