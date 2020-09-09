package com.olehedza.rickandmorty.util;

import com.olehedza.rickandmorty.client.RickAndMortyAPIClient;
import com.olehedza.rickandmorty.dto.client.CharacterDto;
import com.olehedza.rickandmorty.mapper.CharacterMapper;
import com.olehedza.rickandmorty.mapper.LocationMapper;
import com.olehedza.rickandmorty.mapper.OriginMapper;
import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.service.CharacterService;
import com.olehedza.rickandmorty.service.EpisodeService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientDatabaseStartupSynchronization implements CommandLineRunner {
    private static final String NODE_NAME = "results";
    private final RickAndMortyAPIClient apiClient;
    private final CharacterService characterService;
    private final EpisodeService episodeService;
    private final OriginMapper originMapper;
    private final LocationMapper locationMapper;
    private final CharacterMapper characterMapper;

    @Autowired
    public ClientDatabaseStartupSynchronization(RickAndMortyAPIClient apiClient,
                                                CharacterService characterService,
                                                EpisodeService episodeService,
                                                OriginMapper originMapper,
                                                LocationMapper locationMapper,
                                                CharacterMapper characterMapper) {
        this.apiClient = apiClient;
        this.characterService = characterService;
        this.episodeService = episodeService;
        this.originMapper = originMapper;
        this.locationMapper = locationMapper;
        this.characterMapper = characterMapper;
    }

    @Override
    public void run(String... args)
            throws IOException, URISyntaxException {
        List<CharacterDto> dtos = apiClient.parseJsonToDtoList(NODE_NAME);
        for (CharacterDto dto: dtos) {
            Character character = characterMapper.toModel(dto);
            character.setOrigin(originMapper.toModel(dto.getOrigin()));
            character.setLocation(locationMapper.toModel(dto.getLocation()));
            character.getEpisodes().forEach(episodeService::save);
            characterService.save(character);
        }
    }
}
