package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.model.ArtistFestival;
import com.example.locostage.domain.model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {



}
