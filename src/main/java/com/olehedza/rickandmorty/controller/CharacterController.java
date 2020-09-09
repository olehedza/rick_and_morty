package com.olehedza.rickandmorty.controller;

import com.olehedza.rickandmorty.dto.response.CharacterResponseDto;
import com.olehedza.rickandmorty.mapper.CharacterMapper;
import com.olehedza.rickandmorty.model.Character;
import com.olehedza.rickandmorty.service.CharacterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService,
                               CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping("/random")
    public CharacterResponseDto getRandomCharacter() {
        Character randomById = characterService.getRandomCharacter();
        return characterMapper.toDto(randomById);
    }

    @GetMapping
    public List<CharacterResponseDto> filterAllByNameString(
            @RequestParam(required = false, defaultValue = "") String name
    ) {
        return characterService.getCharactersByNameContains(name).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
