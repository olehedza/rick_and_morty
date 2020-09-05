package com.olehedza.rickandmorty.service;

import com.olehedza.rickandmorty.dto.CharacterDto;
import com.olehedza.rickandmorty.dto.CharactersDto;

public interface JsonParser {
    CharactersDto parseToPojo();
}
