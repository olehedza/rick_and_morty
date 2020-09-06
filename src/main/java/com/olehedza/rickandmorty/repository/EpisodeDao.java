package com.olehedza.rickandmorty.repository;

import com.olehedza.rickandmorty.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeDao extends JpaRepository<Episode, Long> {
}
