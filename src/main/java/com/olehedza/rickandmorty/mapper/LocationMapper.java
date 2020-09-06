package com.olehedza.rickandmorty.mapper;

import com.olehedza.rickandmorty.dto.client.LocationDto;
import com.olehedza.rickandmorty.dto.response.LocationResponseDto;
import com.olehedza.rickandmorty.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public Location toModel(LocationDto dto) {
        Location model = new Location();
        model.setName(dto.getName());
        model.setUrl(dto.getUrl());
        return model;
    }

    public LocationResponseDto toDto(Location model) {
        LocationResponseDto dto = new LocationResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setUrl(model.getUrl());
        return dto;
    }
}
