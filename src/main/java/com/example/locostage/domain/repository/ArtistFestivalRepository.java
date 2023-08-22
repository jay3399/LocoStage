//package com.example.locostage.domain.repository;
//
//import com.example.locostage.domain.model.back.ArtistFestival;
//import com.example.locostage.domain.model.Festival;
//import java.awt.print.Pageable;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ArtistFestivalRepository extends JpaRepository<ArtistFestival, Long> {
//
//    @Query("SELECT af.festival FROM ArtistFestival af WHERE af.artist.artistId = :artist")
//    List<Festival> findFestivalsByArtist(@Param("artist") Long artistId , Pageable pageable);
//
//
//
//
//}
