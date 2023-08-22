package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Event;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e join e.venue v where v.country = :country ORDER BY e.date asc")
    List<Event> findAllEventsByCountry(@Param("country") String country);



}
