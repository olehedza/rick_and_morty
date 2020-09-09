package com.olehedza.rickandmorty.mapper;

import com.olehedza.rickandmorty.dto.client.CharacterDto;
import com.olehedza.rickandmorty.dto.response.CharacterResponseDto;
import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.model.Episode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
    private final OriginMapper originMapper;
    private final LocationMapper locationMapper;

    public CharacterMapper(OriginMapper originMapper, LocationMapper locationMapper) {
        this.originMapper = originMapper;
        this.locationMapper = locationMapper;
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
