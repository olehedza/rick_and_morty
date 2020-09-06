package com.olehedza.rickandmorty.mapper;

import com.olehedza.rickandmorty.dto.client.OriginDto;
import com.olehedza.rickandmorty.dto.response.OriginResponseDto;
import com.olehedza.rickandmorty.model.Origin;
import org.springframework.stereotype.Component;

@Component
public class OriginMapper {
    public Origin toModel(OriginDto dto) {
        Origin model = new Origin();
        model.setName(dto.getName());
        model.setUrl(dto.getUrl());
        return model;
    }

    public OriginResponseDto toDto(Origin model) {
        OriginResponseDto dto = new OriginResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setUrl(model.getUrl());
        return dto;
    }
}
