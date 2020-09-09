package com.olehedza.rickandmorty.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olehedza.rickandmorty.dto.client.CharacterDto;
import com.olehedza.rickandmorty.dto.client.CharactersDto;
import com.olehedza.rickandmorty.mapper.CharacterMapper;
import com.olehedza.rickandmorty.mapper.LocationMapper;
import com.olehedza.rickandmorty.mapper.OriginMapper;
import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.service.CharacterService;
import com.olehedza.rickandmorty.service.EpisodeService;
import com.olehedza.rickandmorty.service.JsonParser;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientDatabaseStartupSynchronization implements CommandLineRunner {
    private final JsonParser jsonParser;
    private final CharacterService characterService;
    private final EpisodeService episodeService;
    private final OriginMapper originMapper;
    private final LocationMapper locationMapper;
    private final CharacterMapper characterMapper;


    @Autowired
    public ClientDatabaseStartupSynchronization(JsonParser jsonParser,
                                                CharacterService characterService,
                                                EpisodeService episodeService,
                                                OriginMapper originMapper,
                                                LocationMapper locationMapper,
                                                CharacterMapper characterMapper) {
        this.jsonParser = jsonParser;
        this.characterService = characterService;
        this.episodeService = episodeService;
        this.originMapper = originMapper;
        this.locationMapper = locationMapper;
        this.characterMapper = characterMapper;
    }

    @Override
    public void run(String... args)
            throws JsonProcessingException, URISyntaxException {
        CharactersDto charactersDto = jsonParser.parseToPojo();
        List<CharacterDto> dtos = charactersDto.getCharacterDtos();
        for (CharacterDto dto: dtos) {
            Character character = characterMapper.toModel(dto);
            character.setOrigin(originMapper.toModel(dto.getOrigin()));
            character.setLocation(locationMapper.toModel(dto.getLocation()));
            character.getEpisodes().forEach(episodeService::save);
            characterService.save(character);
        }
    }
}
