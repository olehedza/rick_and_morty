package com.olehedza.rickandmorty.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OriginDto {
    private String name;
    private String url;
}
