package com.olehedza.rickandmorty.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginResponseDto origin;
    private LocationResponseDto location;
    private String image;
    private List<String> episodes;
    private String url;
    private String created;
}
