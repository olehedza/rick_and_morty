package com.olehedza.rickandmorty.service.impl;

import com.olehedza.rickandmorty.model.Origin;
import com.olehedza.rickandmorty.repository.OriginDao;
import com.olehedza.rickandmorty.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginServiceImpl implements OriginService {
    private final OriginDao originDao;

    @Autowired
    public OriginServiceImpl(OriginDao originDao) {
        this.originDao = originDao;
    }

    @Override
    public Origin save(Origin origin) {
        return originDao.save(origin);
    }
}
