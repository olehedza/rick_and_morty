package com.olehedza.rickandmorty.service.impl;

import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.repository.CharacterDao;
import com.olehedza.rickandmorty.service.CharacterService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterDao characterDao;
    private final Random random;

    @Autowired
    public CharacterServiceImpl(CharacterDao characterDao) {
        this.characterDao = characterDao;
        this.random = new Random();
    }

    @Override
    public Character save(Character character) {
        return characterDao.save(character);
    }

    @Override
    public Character getRandomCharacter() {
        List<Character> characters = characterDao.findAll();
        return characters.get(random.nextInt(characters.size()));
    }

    @Override
    public List<Character> getCharactersByNameContains(String name) {
        return characterDao.getAllByNameContains(name);
    }
}
