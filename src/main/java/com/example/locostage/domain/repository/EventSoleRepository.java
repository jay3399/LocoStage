package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.EventSole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSoleRepository extends JpaRepository<EventSole , Long> {

}
