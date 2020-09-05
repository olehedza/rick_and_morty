package com.olehedza.rickandmorty.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RickAndMortyAPIClient {
    private static final String URI = "https://rickandmortyapi.com/api/character/";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RickAndMortyAPIClient.class);
    private final RestTemplate restTemplate = new RestTemplate();

    public JsonNode getJsonNode(String nodeName) {
        JsonNode jsonNode = new ObjectMapper().missingNode();
        try {
            jsonNode = getJson().orElse(new ObjectMapper()
                    .readTree("{}")).path(nodeName);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while json string processing", e);
        }
        return jsonNode;
    }

    private Optional<JsonNode> getJson() {
        JsonNode root = null;
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .getForEntity(new URI(URI), String.class);
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(responseEntity.getBody());
        } catch (URISyntaxException e) {
            LOGGER.error("Error while URI string processing", e);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while json string processing", e);
        }
        return Optional.ofNullable(root);
    }
}
