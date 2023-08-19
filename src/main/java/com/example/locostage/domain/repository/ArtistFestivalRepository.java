package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.ArtistFestival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistFestivalRepository extends JpaRepository<ArtistFestival, Long> {


}
