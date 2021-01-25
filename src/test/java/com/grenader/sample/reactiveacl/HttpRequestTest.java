package com.grenader.sample.reactiveacl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {

        String urlToCall = "http://localhost:" + port + "/hello/";

        ResponseEntity<String> response = restTemplate.getForEntity(urlToCall, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String responseStr = response.getBody();
        assertEquals("Hello, World", responseStr);

        log.debug("responseStr = " + responseStr);

    }
}
