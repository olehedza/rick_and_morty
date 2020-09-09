package com.olehedza.rickandmorty.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RickAndMortyAPIClient {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RickAndMortyAPIClient.class);
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${api.character.uri}")
    private String characterUri;

    public JsonNode getJsonNode(String nodeName)
            throws JsonProcessingException, URISyntaxException {
        JsonNode jsonNode;
        try {
            jsonNode = getJson().orElse(new ObjectMapper()
                    .readTree("{}")).path(nodeName);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while json string processing", e);
            throw e;
        } catch (URISyntaxException e) {
            LOGGER.error("Error while URI string processing", e);
            throw e;
        }
        return jsonNode;
    }

    private Optional<JsonNode> getJson()
            throws URISyntaxException, JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate
                    .getForEntity(new URI(characterUri), String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());

        return Optional.ofNullable(root);
    }
}
