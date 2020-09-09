package com.olehedza.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.olehedza.rickandmorty.dto.client.CharactersDto;
import java.net.URISyntaxException;

public interface JsonParser {
    CharactersDto parseToPojo() throws JsonProcessingException, URISyntaxException;
}
