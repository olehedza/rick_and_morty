package com.olehedza.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.olehedza.rickandmorty.client.RickAndMortyAPIClient;
import com.olehedza.rickandmorty.dto.client.CharacterDto;
import com.olehedza.rickandmorty.dto.client.CharactersDto;
import com.olehedza.rickandmorty.dto.client.LocationDto;
import com.olehedza.rickandmorty.dto.client.OriginDto;
import com.olehedza.rickandmorty.service.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonParserImpl implements JsonParser {
    private final RickAndMortyAPIClient apiClient;

    @Autowired
    public JsonParserImpl(RickAndMortyAPIClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public CharactersDto parseToPojo() {
        JsonNode results = apiClient.getJsonNode("results");
        List<CharacterDto> characterDtoList = new ArrayList<>();
        CharactersDto charactersDto = new CharactersDto();

        for (JsonNode node : results) {
            CharacterDto characterDto = new CharacterDto();
            characterDto.setName(node.path("name").textValue());
            characterDto.setStatus(node.path("status").textValue());
            characterDto.setSpecies(node.path("species").textValue());
            characterDto.setType(node.path("type").textValue());
            characterDto.setGender(node.path("gender").textValue());
            characterDto.setImage(node.path("image").textValue());
            characterDto.setUrl(node.path("url").textValue());
            characterDto.setCreated(node.path("created").textValue());
            characterDto.setLocation(parseToLocation(node));
            characterDto.setOrigin(parseToOrigin(node));
            characterDto.setEpisodes(parseToEpisodeList(node));

            characterDtoList.add(characterDto);
        }

        charactersDto.setCharacterDtos(characterDtoList);
        return charactersDto;
    }

    private LocationDto parseToLocation(JsonNode jsonNode) {
        LocationDto locationDto = new LocationDto();
        JsonNode location = jsonNode.path("location");

        locationDto.setName(location.path("name").textValue());
        locationDto.setUrl(location.path("url").textValue());
        return locationDto;
    }

    private OriginDto parseToOrigin(JsonNode jsonNode) {
        OriginDto originDto = new OriginDto();
        JsonNode origin = jsonNode.path("origin");

        originDto.setName(origin.path("name").textValue());
        originDto.setUrl(origin.path("url").textValue());
        return originDto;
    }

    private List<String> parseToEpisodeList(JsonNode jsonNode) {
        List<String> episodes = new ArrayList<>();
        for (JsonNode node : jsonNode.path("episode")) {
            episodes.add(node.textValue());
        }
        return episodes;
    }
}
