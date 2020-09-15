package com.olehedza.rickandmorty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olehedza.rickandmorty.client.RickAndMortyAPIClient;
import com.olehedza.rickandmorty.dto.client.CharacterDto;
import com.olehedza.rickandmorty.mapper.CharacterMapper;
import com.olehedza.rickandmorty.mapper.LocationMapper;
import com.olehedza.rickandmorty.mapper.OriginMapper;
import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.service.CharacterService;
import com.olehedza.rickandmorty.service.EpisodeService;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class RickandmortyApplication {
    private static final String NODE_NAME = "results";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RickandmortyApplication.class);
    private final RickAndMortyAPIClient apiClient;
    private final CharacterService characterService;
    private final EpisodeService episodeService;
    private final OriginMapper originMapper;
    private final LocationMapper locationMapper;
    private final CharacterMapper characterMapper;
    private int requestCounter;
    private @Value("${api.request-limit}") int requestLimit;
    private @Value("${api.character.uri}") String characterUri;

    public RickandmortyApplication(RickAndMortyAPIClient apiClient,
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
        requestCounter = 1;
    }

    @Scheduled(cron = "${api.database.sync.cron}")
    void apiDatabaseSync() throws JsonProcessingException, URISyntaxException {
        if (requestCounter <= requestLimit) {
            String jsonString = apiClient.getJsonString(characterUri + requestCounter);
            List<CharacterDto> dtoList = characterMapper.toDtoList(jsonString, NODE_NAME);
            for (CharacterDto dto: dtoList) {
                Character character = characterMapper.toModel(dto);
                character.setOrigin(originMapper.toModel(dto.getOrigin()));
                character.setLocation(locationMapper.toModel(dto.getLocation()));
                character.getEpisodes().forEach(episodeService::save);
                characterService.save(character);
            }
            LOGGER.info("API request {}", requestCounter);
        }
        requestCounter++;
    }

    public static void main(String[] args) {
        SpringApplication.run(RickandmortyApplication.class, args);
    }
}
