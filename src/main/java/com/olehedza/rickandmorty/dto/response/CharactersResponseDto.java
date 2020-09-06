package com.olehedza.rickandmorty.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CharactersResponseDto {
    private List<CharacterResponseDto> characterResponseDtos;
}
