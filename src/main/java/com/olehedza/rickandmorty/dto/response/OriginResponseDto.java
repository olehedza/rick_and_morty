package com.olehedza.rickandmorty.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OriginResponseDto {
    private Long id;
    private String name;
    private String url;
}
