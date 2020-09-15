package com.olehedza.rickandmorty.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RickAndMortyAPIClient {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RickAndMortyAPIClient.class);
    private final RestTemplate restTemplate;

    public RickAndMortyAPIClient() {
        restTemplate = new RestTemplate();
    }

    public String getJsonString(String apiUri)
            throws URISyntaxException {
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .getForEntity(new URI(apiUri), String.class);
            return responseEntity.getBody();
        } catch (URISyntaxException e) {
            LOGGER.error("Error while URI string processing.", e);
            throw e;
        }
    }
}
