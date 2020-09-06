package com.olehedza.rickandmorty.service.impl;

import com.olehedza.rickandmorty.model.Location;
import com.olehedza.rickandmorty.repository.LocationDao;
import com.olehedza.rickandmorty.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationDao locationDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Location save(Location location) {
        return locationDao.save(location);
    }
}
