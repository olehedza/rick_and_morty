package com.olehedza.rickandmorty.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olehedza.rickandmorty.dto.client.CharacterDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private @Value("${api.character.uri}") String characterUri;

    public RickAndMortyAPIClient() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
    }

    public List<CharacterDto> parseJsonToDtoList(String nodeName)
            throws JsonProcessingException, URISyntaxException {
        List<CharacterDto> dtoList = new ArrayList<>();
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .getForEntity(new URI(characterUri), String.class);
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            JsonNode jsonNodes = root.path(nodeName);

            for (JsonNode node: jsonNodes) {
                dtoList.add(objectMapper.readValue(node.toString(), CharacterDto.class));
            }
        } catch (URISyntaxException e) {
            LOGGER.error("Error while URI string processing.", e);
            throw e;
        } catch (JsonMappingException e) {
            LOGGER.error("Error while json string mapping.", e);
            throw e;
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while json string processing.", e);
            throw e;
        }
        return dtoList;
    }
}
