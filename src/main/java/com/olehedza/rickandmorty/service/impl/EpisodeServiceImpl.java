package com.olehedza.rickandmorty.service.impl;

import com.olehedza.rickandmorty.model.Episode;
import com.olehedza.rickandmorty.repository.EpisodeDao;
import com.olehedza.rickandmorty.service.EpisodeService;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeDao episodeDao;

    public EpisodeServiceImpl(EpisodeDao episodeDao) {
        this.episodeDao = episodeDao;
    }

    @Override
    public Episode save(Episode episode) {
        return episodeDao.save(episode);
    }
}
