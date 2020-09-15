package com.olehedza.rickandmorty.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olehedza.rickandmorty.dto.client.CharacterDto;
import com.olehedza.rickandmorty.dto.response.CharacterResponseDto;
import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.model.Episode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CharacterMapper.class);
    private final OriginMapper originMapper;
    private final LocationMapper locationMapper;
    private final ObjectMapper objectMapper;

    public CharacterMapper(OriginMapper originMapper, LocationMapper locationMapper) {
        this.originMapper = originMapper;
        this.locationMapper = locationMapper;
        objectMapper = new ObjectMapper();
    }

    public List<CharacterDto> toDtoList(String jsonString, String nodeName)
            throws JsonProcessingException {
        List<CharacterDto> dtoList = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(jsonString);
            JsonNode jsonNodes = root.path(nodeName);

            for (JsonNode node: jsonNodes) {
                dtoList.add(objectMapper.readValue(node.toString(), CharacterDto.class));
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while json string processing.", e);
            throw e;
        }
        return dtoList;
    }

    public Character toModel(CharacterDto characterDto) {
        Character model = new Character();
        model.setName(characterDto.getName());
        model.setStatus(Character.Status.valueOf(characterDto.getStatus()));
        model.setSpecies(characterDto.getSpecies());
        model.setType(characterDto.getType());
        model.setGender(Character.Gender.valueOf(characterDto.getGender()));
        model.setImage(characterDto.getImage());
        model.setEpisodes(new HashSet<>(characterDto.getEpisode().stream()
                .map(Episode::new)
                .collect(Collectors.toSet())));
        model.setUrl(characterDto.getUrl());
        model.setCreated(Instant.parse(characterDto.getCreated()));
        return model;
    }

    public CharacterResponseDto toDto(Character character) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setStatus(character.getStatus().name());
        dto.setSpecies(character.getSpecies());
        dto.setType(character.getType());
        dto.setGender(character.getGender().name());
        dto.setOrigin(originMapper.toDto(character.getOrigin()));
        dto.setLocation(locationMapper.toDto(character.getLocation()));
        dto.setImage(character.getImage());
        dto.setEpisodes(new ArrayList<>(character.getEpisodes().stream()
                .map(Episode::getUrl)
                .collect(Collectors.toList())));
        dto.setUrl(character.getUrl());
        dto.setCreated(character.getCreated().toString());
        return dto;
    }
}
