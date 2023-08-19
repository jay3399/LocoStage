package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Event;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByVenueCountryOrderOrderByDateDesc(String country ,Pageable pageable);

}
