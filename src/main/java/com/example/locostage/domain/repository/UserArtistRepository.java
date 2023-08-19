package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.UserArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArtistRepository extends JpaRepository<UserArtist, Long> {


}
