package com.olehedza.rickandmorty.repository;

import com.olehedza.rickandmorty.model.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterDao extends JpaRepository<Character, Long> {
    Character getById(Long id);
    List<Character> getAllByNameContains(String name);
}
