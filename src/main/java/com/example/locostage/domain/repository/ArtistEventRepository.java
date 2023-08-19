package com.example.locostage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistEventRepository extends JpaRepository<ArtistEventRepository, Long> {

}
