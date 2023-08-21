package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Artist;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {


    @Query("select a from Artist a join a.artistFestivals af join af.festival fe join fe.venue v where v.country = :country")
    List<Artist> findArtistByFestival(@Param("country") String country, Pageable pageable);


    @Query("select a from Artist a  join  a.soleEvents se join  se.venue v where v.country = :country")
    List<Artist> findArtistBySole(@Param("country") String country, Pageable pageable);




}
