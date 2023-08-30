package com.example.locostage.domain.repository;

import com.example.locostage.application.dto.EventDetailed;
import com.example.locostage.domain.model.Festival;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    @Query("select f from Festival f join f.venue v where v.country = :country order by  f.startDate asc ")
    List<Festival> findAllFestivalsByCountry(@Param("country") String country);
//
//    @Query("select new com.example.locostage.application.dto.EventDetailed(f, ef, v) from Festival f join f.eventFestivals ef join f.venue v where ef.eventId = :eventId")
//    List<EventDetailed> findFestivalAndEventsByEventId(@Param("eventId") Long eventId);

}
