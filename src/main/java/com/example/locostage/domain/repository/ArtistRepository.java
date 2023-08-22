package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Artist;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {


    @Query("select distinct e.artist from Event e join e.venue v where v.country=:country")
    List<Artist> findAllArtistsByCountryWithEvent(@Param("country") String country);



}
