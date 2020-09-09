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
    private static final int MIN = 1;
    private static final int MAX = 20;
    private final CharacterDao characterDao;

    @Autowired
    public CharacterServiceImpl(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    @Override
    public Character save(Character character) {
        return characterDao.save(character);
    }

    @Override
    public Character getRandomCharacter() {
        Random random = new Random();
        long randomCharacterId = random.nextInt((MAX - MIN) + 1) + MIN;
        return characterDao.getById(randomCharacterId);
    }

    @Override
    public List<Character> getCharactersByNameContains(String name) {
        return characterDao.getAllByNameContains(name);
    }
}
