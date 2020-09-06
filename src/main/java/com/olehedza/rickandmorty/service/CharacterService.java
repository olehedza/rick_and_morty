package com.olehedza.rickandmorty.service;

import com.olehedza.rickandmorty.model.Character;
import java.util.List;

public interface CharacterService {
    Character save(Character character);

    Character getRandomById();

    List<Character> getAllByNameContains(String name);
}
