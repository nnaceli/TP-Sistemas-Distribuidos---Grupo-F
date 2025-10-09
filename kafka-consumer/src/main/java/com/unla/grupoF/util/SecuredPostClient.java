package com.unla.grupoF.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class SecuredPostClient {

    private final RestTemplate restTemplate;
    private final String username;
    private final String password;
    private final Environment env;


    public SecuredPostClient(RestTemplateBuilder restTemplateBuilder, Environment env) {
        this.restTemplate = restTemplateBuilder
                .rootUri("http://localhost:5000")
                .build();
        this.env= env;
        this.username = env.getProperty("organization.username");
        this.password = env.getProperty("organization.password");
    }

    /**
     * Performs a login request and returns the authentication token.
     */
    public String login() {
        LoginRequest loginRequest = new LoginRequest(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        AuthResponse authResponse = restTemplate.postForObject(
                "/api/client/login",
                request,
                AuthResponse.class
        );

        if (authResponse != null) {
            return authResponse.token();
        }
        throw new RuntimeException("Login failed, could not retrieve token.");
    }

    /**
     * Performs a secured POST request using the retrieved token.
     */
    public String postSecuredData(String token, String message, String endpoint) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token); // Attach the token here

        HttpEntity<String> request = new HttpEntity<>(message, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    "/api/client/"+ endpoint,
                    HttpMethod.POST,
                    request,
                    String.class
            );
            return responseEntity.getBody();
        } catch (HttpStatusCodeException ex) {
            System.err.println("Secured POST request failed with status code: " + ex.getRawStatusCode());
            System.err.println("Response body: " + ex.getResponseBodyAsString());
            throw new RuntimeException("Secured POST failed. Status code: " + ex.getStatusCode(), ex);
        }// Returns the body from the ResponseEntity
    }

    //POJOS que necesitamos
    public record LoginRequest(String username, String password) {
    }

    public record AuthResponse(String token) {
    }

}
