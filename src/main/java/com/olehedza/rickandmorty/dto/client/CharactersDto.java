package com.olehedza.rickandmorty.dto.client;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CharactersDto {
    private List<CharacterDto> characterDtos;
}
