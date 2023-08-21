package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.back.FestivalEvent;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<FestivalEvent, Long> {

    List<FestivalEvent> findByVenueCountryOrderByDateDesc(String country ,Pageable pageable);

}
