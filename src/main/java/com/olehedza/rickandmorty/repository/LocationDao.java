package com.olehedza.rickandmorty.repository;

import com.olehedza.rickandmorty.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDao extends JpaRepository<Location, Long> {
}
