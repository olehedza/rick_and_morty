package com.olehedza.rickandmorty.repository;

import com.olehedza.rickandmorty.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginDao extends JpaRepository<Origin, Long> {
}
