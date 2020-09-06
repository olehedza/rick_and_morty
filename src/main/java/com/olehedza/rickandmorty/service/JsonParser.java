package com.olehedza.rickandmorty.service;

import com.olehedza.rickandmorty.dto.client.CharactersDto;

public interface JsonParser {
    CharactersDto parseToPojo();
}
