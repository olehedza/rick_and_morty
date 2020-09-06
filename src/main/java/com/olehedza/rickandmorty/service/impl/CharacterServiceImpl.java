package com.olehedza.rickandmorty.service.impl;

import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.repository.CharacterDao;
import com.olehedza.rickandmorty.service.CharacterService;
import com.olehedza.rickandmorty.service.LocationService;
import com.olehedza.rickandmorty.service.OriginService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private static final int MIN = 1;
    private static final int MAX = 20;
    private final LocationService locationService;
    private final OriginService originService;
    private final CharacterDao characterDao;

    @Autowired
    public CharacterServiceImpl(LocationService locationService, OriginService originService,
                                CharacterDao characterDao) {
        this.locationService = locationService;
        this.originService = originService;
        this.characterDao = characterDao;
    }

    @Override
    public Character save(Character character) {
        return characterDao.save(character);
    }

    @Override
    public Character getRandomById() {
        Random random = new Random();
        long lRandom = random.nextInt((MAX - MIN) + 1) + MIN;
        return characterDao.getById(lRandom);
    }

    @Override
    public List<Character> getAllByNameContains(String name) {
        return characterDao.getAllByNameContains(name);
    }
}
