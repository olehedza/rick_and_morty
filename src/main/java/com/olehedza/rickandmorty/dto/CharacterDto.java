package com.olehedza.rickandmorty.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CharacterDto {
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginDto origin;
    private LocationDto location;
    private String image;
    private List<String> episodes;
    private String url;
    private String created;
}
