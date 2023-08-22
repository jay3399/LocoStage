package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.EventFestival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFestivalRepository extends JpaRepository<EventFestival , Long> {

}
